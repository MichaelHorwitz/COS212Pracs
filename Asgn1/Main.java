public class Main {
    /*
     * Tips for using this Main
     * 
     * The output is in out.txt
     * 
     * Comment the tasks you are not currently testing
     * 
     * Look up "Text Comparison" for your IDE of choice. This way you can save your
     * output to a txt file and use that to see if there are differences between
     * your output and my output.
     * 
     * THESE TESTS ARE TRIVIAL. Make sure you expand and do more testing
     */
    public static void main(String[] args) {
        // task1();
        // task2();
        // task3();
        //testListAppend();
        //testListRemove();
        //testListRemovelist();
        //testListContains();
        //testListEquals();
        //testCell();
        testRow();
    }
    
    public static void testRow(){
        Board bd = new Board(2, 3, "4 6 2 5 3 1 3 1 5 6 2 4 6 4 3 1 5 2 2 5 1 4 6 3 5 3 4 2 1 6 1 2 6 3 4 5");
        //bd.setLinks();
    }
    
    public static void testCell(){
        Cell cell = new Cell(4, 4, "1");
        System.out.println("Cell: " + cell);

        System.out.println("Remove 2: ");
        cell.removeVal(1);
        cell.removeVal(-1);
        cell.setVal(14);
    }
    
    public static void testListEquals(){
        List<String> li = new List<String>();
        String [] strArr = {"A", "B", "B", "C", "D"};
        for (String currStr : strArr) {
            li.append(currStr);
        }
        System.out.println("li:\t" + li);
        List<String> other = new List<String>();
        String [] othArr = {"A", "B", "B", "C", "D"};
        for (String currStr : othArr) {
            other.append(currStr);
        }
        System.out.println("other:\t" + other);
        System.out.println("Equals? " + li.equals(other));
        
        other = new List<String>();
        String [] temp = {"A", "B", "D"};
        othArr = temp;
        for (String currStr : othArr) {
            other.append(currStr);
        }
        System.out.println("other:\t" + other);
        System.out.println("Equals? " + li.equals(other));

        other = new List<String>();
        othArr = new String[0];
        for (String currStr : othArr) {
            other.append(currStr);
        }
        System.out.println("other:\t" + other);
        System.out.println("Equals? " + li.equals(other));
        
        System.out.println("null");
        System.out.println("Equals? " + li.equals(null));
    }
    
    public static void testListContains(){
        List<String> li = new List<String>();
        System.out.println("Search empty: ");
        System.out.println(li.contains("A"));
        String [] strArr = {"A", "B", "B", "C", "D"};
        for (String currStr : strArr) {
            li.append(currStr);
        }
        System.out.println(li);
        String [] conArr = {"B", "B", "C", "Z", "D", "A", "A"};
        for (String i : conArr) {
            System.out.println("Searching for " + i);
            System.out.println(li.contains(i));
        }



    }
    
    public static void testListRemovelist(){
        List<String> li = new List<String>();
        String [] strArr = {"A", "B", "B", "C", "D"};
        for (String currStr : strArr) {
            li.append(currStr);
        }
        System.out.println(li);
        String [] delArr = {"B", "B", "C", "Z", "D", "A", "A"};
        List<String> delLi = new List<String>();
        for (String i : delArr) {
            delLi.append(i);
        }
        li.remove(delLi);
        System.out.println("New List: " + li);

    }
    
    public static void testListRemove(){
        List<String> li = new List<String>();
        String [] strArr = {"A", "B", "B", "C", "D"};
        for (String currStr : strArr) {
            li.append(currStr);
        }
        System.out.println(li);
        String [] delArr = {"C", "D", "Z", "B", "B", "A", "A"};
        for (String delStr : delArr) {
            System.out.println("remove: " + delStr);
            System.out.println(li.remove(delStr));
            System.out.println(li);
        }
    }
    
    public static void testListAppend(){
        List<String> li = new List<String>();
        System.out.println(li);

        li.append("A");
        System.out.println(li);
        
        li.append("A");
        System.out.println(li);
        
        li.append("B");
        System.out.println(li);
        
        li.append("D");
        System.out.println(li);

    }

    public static void task1() {
        System.out.println("==========\nTask1\n==========");
        List<Integer> l = new List<>();

        for (int i = 1; i <= 9; i++) {
            l.append(i);
        }

        System.out.println(l.length + "\t" + l);
        l.remove(2);
        System.out.println(l.length + "\t" + l);
        l.remove(1);
        System.out.println(l.length + "\t" + l);
        l.remove(9);
        System.out.println(l.length + "\t" + l);
        l.remove(4);
        System.out.println(l.length + "\t" + l);
    }

    public static void task2() {
        System.out.println("==========\nTask2\n==========");
        SudokuSolver s1 = new SudokuSolver("2x3.txt");
        System.out.println("### Print Board ###");
        System.out.println(s1.board());
        System.out.println("### testLinks ###");
        s1.board().testLinks();
        System.out.println("### testCells ###");
        s1.board().testCells();
    }

    public static void task3() {
        System.out.println("==========\nTask3\n==========");
        SudokuSolver s1 = new SudokuSolver("2x3.txt");
        s1.solveBoard();
    }
}
