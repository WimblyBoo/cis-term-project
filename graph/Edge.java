package graph;


import list.DListNode;

public class Edge {
    VertexPair vertexPair;
    DListNode partnerEdge;
    Vertex connectingVertex;



    public Edge(VertexPair vertexPair) {
        this.vertexPair = vertexPair;
    }

    public void setPartnerEdge(DListNode partnerEdge) {
        this.partnerEdge = partnerEdge;
    }
    public void setConnectingVertex(Vertex connectingVertex) {
        this.connectingVertex = connectingVertex;
    }
}