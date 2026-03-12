package mobility;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 输入起点、终点、出发时间（24小时制，例如 8.5 = 8:30）
        System.out.print("Enter start node (A-F): ");
        String startName = scanner.nextLine().toUpperCase();
        System.out.print("Enter end node (A-F): ");
        String endName = scanner.nextLine().toUpperCase();
        System.out.print("Enter departure time (hour in 0-24, e.g., 8.5 for 8:30): ");
        double departureTime = scanner.nextDouble();

        // 创建城市图
        Graph city = new Graph();

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        Node[] nodes = {A, B, C, D, E, F};
        for (Node node : nodes) city.addNode(node);

        // Rush Hour 判断
        boolean rushHour = (departureTime >= 8 && departureTime <= 10) ||
                (departureTime >= 17 && departureTime <= 19);

        double normal = 1.0;
        double rush = 1.5;

        // 添加道路（根据 rushHour 自动调整 trafficFactor）
        city.addEdge(A, B, 5, rushHour ? rush : normal);
        city.addEdge(B, C, 5, rushHour ? rush : normal);
        city.addEdge(A, D, 6, rushHour ? rush : normal);
        city.addEdge(B, E, 4, rushHour ? rush : normal);
        city.addEdge(D, E, 2, rushHour ? rush : normal);
        city.addEdge(E, F, 3, rushHour ? rush : normal);
        city.addEdge(C, F, 5, rushHour ? rush : normal);

        // 查找对应节点对象
        Node startNode = null, endNode = null;
        for (Node node : nodes) {
            if (node.getName().equals(startName)) startNode = node;
            if (node.getName().equals(endName)) endNode = node;
        }

        if (startNode == null || endNode == null) {
            System.out.println("Invalid node names!");
            return;
        }

        // 执行升级版 Dijkstra
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();
        dijkstra(city, startNode, distance, prev);

        // 输出结果
        System.out.println("\n=== Route Recommendation ===");
        if (!distance.containsKey(endNode) || distance.get(endNode) == Double.MAX_VALUE) {
            System.out.println("No path found from " + startName + " to " + endName);
        } else {
            System.out.println("Estimated travel time: " + distance.get(endNode) + " minutes");
            System.out.print("Route: ");
            printPath(prev, endNode);
            System.out.println();
            System.out.println("Traffic condition: " + (rushHour ? "Rush Hour" : "Normal"));
        }
    }

    // 升级版 Dijkstra，记录前驱节点
    public static void dijkstra(Graph graph, Node start, Map<Node, Double> distance, Map<Node, Node> prev) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(distance::get));

        for (Node node : graph.getNodes()) {
            distance.put(node, Double.MAX_VALUE);
            prev.put(node, null);
        }

        distance.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Edge edge : graph.getEdges(current)) {
                Node neighbor = edge.target;
                double newDist = distance.get(current) + edge.getWeight();
                if (newDist < distance.get(neighbor)) {
                    distance.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    // 从终点回溯路径
    public static void printPath(Map<Node, Node> prev, Node end) {
        List<String> path = new ArrayList<>();
        Node current = end;
        while (current != null) {
            path.add(current.getName());
            current = prev.get(current);
        }
        Collections.reverse(path);
        System.out.println(String.join(" → ", path));
    }
}