@SuppressWarnings("unchecked")
public class Database {
    String[][] database;
    String[] columnNames;
    Treap<Cell>[] indexes;

    public Database(String[] cols, int maxSize) {
        database = new String[maxSize][cols.length];
        for (int i = 0; i < maxSize; i++) {
            database[i] = new String[cols.length];
            for (int j = 0; j < cols.length; j++) {
                database[i][j] = null;
            }
        }
        columnNames = new String[cols.length];
        for (int i = 0; i < cols.length; i++) {
            columnNames[i] = cols[i];
        }
        indexes = new Treap[cols.length];
        for (int i = 0; i < cols.length; i++) {
            indexes[i] = null;
        }
    }

    public void insert(String[] newRowDetails) throws DatabaseException {
        if (newRowDetails.length != columnNames.length) {
            throw DatabaseException.invalidColumnName(null);
        }
        for (int j = 0; j < newRowDetails.length; j++) {
            Cell testCell = new Cell();
            testCell.value = newRowDetails[j];
            if (indexes[j] != null) {
                if (indexes[j].access(testCell) != null) {
                    throw DatabaseException.duplicateInsert(testCell.value);
                }
            }
        }
        int i = 0;
        while (i < database.length && database[i][0] != null) {
            i++;
        }
        if (i >= database.length) {
            throw DatabaseException.databaseFull();
        }
        for (int j = 0; j < newRowDetails.length; j++) {
            database[i][j] = newRowDetails[j];
            if (indexes[j] != null) {
                Cell cell = new Cell();
                cell.value = newRowDetails[j];
                cell.databaseRow = i;
                indexes[j].insert(cell);
            }
        }
    }

