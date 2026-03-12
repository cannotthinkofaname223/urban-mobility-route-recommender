package mobility;

public class Edge {
    Node target;
    double distance;
    double trafficFactor;

    public Edge (Node target, double distance, double trafficFactor){
        this.target = target;
        this.distance = distance;
        this.trafficFactor = trafficFactor;
    }

    public double getWeight() {
        return distance * trafficFactor;
    }
}
