public class Edge {
    private final String annotation;
    private final Node nextNode;
    private final int computationalTime;

    public Edge(String annot, Node nextNode, int compTime){
        annotation = annot;
        this.nextNode = nextNode;
        computationalTime = compTime;
    }
    public boolean equals(Edge data){
        if (this.annotation.equals(data.annotation)) {
            return true;
        }
        return false;
    }
    public Node getNext(){
        return nextNode;
    }

    public String getAnnotation(){
        return annotation;
    }
    public int getCompTime(){
        return computationalTime;
    }

    public String toString(){
        //Provided function, do not alter!!!
        String res = "-> " + annotation + " -[" + computationalTime + "]-"; 
        
        if(nextNode == null)
            res += "> NULL";
        else
            res += "> " + nextNode.getAnnotation();

        return res;
    }
    public static Edge[] objToEdgeArr(Object[] objArr){
        Edge[] ret = new Edge[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            ret[i] = (Edge)objArr[i];
        }
        return ret;
    }
}
