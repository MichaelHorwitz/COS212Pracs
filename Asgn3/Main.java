public class Main
{
    public static void main(String[] args)
    {

        //Tests for Edge and Node
        //testSingleNodeMultiEdge();
        //testEdgeNode();
        //testMultiEdgeMultiNode();

        //Test for Path Class
        //testPathConstruct();
        //testPathCopy();

        ////Tests for CFG Class
        //testCFG_Construct();
        //testReachability();
        //testValidity();
        testSplit();
        //testShortest();
        //testSimple();
        //testPrime();

    }

    public static void testSingleNodeMultiEdge()
    {
        System.out.println(
                "---------------------------------------------\nTesting Single Node with Multiple Edges\n---------------------------------------------");
        Node N0 = new Node("N0");
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");
        Node N9 = new Node("N9");

        N0.addEdge(N1, "E0", 1);
        N0.addEdge(N2, "E1", 1);
        N0.addEdge(N3, "E2", 1);
        N0.addEdge(N4, "E3", 1);
        N0.addEdge(N5, "E4", 1);
        N0.addEdge(N6, "E5", 1);
        N0.addEdge(N7, "E6", 1);
        N0.addEdge(N8, "E7", 1);
        N0.addEdge(N9, "E8", 1);

        System.out.println("Edges Array bounds and random point");
        Edge[] edges = N0.getEdges();

        System.out.println(edges[0]);
        System.out.println(edges[4]);
        System.out.println(edges[8]);
        System.out.println(edges[3].getAnnotation());

        System.out.println("\nEdges Representation \n" + N0);

        System.out.println();
    }

    public static void testEdgeNode()
    {
        System.out.println(
                "---------------------------------------------\nTesting Multi Nodes with Single Edge\n---------------------------------------------");
        Node N0 = new Node("N0");
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");

        N0.addEdge(N1, "E0", 1);
        N1.addEdge(N2, "E1", 1);
        N2.addEdge(N3, "E2", 1);
        N3.addEdge(N4, "E3", 1);
        N4.addEdge(N5, "E4", 1);

        System.out.println(N0);
        System.out.println(N1);
        System.out.println(N2);
        System.out.println(N3);
        System.out.println(N4);
        System.out.println(N5);
    }

    public static void testMultiEdgeMultiNode()
    {
        System.out.println(
                "---------------------------------------------\nTesting Multi Nodes with Multi Edges\n---------------------------------------------");

        Node N0 = new Node("N0");
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");

        N0.addEdge(N1, "E2", 1);
        N0.addEdge(N2, "E4", 1);
        N0.addEdge(N3, "E6", 1);

        N1.addEdge(N1, "E3", 1);
        N1.addEdge(N2, "E5", 1);
        N1.addEdge(N0, "E1", 1);

        N2.addEdge(N1, "E0", 1);
        N2.addEdge(N3, "E7", 1);

        System.out.println(N0);
        System.out.println(N1);
        System.out.println(N2);
        System.out.println(N3);
    }

    public static void testPathConstruct()
    {
        System.out.println(
                "---------------------------------------------\nTesting Path\n---------------------------------------------");

        Node N0 = new Node("N0");
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");

        Edge E0 = new Edge("E0", N1, 1);
        Edge E1 = new Edge("E1", N2, 1);
        Edge E2 = new Edge("E2", N3, 1);

        N0.addEdge(N1, "E0", 1);
        N1.addEdge(N2, "E1", 1);
        N2.addEdge(N3, "E2", 1);

        Node[] nodes =
        { N0, N1, N2, N3 };
        Edge[] edges =
        { E0, E1, E2 };

        Path journey = new Path(N1, N3, nodes, edges);
        System.out.println(journey);

        System.out.println("Valid Path:" + journey.validPath());
        System.out.println("Cost of Path:" + journey.computationalCostOfPath());
    }

    public static void testPathCopy()
    {
        System.out.println(
                "---------------------------------------------\nTesting Path Copy Constructor\n---------------------------------------------");

        Node N0 = new Node("N0");
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");

        Edge E0 = new Edge("E0", N1, 1);
        Edge E1 = new Edge("E1", N2, 1);
        Edge E2 = new Edge("E2", N3, 1);

        N0.addEdge(N1, "E0", 1);
        N1.addEdge(N2, "E1", 1);
        N2.addEdge(N3, "E2", 1);

        Node[] nodes =
        { N0, N1, N2, N3 };
        Edge[] edges =
        { E0, E1, E2 };

        Path journey = new Path(N1, N3, nodes, edges);
        Path getaway = new Path(journey);

        System.out.println(getaway);

        System.out.println("Valid Path:" + getaway.validPath());
        System.out.println("Cost of Path:" + getaway.computationalCostOfPath());
    }

    public static void testCFG_Construct()
    {
        System.out.println(
                "---------------------------------------------\nTesting CFG Constructors (3)\n---------------------------------------------");

        System.out.println(
                "---------------------------\n1.1) Testing Empty Default Constructor\n---------------------------");

        CFG blank = new CFG();
        printGraph(blank);

        System.out.println(
                "---------------------------\n1.2) Testing Populated Default Constructor\n---------------------------");
        CFG_subTest();

        System.out.println("---------------------------\n1.3) Testing Copy Constructor\n---------------------------");

        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node[] nodes =
        { N1, N2, N3, N4, N5 };

        Edge E1 = new Edge("E1", N2, 0);
        Edge E2 = new Edge("E2", N3, 0);
        Edge E3 = new Edge("E3", N4, 0);
        Edge E4 = new Edge("E4", N3, 0);
        Edge E5 = new Edge("E5", N5, 0);
        Edge[] edges =
        { E1, E2, E3, E4, E5 };

        N1.addEdge(N2, "E1", 0);
        N2.addEdge(N3, "E2", 0);
        N3.addEdge(N4, "E3", 0);
        N4.addEdge(N3, "E4", 0);
        N3.addEdge(N5, "E5", 0);

        Node[] exitNodes =
        { N5 };

        CFG cfg = new CFG(nodes, edges, N1, exitNodes);
        printGraph(cfg);

        System.out.println("---------------------------\n3) Testing Copy Constructor\n---------------------------");
        CFG copyCfg = new CFG(cfg);
        printGraph(copyCfg);
    }

    public static void CFG_subTest()
    {
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
    }

    public static void testReachability()
    {
        System.out.println(
                "---------------------------------------------\nTesting Reachability\n---------------------------------------------");

        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E10", cfg.getNode("N4"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);

        System.out.println("N5 reachable from N1: " + cfg.isReachable(N1, N5));
        System.out.println("N1 reachable from N5: " + cfg.isReachable(N5, N1));

        System.out.println("N8 reachable from N1: " + cfg.isReachable(N1, N8));
        System.out.println("N2 reachable from N6: " + cfg.isReachable(N6, N2));

        System.out.println("N7 reachable from N1: " + cfg.isReachable(N1, N7));
        System.out.println("N8 reachable from N6: " + cfg.isReachable(N6, N8));

        System.out.println("N5 reachable from N7: " + cfg.isReachable(N7, N5));
    }

    public static void testValidity()
    {
        System.out.println(
                "---------------------------------------------\nTesting Validity(4)\n---------------------------------------------");
        System.out.println("---------------------------\n1.1) Testing 1\n---------------------------");
        valid_1();

        System.out.println("---------------------------\n1.2) Testing 2\n---------------------------");
        valid_2();

        System.out.println("---------------------------\n1.3) Testing 3\n---------------------------");
        valid_3();

        System.out.println("---------------------------\n1.4) Testing 4\n---------------------------");
        valid_4();
    }

    public static void valid_1()
    {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E10", cfg.getNode("N4"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);

        System.out.println("Valid CFG: " + cfg.isValid());
        System.out.println("SESE Graph: " + cfg.isSESE());
    }

    public static void valid_2()
    {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addNode(N8);
        cfg.addExitNode(N5);
        //cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);

        System.out.println("Valid CFG: " + cfg.isValid());
        System.out.println("SESE Graph: " + cfg.isSESE());
    }

    public static void valid_3()
    {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);

        System.out.println("Valid CFG: " + cfg.isValid());
        System.out.println("SESE Graph: " + cfg.isSESE());
    }

    public static void valid_4()
    {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addNode(N8);
        cfg.addExitNode(N5);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);
        cfg.addEdge("E12", cfg.getNode("N8"), cfg.getNode("N7"), 0);

        System.out.println("Valid CFG: " + cfg.isValid());
        System.out.println("SESE Graph: " + cfg.isSESE());
    }

    public static void testSplit()
    {
        System.out.println(
                "---------------------------------------------\nTesting Split(2)\n---------------------------------------------");
        System.out.println("---------------------------\n1.1) Testing 1\n---------------------------");
        split_1();

        System.out.println("---------------------------\n1.2) Testing 2\n---------------------------");
        split_2();
    }

    public static void split_1()
    {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addNode(N8);
        cfg.addExitNode(N5);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);
        cfg.addEdge("E12", cfg.getNode("N8"), cfg.getNode("N7"), 0);

        CFG [] splits= cfg.splitGraph();
        for(CFG s: splits)
        {
            System.out.println(s);
        }
    }

    public static void split_2()
    {
        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 0);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 0);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 0);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 0);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 0);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 0);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 0);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 0);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 0);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 0);

        CFG [] splits= cfg.splitGraph();
        for(CFG s: splits)
        {
            System.out.println(s);
            System.out.println("--------------------------------------------------------------------------");
        }
    }
    
    public static void testShortest()
    {
        System.out.println(
                "---------------------------------------------\nTesting Shortest Paths\n---------------------------------------------");

        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 5);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 2);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 3);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 6);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 7);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 13);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 7);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 8);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 2);
        cfg.addEdge("E10", cfg.getNode("N4"), cfg.getNode("N8"), 1);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 2);

        System.out.println("---------Shortest path to N5 from N1---------");
        System.out.println(cfg.shortestCompTimePath(N1, N5));

        System.out.println("---------Shortest path to N4 from N3---------");
        System.out.println(cfg.shortestCompTimePath(N3, N4));

        System.out.println("---------Shortest path to N5 from N6---------");
        System.out.println(cfg.shortestCompTimePath(N6, N5));

        System.out.println("---------Shortest path to N3 from N4---------");
        System.out.println(cfg.shortestCompTimePath(N4, N3));
    }

    public static void testSimple()
    {
        System.out.println(
                "---------------------------------------------\nTesting Simple Paths\n---------------------------------------------");

        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 5);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 2);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 3);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 6);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 7);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 13);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 7);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 8);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 2);
        cfg.addEdge("E10", cfg.getNode("N4"), cfg.getNode("N8"), 1);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 2);

        Path[] simplePaths = cfg.getSimplePaths();
        for (Path p : simplePaths)
        {
            System.out.println(p.toString());
        }
    }

    public static void testPrime()
    {
        System.out.println(
                "---------------------------------------------\nTesting Prime Paths\n---------------------------------------------");

        Node N1 = new Node("N1");
        Node N2 = new Node("N2");
        Node N3 = new Node("N3");
        Node N4 = new Node("N4");
        Node N5 = new Node("N5");
        Node N6 = new Node("N6");
        Node N7 = new Node("N7");
        Node N8 = new Node("N8");

        CFG cfg = new CFG();
        cfg.addStartNode(N1);
        cfg.addNode(N2);
        cfg.addNode(N3);
        cfg.addNode(N4);
        cfg.addNode(N6);
        cfg.addNode(N7);
        cfg.addExitNode(N5);
        cfg.addExitNode(N8);
        cfg.addEdge("E1", cfg.getNode("N1"), cfg.getNode("N2"), 5);
        cfg.addEdge("E2", cfg.getNode("N2"), cfg.getNode("N3"), 2);
        cfg.addEdge("E3", cfg.getNode("N3"), cfg.getNode("N4"), 3);
        cfg.addEdge("E4", cfg.getNode("N4"), cfg.getNode("N3"), 6);
        cfg.addEdge("E5", cfg.getNode("N3"), cfg.getNode("N5"), 7);
        cfg.addEdge("E6", cfg.getNode("N6"), cfg.getNode("N5"), 13);
        cfg.addEdge("E7", cfg.getNode("N6"), cfg.getNode("N7"), 7);
        cfg.addEdge("E8", cfg.getNode("N7"), cfg.getNode("N6"), 8);
        cfg.addEdge("E9", cfg.getNode("N7"), cfg.getNode("N8"), 2);
        cfg.addEdge("E10", cfg.getNode("N4"), cfg.getNode("N8"), 1);
        cfg.addEdge("E11", cfg.getNode("N6"), cfg.getNode("N2"), 2);

        Path[] primePaths = cfg.getPrimePaths();
        for (Path p : primePaths)
        {
            System.out.println(p.toString());
        }
    }

    public static void printGraph(CFG graph)
    {
        if (graph == null)
        {
            System.out.println("NULL Graph");
        } else
        {
            System.out.println(graph.toString());
        }
    }
}