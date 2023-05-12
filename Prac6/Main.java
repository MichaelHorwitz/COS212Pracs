public class Main {
    public static void main(String[] args) {
        //testConst();
        //testInsVec();
        //testInsEdge();
        //testRemVec();
        //givenTest();
        testDFT();
    }
    static void testDFT(){
        Graph g = new Graph("Prac6\\graph.txt");
        System.out.println(g);
        System.out.println(g.depthFirstTraversal());
        g = new Graph("");
        System.out.println(g.depthFirstTraversal());
    }
    static void testRemVec(){
        Graph g = new Graph("Prac6\\graph.txt");
        System.out.println(g);

        System.out.println("Remove L");
        g.removeVertex("L");
        System.out.println(g);
        
        System.out.println("Remove A");
        g.removeVertex("A");
        System.out.println(g);
        
        System.out.println("Remove R");
        g.removeVertex("R");
        System.out.println(g);
        
        System.out.println("Remove #");
        g.removeVertex("#");
        System.out.println(g);
    }
    static void testInsEdge(){
        Graph g = new Graph("Prac6\\graph.txt");
        System.out.println(g);
        g.insertEdge("J", "A", 5);
        System.out.println(g);
        g.insertEdge("A", "A", 5);
        System.out.println(g);
        g.insertEdge("#", "A", 5);
        System.out.println(g);
        g.insertEdge("J", "A", 0);
        System.out.println(g);
    }
    static void testInsVec(){
        Graph g = new Graph("Prac6\\graph.txt");
        System.out.println(g);
        String str = "Z";
        g.insertVertex(str);
        System.out.println(g);
        g = new Graph("");
        System.out.println(g);
        g.insertVertex(str);
        System.out.println(g);
        g.insertVertex("B");
        System.out.println(g);
    }
    static void testConst(){
        Graph g = new Graph("Prac6\\graph.txt");
        System.out.println(g);
        g = new Graph("");
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