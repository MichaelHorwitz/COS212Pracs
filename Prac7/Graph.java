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
        while (index < vertices.length && newVert.compareTo(vertices[index]) > 0) {
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
        while (index < edges.length && edge.compareTo(edges[index]) > 0) {
            newEdges[index] = edges[index];
            index++;
        }
        newEdges[index] = edge;
        for (int i = index + 1; i < newEdges.length; i++) {
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
                next[root[u]] = temp;
            } else {
                int rt = root[u];
                length[root[v]] += length[rt];
                root[rt] = root[v];
                for (int j = next[rt]; j != rt; j=next[j]) {
                    root[j] = root[v];
                }
                int temp = next[rt];
                next[rt] = next[root[v]];
                next[root[v]] = temp;
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
        Graph ret = new Graph();
        Edge[] newEdges = new Edge[edges.length];
        Vertex[] newVertices = new Vertex[vertices.length];
        for (int i = 0; i < newVertices.length; i++) {
            newVertices[i] = new Vertex(vertices[i].name);
            //ret.addVertex(vertices[i].name);
        }
        for (int i = 0; i < newEdges.length; i++) {
            int a = findVertIndex(edges[i].vertexA);
            int b = findVertIndex(edges[i].vertexB);
            newEdges[i] = new Edge(newVertices[a], newVertices[b], edges[i].weight);
        }
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < newEdges.length - 1; i++) {
                if (newEdges[i].weight > newEdges[i + 1].weight) {
                    sorted = false;
                    Edge temp = newEdges[i];
                    newEdges[i] = newEdges[i+1];
                    newEdges[i+1] = temp;
                }
            }
        }
        for (int i = 0; i < edges.length && ret.vertices.length < vertices.length; i++) {
            String a = newEdges[i].vertexA.name;
            String b = newEdges[i].vertexB.name;
            ret.addVertex(a);
            ret.addVertex(b);
            ret.addEdge(a, b, newEdges[i].weight);
            if (ret.cycle()) {
                ret.removeEdge(a, b);
            }
        }
        return ret;
    }

    public Vertex[][] brelazColouring() {
        int[] saturationDeg = new int[vertices.length];
        int[] uncoloredDeg = new int[vertices.length];
        int[] deg = getDegreeAll();
        int[] color = new int[vertices.length];
        int maxCol = -1;
        for (int i = 0; i < vertices.length; i++) {
            saturationDeg[i] = 0;
            uncoloredDeg[i] = deg[i];
            color[i] = -1;
        }
        while (hasZero(color)) {
            int v = findBCAV(saturationDeg, uncoloredDeg, color); //find max uncolored with highest saturation
            int j = findBCAJ(v, color);
            int [] neighbours = neighbours(v);
            for (int i = 0; i < neighbours.length; i++) {
                if (color[neighbours[i]] == -1) {
                    int u = neighbours[i];
                    int [] uNeigh = neighbours(u);
                    boolean assignedCj = false;
                    for (int k = 0; k < uNeigh.length; k++) {
                        if (color[uNeigh[k]] == j) {
                            assignedCj = true;
                        }
                    }
                    if (!assignedCj) {
                        saturationDeg[u]++;
                    }
                    uncoloredDeg[u]--;
                }
            }
            if(j > maxCol){
                maxCol = j;
            }
            color[v] = j;
        }
        Vertex[][] tempArr = new Vertex[maxCol + 1][vertices.length];
        int[] numVertices = new int[maxCol + 1];
        for (int i = 0; i < numVertices.length; i++) {
            numVertices[i] = 0;
        }
        for (int i = 0; i < color.length; i++) {
            int currCol = color[i];
            int currNum = numVertices[currCol]++;
            tempArr[currCol][currNum] = vertices[i];
        }
        Vertex[][] ret = new Vertex[maxCol + 1][];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new Vertex[numVertices[i]];
            for (int j = 0; j < numVertices[i]; j++) {
                ret[i][j] = tempArr[i][j];
            }
        }
        return ret;
    }
    private int findBCAJ(int v, int[] color){
        int j = 0;
        int [] neighbours = neighbours(v);
        boolean correctVal = false;
        while (!correctVal) {
            correctVal = true;
            for (int i = 0; i < neighbours.length; i++) {
                if (j == color[i]) {
                    j++;
                    correctVal = false;
                }
            }
        }
        return j;
        
    }
    private int[] neighbours(int index){
        int[] ret = new int[vertices.length];
        int numNeigh = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].vertexA.compareTo(vertices[index]) == 0) {
                ret[numNeigh] = findVertIndex(edges[i].vertexA);
                numNeigh++;
            }
            if (edges[i].vertexB.compareTo(vertices[index]) == 0) {
                ret[numNeigh] = findVertIndex(edges[i].vertexB);
                numNeigh++;
            }
        }
        int[] tempArr = new int[numNeigh];
        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = ret[i];
        }
        ret = tempArr;
        return ret;
    }
    private int findBCAV(int [] saturationDeg, int [] uncoloredDeg, int [] color){
        int index = -1;
        int maxUnCol = -1;
        int maxSat = -1;
        for (int i = 0; i < color.length; i++) {
            if (color[i] != -1) {
                if (saturationDeg[i] > maxSat) {
                    index = i;
                    maxSat = saturationDeg[i];
                    maxUnCol = uncoloredDeg[i];
                }
                if (saturationDeg[i] == maxSat) {
                    if (uncoloredDeg[i] > maxUnCol) {
                        index = i;
                        maxSat = saturationDeg[i];
                        maxUnCol = uncoloredDeg[i];
                    }
                }
            }
        }
        return index;
    }
    private boolean hasZero(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return true;
            }
        }
        return false;
    }
    private int getDegree(int index){
        int deg = 0;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].vertexA.compareTo(vertices[index]) == 0 || edges[i].vertexB.compareTo(vertices[index]) == 0) {
                deg++;
            }
        }
        return deg;
    }
    private int[] getDegreeAll(){
        int[] ret = new int[vertices.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = getDegree(i);
        }
        return ret;
    }
    @Override
    public String toString() {
        int[][] adjMatrix = new int [vertices.length][vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            int a = findVertIndex(edges[i].vertexA);
            int b = findVertIndex(edges[i].vertexB);
            adjMatrix[a][b] = edges[i].weight;
            adjMatrix[b][a] = adjMatrix[a][b];
        }
        String ret = "";

        for (int i = 0; i < vertices.length; i++) {
            ret += "\t";
            ret += vertices[i].name;
        }
        for (int i = 0; i < vertices.length; i++) {
            ret += "\n";
            ret += vertices[i].name;
            for (int j = 0; j < vertices.length; j++) {
                ret += "\t";
                ret += adjMatrix[i][j];
            }
        }
        return ret;
    }
}
