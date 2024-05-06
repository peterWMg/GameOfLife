package com.example.game.model;

import java.util.LinkedList;

public class Cell {
    private boolean alive;
    public int row;
    public  int column;
    private boolean calculated;
    private int aliveNeighbours;

    public Cell(boolean alive, int row, int column) {
        this.alive = alive;
        this.row = row;
        this.column = column;
    }

    public Cell(String neighbour) {
        String[] split = neighbour.split(",");
        this.row = Integer.parseInt(split[0].substring(1));
        this.column = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
    }

    public void calculate() {
        this.calculated = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated, int aliveNeighbours) {
        this.calculated = calculated;
        this.aliveNeighbours = aliveNeighbours;;
    }

    public String toString() {
        return new StringBuilder("[").append(row).append(",").append(column).append("]").toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
