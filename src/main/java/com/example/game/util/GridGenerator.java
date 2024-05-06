package com.example.game.util;

import com.example.game.model.Grid;

public class GridGenerator {

    public static Grid generateGrid(String alives, int rows, int columns) {
        String news = alives.replaceAll("]\\[", ",");
        news = news.replaceAll("[\\[\\]]", "");
        String[] splitValues = news.trim().split(",");

        Grid grid = new Grid(rows, columns);

        for (int i = 0; i < splitValues.length; i += 2) {
            grid.setCell(Integer.parseInt(splitValues[i]), Integer.parseInt(splitValues[i + 1]), true);
        }

        return grid;
    }
}
