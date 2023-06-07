public class App {
    public static void main(String[] args) {
        // SpecExample1();
        testMyDSInsertRemove();
    }

    public static void testMyDSInsertRemove() {
        myDS<Integer> mds = new myDS<Integer>();
        Integer[] toAdd = { 2, 3, 5, 4, 7, 1 };
        System.out.println("TESTING INSERT");
        for (Integer integer : toAdd) {
            mds.insert(integer);
            System.out.println(mds);
        }
        System.out.println("TESTING TOARRAY");
        Comparable<Integer>[] minArr = mds.toArray();
        for (Comparable<Integer> comparable : minArr) {
            System.out.print(comparable + ", ");
        }
        System.out.println();
        System.out.println("TESTING REMOVE");
        Integer[] toRem = {3,2,21,4,7,5,1};
        for (Integer integer : toRem) {
            mds.remove(integer);
            System.out.println(mds);
        }

    }

    public static void SpecExample1() {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addExitNode(N5);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        printGraph(cfg);
        Path[] simplePaths = cfg.getSimplePaths();
        for (Path p : simplePaths) {
            System.out.println(p.toString());
        }
        printBreaker();
        Path[] primePaths = cfg.getPrimePaths();
        for (Path p : primePaths) {
            System.out.println(p.toString());
        }
    }

    public static void printGraph(CFG graph) {
        printBreaker();
        if (graph == null) {
            System.out.println("NULL Graph");
        } else {
            System.out.println(graph.toString());
        }
        printBreaker();
    }

    public static void printBreaker() {
        System.out.println("###");
    }

    /*
     * Expected output
     * ###
     * N1:
     * -> E1 -[0]-> N2
     * N2:
     * -> E2 -[0]-> N3
     * N3:
     * -> E3 -[0]-> N4
     * -> E5 -[0]-> N5
     * N4:
     * -> E4 -[0]-> N3
     * N5:
     * No out going edges
     * 
     * ###
     * N1
     * N2
     * N3
     * N4
     * N5
     * N1-> E1 -[0]-> N2
     * N2-> E2 -[0]-> N3
     * N3-> E3 -[0]-> N4
     * N3-> E5 -[0]-> N5
     * N4-> E4 -[0]-> N3
     * N1-> E1 -[0]-> N2-> E2 -[0]-> N3
     * N2-> E2 -[0]-> N3-> E3 -[0]-> N4
     * N2-> E2 -[0]-> N3-> E5 -[0]-> N5
     * N3-> E3 -[0]-> N4-> E4 -[0]-> N3
     * N4-> E4 -[0]-> N3-> E3 -[0]-> N4
     * N4-> E4 -[0]-> N3-> E5 -[0]-> N5
     * N1-> E1 -[0]-> N2-> E2 -[0]-> N3-> E3 -[0]-> N4
     * N1-> E1 -[0]-> N2-> E2 -[0]-> N3-> E5 -[0]-> N5
     * ###
     * N3-> E3 -[0]-> N4-> E4 -[0]-> N3
     * N4-> E4 -[0]-> N3-> E3 -[0]-> N4
     * N4-> E4 -[0]-> N3-> E5 -[0]-> N5
     * N1-> E1 -[0]-> N2-> E2 -[0]-> N3-> E3 -[0]-> N4
     * N1-> E1 -[0]-> N2-> E2 -[0]-> N3-> E5 -[0]-> N5
     */

}
