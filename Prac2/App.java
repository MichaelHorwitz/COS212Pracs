public class App {
    public static void main(String[] args) throws Exception {
        //testCountListBlank();
        //testCLAccess();
        //testMTFAccess();
        //testNOAccess();
        //testTLAccess();
        //testITstr();
        //testIT();
        testRecStr();
        
    }
    
    static void testRecStr(){
        CountList<Integer> cl = new CountList<Integer>();
        cl.insert(0);
        cl.insert(1);
        cl.insert(2);
        cl.insert(3);
        cl.insert(4);
        IterativeTraverse<Integer> rt = new IterativeTraverse<Integer>(cl);
        System.out.println(rt);
    }

    static void testIT(){
        CountList<Integer> cl = new CountList<Integer>();
        cl.insert(0);
        cl.insert(1);
        cl.insert(2);
        cl.insert(3);
        cl.insert(4);
        IterativeTraverse<Integer> it = new IterativeTraverse<Integer>(cl);
        System.out.println(it);
        System.out.println("Contains 0: " + it.contains(0));
        System.out.println("Contains 2: " + it.contains(2));
        System.out.println("Contains 4: " + it.contains(4));
        System.out.println("Contains 8: " + it.contains(8));

        System.out.println("Size: " + it.size());
        System.out.println("Reversed:\n" + it.reverseList());

        System.out.println("Find 0: " + it.find(0));
        System.out.println("Find 2: " + it.find(2));
        System.out.println("Find 4: " + it.find(4));
        System.out.println("Find 8: " + it.find(8));

        System.out.println("Get 0: " + it.get(0));
        System.out.println("Get 2: " + it.get(2));
        System.out.println("Get 4: " + it.get(4));
        System.out.println("Get 8: " + it.get(8));

        System.out.println(it.clone(cl));
    }

    static void testITstr(){
        CountList<Integer> cl = new CountList<Integer>();
        IterativeTraverse it = new IterativeTraverse<>(cl);
        System.out.println(it);
    }

    static void testTLAccess(){
        TransposeList<Integer> tl = new TransposeList<Integer>();
        tl.insert(0);
        tl.insert(1);
        tl.insert(2);
        tl.insert(3);
        tl.insert(4);
        System.out.println(tl + "\n");
        System.out.println("Access 2");
        tl.access(2);
        System.out.println(tl);
        System.out.println("Access 0");
        tl.access(0);
        System.out.println(tl);
        System.out.println("Access 2");
        tl.access(2);
        System.out.println(tl);
        System.out.println("Access 3");
        tl.access(3);
        System.out.println(tl);
        System.out.println("Access 1");
        tl.access(1);
        System.out.println(tl);
    }

    static void testNOAccess(){
        NaturalOrderList<Integer> no = new NaturalOrderList<Integer>();
        no.insert(4);
        System.out.println(no);
        System.out.println("**********************************");
        no.insert(2);
        System.out.println(no);
        System.out.println("**********************************");
        no.insert(3);
        System.out.println(no);
        System.out.println("**********************************");
        no.insert(3);
        System.out.println(no);
        System.out.println("**********************************");
        no.insert(1);
        System.out.println(no);
        System.out.println("**********************************");
    }
    static void testCountListBlank(){
        CountList<Integer> cl = new CountList<Integer>();
        cl.insert(1);
        cl.insert(2);
        cl.insert(3);
        cl.insert(4);
        System.out.println(cl);
    }
    static void testCLAccess(){
        CountList<Integer> cl = new CountList<Integer>();
        cl.insert(0);
        cl.insert(1);
        cl.insert(2);
        cl.insert(3);
        cl.insert(4);
        System.out.println(cl); 
        cl.access(2);    
        System.out.println(cl);
        cl.access(4);    
        System.out.println(cl);
        cl.access(2);    
        System.out.println(cl);
        cl.access(1);    
        System.out.println(cl);
    }
    static void testMTFAccess(){
        MoveToFrontList<Integer> mtf = new MoveToFrontList<Integer>();
        mtf.insert(0);
        mtf.insert(1);
        mtf.insert(2);
        mtf.insert(3);
        mtf.insert(4);
        System.out.println(mtf); System.out.println("*********************");
        mtf.access(2);    
        System.out.println(mtf); System.out.println("*********************");
        mtf.access(4);    
        System.out.println(mtf); System.out.println("*********************");
        mtf.access(2);    
        System.out.println(mtf); System.out.println("*********************");
        mtf.access(1);    
        System.out.println(mtf); System.out.println("*********************");
    }
}
