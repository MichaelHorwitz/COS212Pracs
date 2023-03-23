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
        for (int i = 0; i < numCells; i++) {
            //System.out.println("i: " + i);
            //System.out.println("cells[i]: " + cells[i]);
            //System.out.println("currRow: " + i/(numRows*numCols));
            int currRow = i/(numRows*numCols);
            if (i%(numRows*numCols) == 0) {
                rows[currRow] = cells[i];
            } else {
                cells[i - 1].right = cells[i];
            }
        }
        /* 
         System.out.println("numRows: " + numRows);
         for (int i = 0; i < numRows * numCols; i++) {
             //System.out.println("i: " + i);
             Cell currCell = rows[i];
             while (currCell != null) {
                 System.out.print(currCell + " ");
                 currCell = currCell.right;
                }
            System.out.println();
        }
        */
        
        Cell [] currCells = new Cell[numCols * numRows];

        for (int i = 0;i < numRows * numCols; i++) {
            cols[i] = cells[i];
            currCells[i] = cols[i];
        }
        for (int i = numRows * numCols; i < numCells; i++) {
            int currPos = i % (numRows * numCols);
            currCells[currPos].below = cells[i];
            currCells[currPos] = cells[i];
        }

                /*
        for (int i = 0; i < numRows * numCols; i++) {
            Cell currCell = cols[i];
            while (currCell != null) {
                System.out.print(currCell + " ");
                currCell = currCell.below;
            }
            System.out.println();
        }
        */
        
        int i = 0;
        int currBlock = 0;
        while (i < numCells) {
            for (int j = 0; j < numRows; j++) {
                blocks[currBlock] = cells[i];
                currBlock++;
                i += numCols;
            }
            for (int j = 0; j < numRows - 1; j++) {
                i += numRows * numCols;
            }
        }
        for (int j = 0; j < numRows * numCols; j++) {
            System.out.println("Main Outer");
            //use prev node to link
            Cell currCell = blocks[j];
            Cell cellBelow = currCell.below;
            for (int l = 0; l < numRows; l++) {
                for (int k = 0; k < numCols - 1 ; k++) {
                    System.out.println("currCell: " + currCell);
                    currCell.block = currCell.right;
                    currCell = currCell.block;
                }
                System.out.println("currCell: " + currCell);
                currCell.block = cellBelow;
                currCell = currCell.block;
                if (cellBelow != null) {
                    cellBelow = cellBelow.below;
                }
            }
            if (currCell != null) {
                currCell.block = null;
            }
        }
        for (int j = 0; j < numRows * numCols; j++) {
            Cell currCell = blocks[j];
            while (currCell != null) {
                System.out.print(currCell + " ");
                currCell = currCell.block;
            }
            System.out.println();
        }
        
    }
    
    public void fullProp() {
    }

    public void propCell(Cell cell) {
    }

    public void solve() {
    }

    public boolean soleCandidate() {
        return false;
    }

    public boolean uniqueCandidate() {
        return false;
    }

    public boolean duplicateCells() {
        return false;
    }
}