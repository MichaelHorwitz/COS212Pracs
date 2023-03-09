public class App {
    public static void main(String[] args) throws Exception {
        //testCountListBlank();
        //testCLAccess();
        //testMTFAccess();
        testNOAccess();
        
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
