package com.example.game.model;

import java.util.*;

public class Grid implements Iterable<Cell> {

    private final HashMap<Integer, Cell> grid;
    private final LinkedHashMap<Integer, Cell> alives;
    private final int rows;
    private final int columns;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new HashMap<>();
        this.alives = new LinkedHashMap<>();
    }

    public void setCell(int row, int column, boolean alive) {
        if (row < 0 || row > rows - 1 || column < 0 || column > columns - 1) {
            throw new IllegalArgumentException("Invalid row or column, %s, %s".formatted(row, column));
        }
        Cell cell = new Cell(alive, row, column);
        grid.put(cell.hashCode(), cell);
        if (alive) {
            alives.put(cell.hashCode(), cell);
        }
    }

    public int aliveNeighboursCount(Cell cell) {
        int alivesCount = 0;
        for (String neighbour : this.getNeighboursString(cell)) {
            if (alives.containsKey(neighbour.hashCode())) {
                alivesCount++;
            }
        }
        cell.setCalculated(true, alivesCount);
        return alivesCount;
    }

    public int aliveCount() {
        return alives.size();
    }

    public String[] getNeighboursString(Cell cell) {
        int row = cell.row;
        int column = cell.column;
        ArrayList<String> arrayList = new ArrayList<>();
        for (int cdif = -1; cdif <= 1; cdif++)
            for (int rowdif = -1; rowdif <= 1; rowdif++)
                if ((row + rowdif >= 0 && row + rowdif < rows) && (column + cdif >= 0 && column + cdif < columns) && (rowdif != 0 || cdif != 0))
                    arrayList.add("[" + (row + rowdif) + "," + (column + cdif) + "]");
        return arrayList.toArray(new String[0]);
    }


    public List<Cell> getDeadNeighbours(Cell cell) {
        LinkedList<Cell> nonAliveNeighbours = new LinkedList<>();
        for (String neighbour : getNeighboursString(cell)) {
            //if alive continue, only want dead cells
            if (this.alives.containsKey(neighbour.hashCode())) {
                continue;
            }
            if (this.grid.containsKey(neighbour.hashCode())) {
                nonAliveNeighbours.add(this.grid.get(neighbour.hashCode()));
            } else {
                Cell newCell = new Cell(neighbour);
                nonAliveNeighbours.add(newCell);
                grid.put(cell.hashCode(), newCell);
            }
        }

        return nonAliveNeighbours;
    }


    public String aliveString() {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : alives.values()) {
            sb.append(cell.toString());
        }
        return sb.toString();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public Iterator<Cell> iterator() {
        return alives.values().iterator();
    }


}







