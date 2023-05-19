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
            index++;
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
        Vertex aVertex = new Vertex(a), bVertex = new Vertex(b);
        Edge edge = new Edge(aVertex, bVertex, w);
        boolean duplicate = false;
        for (int i = 0; i < edges.length; i++) {
            if (edge.compareTo(edges[i]) == 0) {
                duplicate = true;
            }
        }
        if (duplicate) {
            return;
        }
        Edge[] newEdges = new Edge[edges.length + 1];
        int index = 0;
        while (edge.compareTo(edges[index]) < 0) {
            newEdges[index] = edges[index];
            index++;
        }
        newEdges[index] = edge;
        for (int i = index; i < newEdges.length; i++) {
            newEdges[i] = edges[i-1];
        }
        edges = newEdges;
    }

    public void removeEdge(String a, String b) {
        Edge edge = new Edge(new Vertex(a),new Vertex(b), 0);
        boolean found = false;
        int indexAt = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edge.compareTo(edges[i]) == 0) {
                found = true;
                indexAt = i;
            }
        }
        if (!found) {
            return;
        }
        for (int i = indexAt; i < edges.length - 1; i++) {
            edges[i] = edges[i + 1];
        }
        Edge[] newEdges = new Edge[edges.length - 1];
        for (int i = 0; i < newEdges.length; i++) {
            newEdges[i] = edges[i];
        }
        edges = newEdges;
    }

    public int[][] unionFind() {
        boolean cycle = false;
        int[] next = new int[vertices.length];
        int[] root = new int[vertices.length];
        int[] length = new int[vertices.length];
        int[] result = new int[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            root[i] = i;
            next[i] = i;
            length[i] = 1;
            result[i] = 0;
        }
        for (int i = 0; i < edges.length; i++) {
            Edge currEdge = edges[i];
            int v = findVertIndex(currEdge.vertexA);
            int u = findVertIndex(currEdge.vertexB);

            if (root[u] == root[v]) {
                cycle = true;
            } else if (length[root[v]] < length[root[u]]){
                int rt = root[v];
                length[root[u]] += length[rt];
                root[rt] = root[u];
                for (int j = next[rt]; j != rt; j=next[j]) {
                    root[j] = root[u];
                }
                int temp = next[rt];
                next[rt] = next[root[u]];
                next[root[u]] = next[rt];
            } else {
                int rt = root[u];
                length[root[v]] += length[rt];
                root[rt] = root[v];
                for (int j = next[rt]; j != rt; j=next[j]) {
                    root[j] = root[v];
                }
                int temp = next[rt];
                next[rt] = next[root[v]];
                next[root[v]] = next[rt];
            }
        }
        if (cycle) {
            for (int i = 0; i < result.length; i++) {
                result[i] = 1;
            }
        }
        int[][] retArr = new int[4][vertices.length];
        retArr[0] = root;
        retArr[1] = next;
        retArr[2] = length;
        retArr[3] = result;
        return retArr;
    }
    private int findVertIndex(Vertex vertex){
        for (int i = 0; i < vertices.length; i++) {
            if (vertex.compareTo(vertices[i]) == 0) {
                return i;
            }
        }
        return -1;
    }
    public boolean cycle() {
        int[][] unionResult = unionFind();
        if (unionResult[3][0] == 0) {
            return false;
        } else {
            return true;
        }
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
