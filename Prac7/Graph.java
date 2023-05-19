public class Graph {
    public Vertex[] vertices = new Vertex[0];
    public Edge[] edges = new Edge[0];

    public void addVertex(String v) {
        boolean duplicate = false;
        Vertex newVert = new Vertex(v);
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].compareTo(newVert) == 0) {
                duplicate = true;
            }
        }
        if (duplicate) {
            return;
        }
        Vertex[] newVertices = new Vertex[vertices.length + 1];
        int index = 0;
        while (newVert.compareTo(vertices[index]) < 0) {
            newVertices[index] = vertices[index];
        }
        newVertices[index] = newVert;
        for (int i = index + 1; i < newVertices.length; i++) {
            newVertices[i] = vertices[i-1];
        }
        vertices = newVertices;
    }

    public void removeVertex(String v) {
        Vertex vToFind = new Vertex(v);
        boolean found = false;
        int indexAt = 0;
        for (int i = 0; i < vertices.length; i++) {
            if (vToFind.compareTo(vertices[i]) == 0) {
                found = true;
                indexAt = i;
            }
        }
        if (!found) {
            return;
        }
        for (int i = indexAt; i < vertices.length - 1; i++) {
            vertices[i] = vertices[i+1];
        }
        Vertex[] newVertices = new Vertex[vertices.length - 1];
        for (int i = 0; i < newVertices.length; i++) {
            newVertices[i] = vertices[i];
        }
        vertices = newVertices;
    }

    public void addEdge(String a, String b, int w) {
    }

    public void removeEdge(String a, String b) {
    }

    public int[][] unionFind() {
        return null;
    }

    public boolean cycle() {
        return false;
    }

    public Graph minSpanningTree() {
        return null;
    }

    public Vertex[][] brelazColouring() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
