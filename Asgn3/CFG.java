public class CFG {
    private Node startNode;
    private myDS<Node> nodes;
    private myDS<Edge> edges;
    private myDS<Node> exitNodes;

    public CFG() {
        nodes = new myDS<Node>();
        edges = new myDS<Edge>();
        exitNodes = new myDS<Node>();
    }

    public CFG(Node[] nodes, Edge[] edges, Node sNode, Node[] exitNodes) {
        if (nodes == null) {
            this.nodes = new myDS<Node>();
        } else {
            for (Node node : nodes) {
                this.nodes.insert(node);
            }
        }
        if (edges == null) {
            this.edges = new myDS<Edge>();
        } else {
            for (Edge edge : edges) {
                this.edges.insert(edge);
            }
        }
        startNode = sNode;
        if (exitNodes == null) {
            this.exitNodes = new myDS<Node>();
        } else {
            for (Node node : exitNodes) {
                this.exitNodes.insert(node);
            }
        }
    }

    public CFG(CFG other) {
        if (other == null) {
            nodes = new myDS<Node>();
            edges = new myDS<Edge>();
            exitNodes = new myDS<Node>();
            return;
        }
        nodes = other.nodes;
        edges = other.edges;
        exitNodes = other.exitNodes;
        startNode = other.startNode;
    }

    public boolean isValid() {
        // TODO: Implement the function
    }

    public boolean isSESE() {
        // TODO: Implement the function
    }

    public CFG[] splitGraph() {
        // TODO: Implement the function
    }

    public boolean isReachable(Node startNode, Node goalNode) {
        // TODO: Implement the function
    }

    public int compTimeRequired(Path p) {
        // TODO: Implement the function
    }

    public Path shortestCompTimePath(Node sN, Node gN) {
        // TODO: Implement the function
    }

    public Path[] getPrimePaths() {
        // TODO: Implement the function
    }

    public Path[] getSimplePaths() {
        // TODO: Implement the function
    }

    public void addNode(String annotation) {
        // TODO: Implement the function
    }

    public void addNode(Node node) {
        // TODO: Implement the function
    }

    public void addEdge(String annotation, Node fromNode, Node toNode, int computationalTime) {
        // TODO: Implement the function
    }

    public void addExitNode(Node n) {
        // TODO: Implement the function
    }

    public void addStartNode(Node n) {
        // TODO: Implement the function
    }

    public String toString() {
        // Provided function, do not alter!!!
        String res = "";
        for (Object n : nodes.toArray()) {
            res += n.toString();
        }
        return res;
    }

    public Node getNode(String annotation) {
        // TODO: Implement the function
    }
}
