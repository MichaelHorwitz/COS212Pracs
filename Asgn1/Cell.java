public class Cell {
    public int numRows, numCols, r, c, b;
    public Cell below, right, block;
    public Integer value;
    public List<Integer> possibleValues;

    public String toString() {
        if (value == null) {

            String res = " ";
            for (int i = 0; i < String.valueOf(numRows * numCols).length(); i++) {
                res += "-";
            }
            res += " ";
            return res;
        }
        return " " + String.format("%" + String.valueOf(numRows * numCols).length() + "d", value).replace(" ", "0")
                + " ";
    }

    /*
     * Don't change anything above this line
     */

    public Cell(int nR, int nC, String inp) {
        numRows = nR;
        numCols = nC;
        below = right = block = null;
        if (inp.equals("-")) {
            value = null;
            int maxVal = numRows * numCols;
            possibleValues = new List<Integer>();
            for (int i = 1; i <= maxVal; i++) {
                possibleValues.append(i);
            }
            return;
        }
        value = Integer.parseInt(inp);
        possibleValues = null;

    }

    public void removeVal(int val) {
        if (possibleValues == null) {
            return;
        }
        possibleValues.remove(val);
    }

    public void setVal(int val) {
        value = val;
        possibleValues = null;
    }

}
