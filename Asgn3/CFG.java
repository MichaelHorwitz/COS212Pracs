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
        this.nodes = new myDS<Node>();
        this.edges = new myDS<Edge>();
        this.exitNodes = new myDS<Node>();
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
                if (connected(node, exitNode, null, null)) {
                    valid = true;
                }
            }
            if (!valid) {
                return false;
            }

        }
        return true;
    }

    private boolean connected(Node firstNode, Node goalNode, Node[] allNodes, boolean[] visited) {

        if (allNodes == null) {
            allNodes = Node.objToNodeArr(nodes.toArray());
        }
        if (visited == null) {
            visited = new boolean[allNodes.length];
        }
        if (firstNode.equals(goalNode)) {
            return true;
        }
        Edge[] edges = firstNode.getEdges();
        for (Edge edge : edges) {
            boolean conn = false;
            if (findIndex(allNodes, edge.getNext()) != -1 &&   visited[findIndex(allNodes, edge.getNext())] == false) {
                visited[findIndex(allNodes, edge.getNext())] = true;
                conn = connected(edge.getNext(), goalNode, allNodes, visited);
            }
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
        // For each exit node EN
        Object[] ogExitNodes = exitNodes.toArray();
        CFG[] cfgs = new CFG[ogExitNodes.length];
        for (int i = 0; i < ogExitNodes.length; i++) {
            // for (Object exitNodeObj : exitNodes.toArray()) {
            // make a new CFG with the current CFG s start node as the start node and EN as
            // the exit node .
            Node exitNode = (Node) ogExitNodes[i];
            Node[] nodeArr = { startNode, exitNode };
            Edge[] edgeArr = new Edge[startNode.getEdges().length + exitNode.getEdges().length];
            Node[] exitArr = { exitNode };
            CFG currCFG = new CFG(nodeArr, edgeArr, startNode, exitArr);
            cfgs[i] = currCFG;
        }
        // For every node N in the current CFG
        Object[] ogAllNodes = nodes.toArray();
        for (int i = 0; i < ogAllNodes.length; i++) {
            Node currNode = (Node) ogAllNodes[i];
            for (int j = 0; j < ogExitNodes.length; j++) {
                // check if N can reach each of the exit nodes
                Node currExitNode = (Node) ogExitNodes[j];
                if (connected(currNode, currExitNode, null, null)) {
                    // If it can then add that node to the appropriate new CFG .
                    if (!cfgs[j].nodes.contains(currNode)) {
                        cfgs[j].addNode(currNode);
                    }
                    for (Edge edge : currNode.getEdges()) {
                        // Remember to also add all the appropriate edges .
                        if (!cfgs[j].edges.contains(edge)) {
                            cfgs[j].addEdge(edge.getAnnotation(), currNode, edge.getNext(), edge.getCompTime());
                        }

                    }
                }
            }
        }
        return cfgs;
    }

    public boolean isReachable(Node startNode, Node goalNode) {
        return connected(startNode, goalNode, null, null);
    }

    public int compTimeRequired(Path p) {
        if (p == null) {
            return -1;
        }
        return p.computationalCostOfPath();
    }

    public Path shortestCompTimePath(Node sN, Node gN) {
        // Dijkstra(Graph, start){
        Node[] allNodes = Node.objToNodeArr(nodes.toArray());
        // create vertex set unvisited
        Node[] unvisitedNodes = Node.objToNodeArr(nodes.toArray());
        double[] distance = new double[allNodes.length];
        Node[] previouNodes = Node.objToNodeArr(nodes.toArray());

        // for (each vertex v in Graph) { // Initialization
        for (int i = 0; i < allNodes.length; i++) {
            // v.dist = INFINITY // Unknown distance from start to v
            distance[i] = Double.POSITIVE_INFINITY;
            // v.prev = null // Previous node in shortest path
            previouNodes[i] = null;
            // add v to unvisited // All vertices initially unvisited
            // }
        }
        // start.dist = 0 // Distance from start to start (0)
        int startIndex = findIndex(allNodes, sN);
        distance[startIndex] = 0;

        // while (!unvisited.empty()) {
        while (firstNode(unvisitedNodes) != -1) {
            // curr = vertex in unvisited with min dist // will be start
            int minIndex = firstNode(unvisitedNodes);
            Double minDist = distance[minIndex];
            for (int i = 0; i < unvisitedNodes.length; i++) {
                if (distance[i] < minDist && unvisitedNodes[i] != null) {
                    minIndex = i;
                    minDist = distance[i];
                }
            }
            // remove curr from unvisited
            Node currNode = unvisitedNodes[minIndex];
            unvisitedNodes[minIndex] = null;
            // for (each unvisited neighbour v of curr) {
            Edge[] neighbours = Edge.objToEdgeArr(currNode.getEdges());
            for (int i = 0; i < neighbours.length; i++) {
                int currIndex = findIndex(allNodes, neighbours[i].getNext());
                // newDist = curr.dist + length(curr, v)
                Double newDist = distance[minIndex] + neighbours[i].getCompTime();
                // if (newDist < v.dist) {
                if (newDist < distance[currIndex]) {
                    // v.dist = newDist;
                    distance[currIndex] = newDist;
                    // v.prev = curr;
                    previouNodes[currIndex] = allNodes[minIndex];
                    // }
                }
                // }
            }
            // }
        }
        int goalIndex = findIndex(allNodes, gN);
        Node currPrev = previouNodes[goalIndex];
        int currPrevIndex = findIndex(allNodes, currPrev);
        myDS<Node> reversePathNodes = new myDS<Node>();
        myDS<Edge> reversePathEdges = new myDS<Edge>();
        Node prev = null;
        reversePathNodes.insert(gN);
        while (!currPrev.equals(sN)) {
            reversePathNodes.insert(currPrev);
            prev = currPrev;
            currPrev = previouNodes[currPrevIndex];
            currPrevIndex = findIndex(allNodes, currPrev);
            Edge edge = findEdge(currPrev.getEdges(), prev);
            reversePathEdges.insert(edge);
        }
        if (prev == null) {
            reversePathEdges.insert(findEdge(currPrev.getEdges(), gN));
            reversePathNodes.insert(sN);
        } else {
            reversePathEdges.insert(findEdge(currPrev.getEdges(), prev));
            reversePathNodes.insert(sN);
        }
        Node[] reverseNodes = Node.objToNodeArr(reversePathNodes.toArray());
        Edge[] reverseEdges = Edge.objToEdgeArr(reversePathEdges.toArray());
        Node[] pathNodes = new Node[reverseNodes.length];
        Edge[] pathEdges = new Edge[reverseEdges.length];
        int j = 0;
        for (int i = reverseNodes.length - 1; i >= 0; i--) {
            pathNodes[j] = reverseNodes[i];
            j++;
        }
        j = 0;
        for (int i = reverseEdges.length - 1; i >= 0; i--) {
            pathEdges[j] = reverseEdges[i];
            j++;
        }
        Path p = new Path(sN, gN, pathNodes, pathEdges);
        return p;
    }

    private Edge findEdge(Edge[] arr, Node next) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getNext().equals(next)) {
                return arr[i];
            }
        }
        return null;
    }

    private int firstNode(Node[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                return i;
            }
        }
        return -1;
    }

    private int findIndex(Node[] arr, Node node) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }

    public Path[] getPrimePaths() {
        Path[] simpPaths = getSimplePaths();

        return simpPaths;
    }

    public Path[] getSimplePaths() {
        myDS<myDS<Path>> temp = new myDS<myDS<Path>>();
        Node[] nodeArr = Node.objToNodeArr(nodes.toArray());
        for (Node node : nodeArr) {
            myDS<Path> paths = new myDS<Path>();
            Path p = new Path(startNode, startNode, new Node[0], new Edge[0]);
            int length = nodes.toArray().length;
            temp.insert(resSimple(node, p, length, paths));
        }
        Object[] allMyDSArray = temp.toArray();
        int totalNumPaths = 0;
        for (Object object : allMyDSArray) {
            myDS<Path> myDSTemp = (myDS<Path>) object;
            totalNumPaths += myDSTemp.toArray().length;
        }
        Path[] ret = new Path[totalNumPaths];
        int i = 0;
        for (Object object : allMyDSArray) {
            myDS<Path> myDSTemp = (myDS<Path>) object;
            Path[] pathArr = objToPath(myDSTemp.toArray());
            for (Path path : pathArr) {
                ret[i] = path;
                i++;

            }
        }
        return ret;
    }

    private Path[] objToPath(Object[] arr) {
        Path[] ret = new Path[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = (Path) arr[i];
        }
        return ret;
    }

    private myDS<Path> resSimple(Node currNode, Path currPath, int lengthRemaining, myDS<Path> paths) {
        if (lengthRemaining == 0) {
            return paths;
        }
        Edge[] edges = currNode.getEdges();
        Path p = new Path(currPath);
        if (p.validPath()) {
            paths.insert(p);
        }
        for (Edge edge : edges) {
            Node[] pAddNodes = { currNode, edge.getNext() };
            Edge[] pAddEdges = { edge };
            Path pAdd = new Path(currNode, edge.getNext(), pAddNodes, pAddEdges);
            p = new Path(currPath);
            p.appendToPath(pAdd);
            paths.insert(p);
            paths = resSimple(edge.getNext(), p, lengthRemaining - 1, paths);
        }
        return paths;
    }

    public void addNode(String annotation) {
        Node newNode = new Node(annotation);
        nodes.insert(newNode);
    }

    public void addNode(Node node) {
        nodes.insert(node);
    }

    public void addEdge(String annotation, Node fromNode, Node toNode, int computationalTime) {
        Edge newEdge = new Edge(annotation, toNode, computationalTime);
        fromNode.addEdge(toNode, annotation, computationalTime);
        edges.insert(newEdge);
    }

    public void addExitNode(Node n) {
        exitNodes.insert(n);
        nodes.insert(n);
    }

    public void addStartNode(Node n) {
        startNode = n;
        nodes.insert(n);
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
        Node[] nodeArr = Node.objToNodeArr(nodes.toArray());
        Node goalNode = new Node(annotation);
        for (int i = 0; i < nodeArr.length; i++) {
            if (nodeArr[i].equals(goalNode)) {
                return nodeArr[i];
            }
        }
        return null;
    }
}
