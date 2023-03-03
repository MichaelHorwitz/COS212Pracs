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
        testSLStr();
        testSLIns();

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
    
    static void testSLStr(){
        SkipList<Integer> sl = new SkipList<>(3);
        sl.insert(1);
        System.out.println(sl.toString());
        sl.insert(2);
        System.out.println(sl.toString());
    }

    static void testSLIns(){

    }
}
