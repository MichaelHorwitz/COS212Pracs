public class Main {
    public static void main(String[] args) {
        testConst();
        //givenTest();
    }
    static void testConst(){
        Graph g = new Graph("graph.txt;");
        System.out.println(g);
        
    }
    static void givenTest(){
        Graph g = new Graph("graph.txt");
        System.out.println(g);
        System.out.println("DFS:" + g.depthFirstTraversal());
        System.out.println("BFS:" + g.breadthFirstTraversal());
        System.out.println("Strongly Connected:\n" + g.stronglyConnectedComponents());
    }
}