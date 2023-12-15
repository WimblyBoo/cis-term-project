package graph;

import list.DList;

public class Vertex {
    Object name;
    DList edges;

    public Vertex(Object originalObject) {
        this.name = originalObject;
        this.edges = new DList();
    }
}
