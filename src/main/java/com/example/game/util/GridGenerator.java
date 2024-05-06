package com.example.game.util;

import com.example.game.model.Cell;
import com.example.game.model.Grid;

import java.util.HashMap;

public class GridGenerator {

    public static int[][] generateIntArrayGrid(String alives, int rows, int columns) {
        String news = alives.replaceAll("\\]\\[", ",");
        news = news.replaceAll("[\\[\\]]", "");
        String[] splitValues = news.trim().split(",");

        int[][] grid = new int[rows][columns]; // zero value array

        for (int i = 0; i < splitValues.length; i += 2) {
            grid[Integer.parseInt(splitValues[i])][Integer.parseInt(splitValues[i + 1])] = 1;
        }

        return grid;
    }

    public static HashMap<Integer, Cell> generateCells(String alives, int rows, int columns) {
        String news = alives.replaceAll("\\]\\[", ",");
        news = news.replaceAll("[\\[\\]]", "");
        String[] splitValues = news.trim().split(",");

        HashMap<Integer, Cell> aliveCells = new HashMap<>();

        for (int i = 0; i < splitValues.length; i += 2) {
            Cell cell = new Cell(true, Integer.parseInt(splitValues[i]), Integer.parseInt(splitValues[i + 1]));
            aliveCells.put(cell.hashCode(), cell);
        }

        return aliveCells;
    }

    public static Grid generateGrid(String alives, int rows, int columns) {
        String news = alives.replaceAll("\\]\\[", ",");
        news = news.replaceAll("[\\[\\]]", "");
        String[] splitValues = news.trim().split(",");

        Grid grid = new Grid(rows, columns);

        for (int i = 0; i < splitValues.length; i += 2) {
            grid.setCell(Integer.parseInt(splitValues[i]), Integer.parseInt(splitValues[i + 1]), true);
        }

        return grid;
    }
}
