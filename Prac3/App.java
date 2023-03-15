
public class App {
    public static void main(String[] args) {
        //test();
        //testPrint();
        //testHeight();
        //testContains();
        //testNumLeaves();
        testFind();
    }
    
    static void testFind(){
        StandardBinaryTree<Integer> sbt = new StandardBinaryTree<Integer>();
        System.out.println("find 5: " + sbt.find(5));
        sbt.insert(5);
        System.out.println("find 5: " + sbt.find(5));
        System.out.println("find 9: " + sbt.find(9));
        sbt.insert(3);
        sbt.insert(7);
        sbt.insert(1);
        sbt.insert(4);
        System.out.println("find 5: " + sbt.find(5));
        System.out.println("find 9: " + sbt.find(9));
        sbt.insert(6);
        sbt.insert(8);
        System.out.println("find 5: " + sbt.find(5));
        System.out.println("find 9: " + sbt.find(9));
    }
    
    static void testNumLeaves(){
        StandardBinaryTree<Integer> sbt = new StandardBinaryTree<Integer>();
        System.out.println("NumLeaves: " + sbt.numLeavesInTree());
        sbt.insert(5);
        System.out.println("NumLeaves: " + sbt.numLeavesInTree());
        sbt.insert(3);
        sbt.insert(7);
        sbt.insert(1);
        System.out.println("NumLeaves: " + sbt.numLeavesInTree());
        sbt.insert(4);
        sbt.insert(6);
        sbt.insert(8);
        System.out.println("NumLeaves: " + sbt.numLeavesInTree());
    }

    static void testContains(){
        StandardBinaryTree<Integer> sbt = new StandardBinaryTree<Integer>();
        System.out.println("Contains 5: " + sbt.contains(5));
        sbt.insert(5);
        System.out.println("Contains 5: " + sbt.contains(5));
        System.out.println("Contains 9: " + sbt.contains(9));
        sbt.insert(3);
        sbt.insert(7);
        sbt.insert(5);
        sbt.insert(1);
        sbt.insert(4);
        System.out.println("Contains 5: " + sbt.contains(5));
        System.out.println("Contains 9: " + sbt.contains(9));
        sbt.insert(6);
        sbt.insert(8);
        System.out.println("Contains 5: " + sbt.contains(5));
        System.out.println("Contains 9: " + sbt.contains(9));
    }

    static void testHeight(){
        StandardBinaryTree<Integer> sbt = new StandardBinaryTree<Integer>();
        System.out.println("Height: " + sbt.height());
        sbt.insert(5);
        System.out.println("Height: " + sbt.height());
        sbt.insert(3);
        System.out.println("Height: " + sbt.height());
        sbt.insert(7);
        sbt.insert(5);
        sbt.insert(1);
        sbt.insert(4);
        sbt.insert(6);
        sbt.insert(8);
        System.out.println("Height: " + sbt.height());
    }

    static void testPrint()
    {
        StandardBinaryTree<Integer> sbt = new StandardBinaryTree<Integer>();
        System.out.println("Empty: ");
        sbt.depthFirstTraversal();
        sbt.insert(5);
        System.out.println("0: ");
        sbt.depthFirstTraversal();
        sbt.insert(3);
        sbt.insert(7);
        System.out.println("012: ");
        sbt.depthFirstTraversal();
        sbt.insert(1);
        sbt.insert(4);
        sbt.insert(6);
        sbt.insert(8);
        System.out.println("01234: ");
        sbt.depthFirstTraversal();
    }

    public static void test() {
        BinaryTree<Integer> t = new StandardBinaryTree<>();
        
        t.insert(2);
        t.insert(1);
        t.insert(3);
        
        System.out.println("Tree Height");
        System.out.println(t.height() + "\n");
        System.out.println("Tree Number of Leaves");
        System.out.println(t.numLeavesInTree() + "\n");
        System.out.println("Depth First Traversal");
        t.depthFirstTraversal();
        System.out.println("\nFind parent of [3]");
        t.findParent(3);
        System.out.println("\nFind [1]");
        t.find(1);
        System.out.println("\nContains [5]");
        t.contains(5);
    }
}


/*
Tree Height
1

Tree Number of Leaves
3

Depth First Traversal
L[null]<-[1]->R[null]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]

Find parent of [3]
L[1]<-[2]->R[3]

Find [1]
L[1]<-[2]->R[3]
L[null]<-[1]->R[null]

Contains [5]
L[1]<-[2]->R[3]
L[null]<-[3]->R[null]
*/