    public String[] removeFirstWhere(String col, String data) throws DatabaseException {
        Cell cellToRemove = new Cell();
        cellToRemove.value = data;
        cellToRemove.databaseRow = -1;
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i] != null && columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }
        int rowIndex = -1;
        Cell removedCell = null;
        if (indexes[colIndex] != null) {
            Node<Cell> tempNode = indexes[colIndex].remove(cellToRemove);
            if (tempNode != null) {
                removedCell = tempNode.data;
            }
            if (removedCell == null) {
                return new String[0];
            }
            rowIndex = removedCell.databaseRow;
        } else {
            for (int i = 0; rowIndex < 0 && i < database.length; i++) {
                if (database[i][colIndex] != null && database[i][colIndex].equals(data)) {
                    rowIndex = i;
                }
            }
        }
        if (rowIndex < 0) {
            return new String[0];
        }
        String[] removedRow = new String[columnNames.length];
        for (int i = 0; i < columnNames.length; i++) {
            removedRow[i] = database[rowIndex][i];
            database[rowIndex][i] = null;
            if (indexes[i] != null) {
                Cell temp = new Cell();
                temp.value = data;
                temp.databaseRow = rowIndex;
                indexes[i].remove(temp);
            }
        }
        return removedRow;
    }

    public String[][] removeAllWhere(String col, String data) throws DatabaseException {
        String[][] tempRemovedRows = new String[database.length + 1][];
        int numRemoved = 0;
        boolean isNull = false;
        while (!isNull) {
           String[] tempRow = removeFirstWhere(col, data);
           if (tempRow.length != 0) {
            tempRemovedRows[numRemoved] = tempRow;
            numRemoved++;
           } else {
            isNull = true;
           }
        }
        String[][] removedRows = new String[numRemoved][];
        for (int i = 0; i < numRemoved; i++) {
            removedRows[i] = tempRemovedRows[i];
        }
        return removedRows;    
    }

    public String[] findFirstWhere(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }
        int rowIndex = -1;
        Cell cellToFind = new Cell();
        cellToFind.value = data;
        cellToFind.databaseRow = -1;
        Cell foundCell = null;
        if (indexes[colIndex] != null) {
            Node<Cell> tempNode = indexes[colIndex].access(cellToFind);
            if (tempNode != null) {
                foundCell = tempNode.data;
            }
            if (foundCell == null) {
                return new String[0];
            }
            // removedCell = indexes[colIndex].remove(cellToRemove).data;
            // if (removedCell == null) {
            //     return new String[0];
            // }
            // rowIndex = removedCell.databaseRow;
        } else {
            for (int i = 0; rowIndex < 0 && i < database.length; i++) {
                if (database[i][colIndex] != null && database[i][colIndex].equals(data)) {
                    rowIndex = i;
                }
            }
        }
        if (rowIndex == -1) {
            return new String[0];
        }
        return database[rowIndex];
    }

    public String[][] findAllWhere(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }
        String[][] tempFoundRows = new String[database.length][];
        int numFound = 0;
        Cell cellToFind = new Cell();
        cellToFind.value = data;
        cellToFind.databaseRow = -1;
        if (indexes[colIndex] != null) {
            Node<Cell> tempNode = indexes[colIndex].access(cellToFind);
            Cell tempCell = null;
            if (tempNode != null) {
                tempCell = tempNode.data;
            }
            if (tempCell == null) {
                return new String[0][];
            }
        }
        for (int i = 0; i < database.length; i++) {
            if (database[i][colIndex] != null && database[i][colIndex].equals(data)) {
                tempFoundRows[numFound] = database[i];
                numFound++;
            }
        }
        String[][] ret = new String[numFound][columnNames.length];
        for (int i = 0; i < numFound; i++) {
            ret[i] = tempFoundRows[i];
        }
        return ret;
    }

    public String[] updateFirstWhere(String col, String updateCondition, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }
        if (indexes[colIndex] == null) {
            for (int i = 0; i < database.length; i++) {
                if (database[i][colIndex] != null && database[i][colIndex].equals(data)) {
                    database[i][colIndex] = updateCondition;
                    return database[i];
                }
            }
        } else {
            Cell tempCell = new Cell();
            tempCell.value = data;
            tempCell.databaseRow = -1;
            Node<Cell> tempNode = indexes[colIndex].remove(tempCell);
            if (tempNode != null) {
                tempCell = tempNode.data;
                tempCell.value = updateCondition;
                indexes[colIndex].insert(tempCell);
                database[tempCell.databaseRow][colIndex] = updateCondition;
                return database[tempCell.databaseRow];
            }
        }
        return new String[0];
    }

    public String[][] updateAllWhere(String col, String updateCondition, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }

        String[][] tempStr = new String[database.length][columnNames.length];
        int numUpdated = 0;
        boolean isNull = false;
        while (!isNull) {
            String[] temp = updateFirstWhere(col, updateCondition, data);
            if(temp.length == 0){
                isNull = true;
            } else {
                tempStr[numUpdated] = temp;
                numUpdated++;
            }
        }
        String [][] ret = new String[numUpdated][columnNames.length];
        for (int i = 0; i < numUpdated; i++) {
            ret[i] = tempStr[i];
        }
        return ret;
    }

    public Treap<Cell> generateIndexOn(String col) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }
        if (indexes[colIndex] != null) {
            return indexes[colIndex];
        }
        Treap<Cell> newTreap = new Treap<Cell>();
        for (int i = 0; i < database.length; i++) {
            if (database[i][colIndex] != null) {
                Cell tempCell = new Cell();
                tempCell.value = database[i][colIndex];
                tempCell.databaseRow = i;
                try {
                    newTreap.insert(tempCell);
                } catch (Exception e) {
                    newTreap = null;
                    throw e;
                }
            }
        }
        indexes[colIndex] = newTreap;
        return newTreap;
    }

    public Treap<Cell>[] generateIndexAll() throws DatabaseException {
        for (int i = 0; i < columnNames.length; i++) {
            try {
                generateIndexOn(columnNames[i]);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return indexes;
    }

    public int countOccurences(String col, String data) throws DatabaseException {
        int colIndex = -1;
        for (int i = 0; colIndex < 0 && i < columnNames.length; i++) {
            if (columnNames[i].equals(col)) {
                colIndex = i;
            }
        }
        if (colIndex == -1) {
            throw DatabaseException.invalidColumnName(col);
        }
        int ret = 0;
        for (int i = 0; i < database.length; i++) {
            if (database[i][colIndex] != null && database[i][colIndex].equals(data)) {
                ret++;
            }
        }
        return ret;
    }
}
