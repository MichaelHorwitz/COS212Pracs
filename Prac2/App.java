import javax.print.DocFlavor.STRING;

public class App {
    public static void main(String[] args) throws Exception {
        //testCountListBlank();
        //testCLAccess();
        //testMTFAccess();
        //testNOAccess();
        //testTLAccess();
        //testITstr();
        testIT();
        //testRecStr();
        //testRec();
        
    }
    
    static void testRec(){
        TransposeList<Integer> cl = new TransposeList<Integer>();
        cl.insert(0);
        RecursiveTraverse<Integer> rec = new RecursiveTraverse<Integer>(cl);
        System.out.println(rec);
        System.out.println("Contains 0: " + rec.contains(0));
        System.out.println("Contains 2: " + rec.contains(2));
        System.out.println("Contains 4: " + rec.contains(4));
        System.out.println("Contains 8: " + rec.contains(8));
        
        System.out.println("Size: " + rec.size());
        System.out.println("Reversed:\n" + rec.reverseList());

        System.out.println("Find 0: " + rec.find(0));
        System.out.println("Find 2: " + rec.find(2));
        System.out.println("Find 4: " + rec.find(4));
        System.out.println("Find 8: " + rec.find(8));

        System.out.println("Get 0: " + rec.get(0));
        System.out.println("Get 2: " + rec.get(2));
        System.out.println("Get 4: " + rec.get(4));
        System.out.println("Get 8: " + rec.get(8));

        System.out.println("Clone:\n" + rec.clone(cl));
        System.out.println("*********************************************************");
    }
    static void testRecStr(){
        CountList<Integer> cl = new CountList<Integer>();
        cl.insert(0);
        cl.insert(1);
        cl.insert(2);
        cl.insert(3);
        cl.insert(4);
        RecursiveTraverse<Integer> rt = new RecursiveTraverse<Integer>(cl);
        System.out.println(rt);
    }
    static void testIT(){
        CountList<String> cl = new CountList<String>();
        cl.insert("A");
        cl.insert("B");
        cl.insert("C");
        cl.insert("D");
        cl.insert("E");
        IterativeTraverse<String> it = new IterativeTraverse<String>(cl);
        System.out.println(it);
        System.out.println("Contains A: " + it.contains("A"));
        System.out.println("Contains C: " + it.contains("C"));
        System.out.println("Contains E: " + it.contains("E"));
        System.out.println("Contains D: " + it.contains("F"));

        System.out.println("Size: " + it.size());
        System.out.println("Reversed:\n" + it.reverseList());

        System.out.println("Find A: " + it.find("A"));
        System.out.println("Find C: " + it.find("C"));
        System.out.println("Find E: " + it.find("E"));
        System.out.println("Find F: " + it.find("F"));

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
