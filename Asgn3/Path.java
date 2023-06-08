public class Path {
    private final Node startNode;
    private Node endNode;
    private final myDS<Node> nodes;
    private final myDS<Edge> edges;

    public Path(Node startNode, Node endNode, Node[] nodes, Edge[] edges){
        this.startNode = startNode;
        this.endNode = endNode;
        this.nodes = new myDS<Node>();
        this.edges = new myDS<Edge>();
        if (nodes != null) {
            for (int i = 0; i < nodes.length; i++) {
                this.nodes.insert(nodes[i]);
            }
        }
        if (edges != null) {
            for (int i = 0; i < edges.length; i++) {
                this.edges.insert(edges[i]);
            }
        }
    }

    public Path(Path other){
        startNode = other.startNode;
        endNode = other.endNode;
        nodes = new myDS<Node>();
        edges = new myDS<Edge>();
        Node[] nodeArr = (Node[])other.nodes.toArray();
        Edge[] edgeArr = (Edge[])other.edges.toArray();
        for (Node node : nodeArr) {
            nodes.insert(node);
        }
        for (Edge edge : edgeArr) {
            edges.insert(edge);
        }
    }

    public int computationalCostOfPath(){
        Edge[] edgeArr = (Edge[])edges.toArray();
        int cost = 0;
        for (Edge edge : edgeArr) {
            cost += edge.getCompTime();
        }
        return cost;
    }

    @SuppressWarnings("unchecked")
    public void appendToPath(Path p){
        Edge[] edgeArr = (Edge[])p.edges.toArray();
        Node[] nodeArr = (Node[])p.nodes.toArray();
        for (Node node : nodeArr) {
            if (!nodes.contains(node)) {
                nodes.insert(node);
            }
        }
        for (Edge edge : edgeArr) {
            if (!edges.contains(edge)) {
                edges.insert(edge);
            }
        }
    }

    public boolean validPath(){
        Node[] nodeArr = (Node[])nodes.toArray();
        Edge[] edgeArr;
        boolean valid = true;
        for (int i = 0; i < nodeArr.length - 1; i++) {
            Node currNode = nodeArr[i];
            Node nextNode = nodeArr[i+1];
            edgeArr = currNode.getEdges();
            valid = false;
            for (Edge edge : edgeArr) {
                if (edge.getNext().getAnnotation().equals(nextNode.getAnnotation())) {
                    valid = true;
                }
            }
            if (!valid) {
                return false;
            }
        }
        return valid;
    }

    public String toString(){
        //Provided function, do not alter!!!
        String str = "";
        str += ((Node)nodes.toArray()[0]).getAnnotation();
        for(Object e: edges.toArray()){
            str += e.toString();
        }
        return str;
    }
    
}
