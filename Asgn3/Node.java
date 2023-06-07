public class Node {
    private final String annotation;
    private final myDS<Edge> edges;
    
    public Node(String annot){
        annotation = annot;
        edges = new myDS<Edge>();
    }

    public void addEdge(Node nextNode, String annotation, int compTime){
        Edge edgeToIns = new Edge(annotation, nextNode, compTime);
        edges.insert(edgeToIns);
    }

    public String getAnnotation(){
        return annotation;
    }

    public Edge[] getEdges(){
        Edge[] ret = (Edge[])edges.toArray();
        return ret;
    }

    public String toString(){
        //Provided function, do not alter!!!
        String res = annotation + ":\n";
        if(edges.toArray().length == 0)
            res += "\t" + "No out going edges" + "\n";
        else
            for(Object e: edges.toArray()){
                res += "\t" + e.toString() + "\n";
            } 
        return res;
    }
}
