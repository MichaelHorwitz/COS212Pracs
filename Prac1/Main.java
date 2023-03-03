public class Main {
    public static void main(String[] args) {
        /*SkipList<Integer> myList = new SkipList<>(3);
        for (int i = 0; i < 10; i++) {
            myList.insert(i);
        }

        System.out.println(myList);
        System.out.print("Searching for 8\t");
        myList.printSearchPath(8);
        System.out.print("Searching for 2\t");
        myList.printSearchPath(2);*/

        //testSLNConst();
        //testSLConst();
        //testSLEmpty();
        //testSLIns();
        testSLSer();

    }

    static void testSLNConst(){
        SkipListNode<Integer> sln = new SkipListNode<Integer>(2, 1);
        System.out.println(sln.emptyString());

    }

    static void testSLConst(){
        SkipList<Integer> sl = new SkipList<>(5);
        for (int i = 0; i < 10; i++) {
            
            sl.chooseLevel();
        }
    }
    
    static void testSLEmpty(){
        SkipList<Integer> sl = new SkipList<>(3);
        if (sl.isEmpty()) {
            System.out.println("Empty");
        }
        
    }
    
    static void testSLIns(){
        SkipList<Integer> sl = new SkipList<>(3);
        sl.insert(1);
        System.out.println(sl.toString());
        sl.insert(2);
        System.out.println(sl.toString());
        sl.insert(4);
        System.out.println(sl.toString());
        sl.insert(3);
        System.out.println(sl.toString());
        sl.insert(0);
        System.out.println(sl.toString());
        sl.insert(0);
        System.out.println(sl.toString());
    }

    static void testSLSer(){
        SkipList<Integer> sl = new SkipList<>(3);
        sl.insert(1);
        sl.insert(2);
        sl.insert(4);
        sl.insert(3);
        sl.insert(0);
        sl.insert(0); 
        sl.insert(4); 
        sl.insert(4); 
        sl.insert(4); 
        sl.insert(4); 
        sl.insert(4); 
        System.out.println(sl.toString());
        System.out.println(sl.search(4).toString());
        
    }
}
