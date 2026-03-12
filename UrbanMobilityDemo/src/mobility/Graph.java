package mobility;

import java.util.*;

public class Graph {
    Map<Node, List<Edge>> adjacencyList = new HashMap<>();

    public void addNode(Node node){
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to, double distance, double traffic) {
        adjacencyList.get(from).add(new Edge(to, distance, traffic));
        adjacencyList.get(to).add(new Edge(from, distance, traffic)); // 双向
    }

    public List<Edge> getEdges(Node node) {
        return adjacencyList.get(node);
    }

    public Set<Node> getNodes() {
        return adjacencyList.keySet();
    }


}
