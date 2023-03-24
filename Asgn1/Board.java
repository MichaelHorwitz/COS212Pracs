import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class Board {
    private int numRows, numCols;
    private Cell cells[], rows[], cols[], blocks[];

    public String toString() {
        String res = "";
        for (int r = 0; r < numRows * numCols; r++) {
            if (r % numRows == 0) {
                res += horizLine() + "\n";
            }
            for (int c = 0; c < numRows * numCols; c++) {
                if (c % numCols == 0) {
                    res += "|";
                }
                res += cells[r * numRows * numCols + c];
            }
            res += "|\n";
        }

        res += horizLine();
        return res;
    }

    public String horizLine() {
        String res = "";
        for (int i = 0; i < numRows + 1 + (numRows * numCols * (String.valueOf(numRows * numCols).length() + 2)); i++) {
            res += "-";
        }
        return res;
    }

    public void testLinks() {
        System.out.println("Rows forward");

        for (int r = 0; r < numRows * numCols; r++) {
            System.out.print("Row " + r + "\t");
            Cell ptr = rows[r];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.right;
            }
            System.out.println();
        }

        System.out.println("Cols forward");

        for (int c = 0; c < numRows * numCols; c++) {
            System.out.print("Col " + c + "\t");
            Cell ptr = cols[c];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.below;
            }
            System.out.println();
        }

        System.out.println("Blocks");
        for (int b = 0; b < numRows * numCols; b++) {
            System.out.print("Block " + b + "\t");
            Cell ptr = blocks[b];
            while (ptr != null) {
                System.out.print(indexOf(ptr) + " ");
                ptr = ptr.block;
            }
            System.out.println();
        }
    }

    public void testCells() {
        System.out.println("Cell\tCoord\tBlock\ttoString");
        for (Cell c : cells) {
            System.out.println(indexOf(c) + "\t(" + c.r + "," + c.c + ")\t" + c.b + "\t" + c);
        }
    }

    public int indexOf(Cell c) {
        for (int i = 0; i < numRows * numRows * numCols * numCols; i++) {
            if (cells[i].equals(c)) {
                return i;
            }
        }
        return -1;
    }

    public Cell cellAt(int r, int c) {
        if (r < 0 || r >= numRows * numCols || c < 0 || c >= numRows * numCols) {
            return null;
        }
        return cells[r * numRows * numCols + c];
    }

    /*
     * Don't change anything above this line
     */

    public Board(int r, int c, String boardString) {
        numRows = r;
        numCols = c;
        cells = new Cell[(numRows * numCols) * numRows * numCols];
        String currStr = "";
        int cellSize = 0;
        //System.out.println("boardString.length(): " + boardString.length());
        for (int i = 0; i < boardString.length(); i++) {
            //System.out.println("i: " + i);
            if (boardString.charAt(i) == ' ') {
                //System.out.println("currStr: " + currStr);
                
                cells[cellSize] = new Cell(numRows, numCols, currStr);
                currStr = "";
                cellSize++;
            } else {
                currStr += boardString.charAt(i);
            }
        }
        //System.out.println("currStr: " + currStr);
        cells[cellSize] = new Cell(numRows, numCols, currStr);
        cellSize++;
        this.setLinks();
    }

    public void setLinks() {
        int numCells = numRows * numCols * numRows * numCols;
        rows = new Cell[numRows * numCols];
        cols = new Cell[numRows * numCols];
        blocks = new Cell[numRows * numCols];
        //Set for rows
        for (int i = 0; i < numCells; i++) {
            int currRow = i/(numRows*numCols);
            if (i%(numRows*numCols) == 0) {
                rows[currRow] = cells[i];
                rows[currRow].r = currRow;
            } else {
                cells[i].r = currRow;
                cells[i - 1].right = cells[i];
            }
        }
        
        //Set for cols
        Cell [] currCells = new Cell[numCols * numRows];

        //Set cols array
        for (int i = 0;i < numRows * numCols; i++) {
            cols[i] = cells[i];
            currCells[i] = cols[i];
            cols[i].c = i;
        }
        //Set links in cols
        for (int i = numRows * numCols; i < numCells; i++) {
            int currPos = i % (numRows * numCols);
            currCells[currPos].c = currPos;
            currCells[currPos].below = cells[i];
            currCells[currPos] = cells[i];
        }  
        //set for blocks
        int i = 0;
        int currBlock = 0;
        //set blocks arr
        while (i < numCells) {
            for (int j = 0; j < numRows; j++) {
                blocks[currBlock] = cells[i];
                cells[i].b = currBlock;
                currBlock++;
                i += numCols;
            }
            for (int j = 0; j < numRows - 1; j++) {
                i += numRows * numCols;
            }
        }
        //set links in each block
        for (int j = 0; j < numRows * numCols; j++) {
            Cell prevCell = null;
            Cell currCell = blocks[j];
            Cell firstCell = blocks[j];
            for (int l = 0; l < numRows; l++) {
                for (int k = 0; k < numCols; k++) {
                    if (prevCell != null) {
                        prevCell.block = currCell;
                    }
                    currCell.b = j;
                    prevCell = currCell;
                    currCell = currCell.right;
                }
                prevCell.block = currCell;
                currCell = firstCell.below;
                firstCell = firstCell.below;
            }
            prevCell.block = null;
        }
    }
    
    public void fullProp() {
        for (int i = 0; i < numRows * numCols * numRows * numCols; i++) {
            propCell(cells[i]);
        }
    }

    public void propCell(Cell cell) {
        if (cell.value == null) {
            return;
        }
        Cell currCell = rows[cell.r];
        while (currCell != null) {
            currCell.removeVal(cell.value);
            currCell = currCell.right;
        }
        currCell = cols[cell.c];
        while (currCell != null) {
            currCell.removeVal(cell.value);
            currCell = currCell.below;
        }
        currCell = blocks[cell.b];
        while (currCell != null) {
            currCell.removeVal(cell.value);
            currCell = currCell.block;
        }
    }

    public void solve() {
        int counter = 0;
        while (soleCandidate() || uniqueCandidate() || duplicateCells()) {
            counter++;
        }
        System.out.println("Number of moves: " + counter);
    }

    public boolean soleCandidate() {
        //for ( Loop through rows with a loop variable called row ) { 
        for (Cell row: rows) {
            //Create a 1 D int array of size { numRows * numCols } and call it counts 
            int [] counts = new int[numRows * numCols];
            //Create a Cell variable called rowPtr and set it equal to row 
            Cell rowPtr = row;
            //while ( rowPtr is not null ) { 
            while (rowPtr != null) {
                //if( rowPtr has possibleValues ) {
                if (rowPtr.possibleValues != null && rowPtr.possibleValues.head != null) {
                    //Create a Node < Integer > variable called nodePtr and set it equal to the first node in the rowPtr possibleValues
                    Node<Integer> nodePtr = rowPtr.possibleValues.head;
                    //while ( nodePtr is not null ) { 
                    while (nodePtr != null) {
                        //increment counts [ nodePtr . data - 1]
                        counts[nodePtr.data - 1]++;
                        //update nodePtr to the next value
                        nodePtr = nodePtr.next;
                    //}
                    }
                //}
                }
                //update rowPtr to the next value
                rowPtr = rowPtr.right;
            //}
            }
            //for ( i in range [0 , numRows * numCols ]) {
            for (int i = 0; i < numRows * numCols; i++) {
                //if( counts [ i ] == 1) {
                if (counts[i] == 1) {
                    //rowPtr = row
                    rowPtr = row;                    
                    //while ( rowPtr is not null ) {
                    while (rowPtr != null) {
                        //if( rowPtr has possibleValues and contains { i + 1}) {
                        if (rowPtr.possibleValues != null && rowPtr.possibleValues.contains(i + 1)) {
                            //Call setVal on rowPtr with { i + 1 }
                            rowPtr.setVal(i + 1);
                            //Call propCell with rowPtr
                            propCell(rowPtr);
                        //}
                        }
                        //update rowPtr to the next val
                        rowPtr = rowPtr.right;
                    //}
                    }
                    //return true
                    return true;
                //}
                }
            //}
            }
        //}
        }
        //Repeat the above for cols 29
        //Repeat the above for blocks 30
        //return false
        return false;
    }

    public boolean uniqueCandidate() {
        return false;
    }

    public boolean duplicateCells() {
        return false;
    }
}