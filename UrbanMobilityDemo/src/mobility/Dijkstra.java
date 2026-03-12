package mobility;

import java.util.*;

public class Dijkstra {
    public static Map<Node, Double> shortestPath(Graph graph, Node start) {
        Map<Node, Double> distance = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(distance::get));

        for (Node node : graph.getNodes()) {
            distance.put(node, Double.MAX_VALUE);
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
                    queue.add(neighbor);
                }
            }
        }

        return distance;

    }
}



