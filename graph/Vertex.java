package graph;

import dict.HashTableChained;

import java.util.List;

public class Vertex {
    Object name;
    HashTableChained connectingEdges;

    public Vertex(Object originalObject) {
        this.name = originalObject;

    }
}
