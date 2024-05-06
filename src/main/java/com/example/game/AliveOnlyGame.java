package com.example.game;

import com.example.game.model.Cell;
import com.example.game.model.Grid;
import com.example.game.util.GridGenerator;

public class AliveOnlyGame {

    private int MAX_ROWS = 200;
    private int MAX_COLUMNS = 200;

    private static final int GENERATIONS = 100;

    public static void main(String[] args) {
        AliveOnlyGame game = new AliveOnlyGame();
        if (args.length < 1) {
            System.out.println("Please provide at least one starting configuration in the form [[x1,y1],....,[xn,yn]]");
            return;
        }
        if (args.length == 3) {
            game.MAX_ROWS = Integer.parseInt(args[1]);
            game.MAX_COLUMNS = Integer.parseInt(args[2]);
        }
        game.run(GridGenerator.generateGrid(args[0], game.MAX_ROWS, game.MAX_COLUMNS));
    }

    public void run(Grid grid) {
        for (int generation = 1; generation <= GENERATIONS && grid.aliveCount() > 0; generation++) {
            grid = nextGeneration(grid);
            printLife(grid, generation);
        }
    }

    static Grid nextGeneration(Grid grid) {
        Grid future = new Grid(grid.getRows(), grid.getColumns());
        int aliveNeighbours;
        // Loop through every alive cell and adjacent cells
        for (Cell cell : grid) {
            if (!cell.isCalculated()) {
                future.setCell(cell.row, cell.column, liveRule(cell, grid.aliveNeighboursCount(cell)));
            }
           // Before continuing to the next alive cell check the future status of all uninhabited neighbours
            grid.getDeadNeighbours(cell).stream()
                    .filter(neighbour -> !neighbour.isCalculated())
                    .forEach(neighbour -> {
                        future.setCell(neighbour.row, neighbour.column, liveRule(neighbour, grid.aliveNeighboursCount(neighbour)));
                    });
        }
        return future;
    }

    private static boolean liveRule(Cell cell, int aliveNeighbours) {
        if (cell.isAlive() && (aliveNeighbours < 2 || aliveNeighbours > 3))
            return false;
        else if (!cell.isAlive() && aliveNeighbours == 3)
            return true;

        return cell.isAlive();
    }

    private static void printLife(Grid grid, int generation) {
        if (grid.aliveCount() > 0) {
            StringBuilder buffer = new StringBuilder();
            buffer.append(generation).append(": ");
            buffer.append(grid.aliveString());
            System.out.println(buffer);
        } else {
            System.out.println("[]");
        }
    }
}
