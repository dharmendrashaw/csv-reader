package com.test.data;

import com.test.exception.EndOfDataException;
import com.test.exception.InValidOrEmptyDataException;
import com.test.exception.InvalidDataException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVData {

    private List<String[]> data;

    private Map<String, Integer> columnIndexMap;

    private int currentRow = 1;

    public CSVData(List<String[]> data) {
        if (data == null || data.isEmpty()) {
            throw new InValidOrEmptyDataException("Null or empty data not allowed");
        }

        this.data = data;
        columnIndexMap = new HashMap<>();

        String[] columns = getColumns();
        for (int i = 0; i < columns.length; i++) {
            columnIndexMap.put(columns[i], i);
        }
    }

    public String[] getColumns() {
        return data.get(0);
    }

    public int countColumns() {
        return getColumns().length;
    }

    public boolean hasNext() {
        return currentRow < countRow();
    }

    private int countRow() {
        return data.size();
    }

    public String[] getNextRow() {
        if (!hasNext()) {
            throw new EndOfDataException("No rows left to read");
        }
        return data.get(currentRow++);
    }

    public Map<String, String> getNextRowWithColumn() {

        if (!hasNext()) {
            throw new EndOfDataException("No rows left to read");
        }

        Map<String, String> rowData = new HashMap<>();

        String[] row = getNextRow();
        for (int i = 0; i < countColumns(); i++) {
            rowData.put(getColumns()[i], row[i]);
        }

        return rowData;
    }

    public List<String[]> getData() {
        return this.data;
    }

    /**
     * Add a new column to existing column
     *
     * @param column - column name to be added
     * @return - Index of column if added -1 otherwise.
     */
    public int addColumn(String column) {
        if (columnIndexMap.containsKey(column)) {
            return -1;
        }

        String[] columns = getColumns();
        String[] withAddedColumn = new String[columns.length + 1];

        System.arraycopy(columns, 0, withAddedColumn, 0, columns.length);
        withAddedColumn[withAddedColumn.length - 1] = column;

        this.data.set(0, withAddedColumn);
        return columnIndexMap.put(column, withAddedColumn.length - 1);
    }

    /**
     * @param whichRow    - row number where data has to be added.
     * @param whichColumn - name of the column for which data is to be added
     * @param data        - Content to be added.
     * @return - Index of column where data added.
     */
    public int addDataToColumn(int whichRow, String whichColumn, String data) {
        if (whichRow > countRow() || !columnIndexMap.containsKey(whichColumn)) {
            throw new InvalidDataException("Invalid row `" + whichRow + "` or invalid column `" + whichColumn + "`");
        }

        String[] row = this.data.get(whichRow);
        String[] withAddedData = row.length == getColumns().length ? row : new String[row.length + 1];

        System.arraycopy(row, 0, withAddedData, 0, row.length);
        int columnIndex = columnIndexMap.get(whichColumn);
        withAddedData[columnIndex] = data;
        this.data.set(whichRow, withAddedData);
        return columnIndex;
    }
}

