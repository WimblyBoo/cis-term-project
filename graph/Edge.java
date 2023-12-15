package graph;


import java.util.ArrayList;
import java.util.List;

public class Edge {
    int weight;
    Vertex toVertex;
    Vertex fromVertex;

    public Edge(int weight, Vertex toVertex, ) {
        this.weight = weight;
        this.toVertex = toVertex;
    }
}