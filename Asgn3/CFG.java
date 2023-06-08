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
        if (startNode == null) {
            return false;
        }
        if (exitNodes.toArray().length < 1) {
            return false;
        }
        for (Object nodeObj : nodes.toArray()) {
            Node node = (Node) nodeObj;
            boolean valid = false;
            for (Object exitNodeObj : exitNodes.toArray()) {
                Node exitNode = (Node) exitNodeObj;
                if (node.equals(exitNode)) {
                    valid = true;
                }
                if (connected(node, exitNode)) {
                    valid = true;
                }
            }
            if (!valid) {
                return false;
            }

        }
        return true;
    }
    private boolean connected(Node firstNode, Node goalNode){
        if (firstNode.equals(goalNode)) {
            return true;
        }
        Edge[] edges = firstNode.getEdges();
        for (Edge edge : edges) {
            boolean conn = connected(edge.getNext(), goalNode);
            if (conn) {
                return true;
            }
        }
        return false;
    }
    public boolean isSESE() {
        boolean valid = this.isValid();
        if (exitNodes.toArray().length != 1) {
            return false;
        }
        if (!valid) {
            return false;
        }
        return true;
    }

    public CFG[] splitGraph() {
        //For each exit node EN
        Object[] ogExitNodes = exitNodes.toArray();
        CFG[] cfgs = new CFG[ogExitNodes.length];
        for (int i = 0; i <  ogExitNodes.length; i++) {
        //for (Object exitNodeObj : exitNodes.toArray()) {
            //make a new CFG with the current CFG s start node as the start node and EN as the exit node .
            Node exitNode = (Node) ogExitNodes[i];
            Node[] nodeArr  = {startNode, exitNode};
            Edge[] edgeArr = new Edge[startNode.getEdges().length + exitNode.getEdges().length];
            Node[] exitArr = {exitNode};
            CFG currCFG = new CFG(nodeArr, edgeArr, startNode, exitArr);
            cfgs[i] = currCFG;
        }
        //For every node N in the current CFG 
        Object[] ogAllNodes = nodes.toArray();
        for (int i = 0; i < ogAllNodes.length; i++) {
            Node currNode = (Node) ogAllNodes[i];
            for (int j = 0; j < ogExitNodes.length; j++) {
                //check if N can reach each of the exit nodes
                Node currExitNode = (Node) ogExitNodes[i];
                if (connected(currNode, currExitNode)) {
                    //If it can then add that node to the appropriate new CFG .
                    cfgs[i].addNode(currNode);
                    for (Edge edge : currNode.getEdges()) {
                        //Remember to also add all the appropriate edges .
                        cfgs[i].addEdge(edge.getAnnotation(), currNode, edge.getNext(), edge.getCompTime());
                    }
                }
            }
        }
        return cfgs;
    }

    public boolean isReachable(Node startNode, Node goalNode) {
        // TODO: Implement the function
        return false;
    }

    public int compTimeRequired(Path p) {
        // TODO: Implement the function
        return -1;
    }

    public Path shortestCompTimePath(Node sN, Node gN) {
        // TODO: Implement the function
        return null;
    }

    public Path[] getPrimePaths() {
        // TODO: Implement the function
        return null;
    }

    public Path[] getSimplePaths() {
        // TODO: Implement the function
        return null;
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
        return null;
    }
}
