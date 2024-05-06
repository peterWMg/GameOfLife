package com.example.game;


import com.example.game.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.SplittableRandom;
import java.util.random.RandomGenerator;

public class AliveGameTests {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    public void captureOutput() {
        this.outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    public void resetOutput() {
        System.setOut(standardOut);
    }


    @Test
    public void testOneOne() throws Exception {
        this.captureOutput();
        new AliveOnlyGame().main(new String[]{"[[1,1]]"});
        resetOutput();
        Assertions.assertEquals("[]", outContent.toString().trim());
    }

    @Test
    public void tesGiven() throws Exception {
        this.captureOutput();
        new AliveOnlyGame().main(new String[]{"[[5,5],[6,5],[7,5],[5,6],[6,6],[7,6]]"});
        resetOutput();

        String expectedResult = new TestUtil().loadResultFromFile("givenResult.txt");
        Assertions.assertEquals(expectedResult, outContent.toString().trim());
    }

    @Test
    public void tesGivenRowFirst() throws Exception {
        this.captureOutput();
        AliveOnlyGame.main(new String[]{"[[5,5],[5,6],[6,5],[6,6],[7,5],[7,6]]"});
        resetOutput();

        String expectedResult = new TestUtil().loadResultFromFile("givenRowFirstResult.txt");
        Assertions.assertEquals(expectedResult, outContent.toString().trim());
    }

//    @Test
//    public void testLarge() throws Exception {
//        AliveOnlyGame.main(new String[]{AliveGameTests.generateStringGrid(2000, 2000, 0.8), "2000", "2000"});
//    }


    @Test
    public void testBasic() throws Exception {
        this.captureOutput();
        new AliveOnlyGame().main(new String[]{"[[1,3][1,4][2,4][5,3][5,4][6,2][6,3][7,5][8,4]]"});
        resetOutput();

        String expected = new TestUtil().loadResultFromFile("basicResult.txt");
        Assertions.assertEquals(expected, outContent.toString().trim());
    }

    @Test
    public void testNoArgs() {
        this.captureOutput();
        AliveOnlyGame.main(new String[]{});
        resetOutput();

        String expected = "Please provide at least one starting configuration in the form [[x1,y1],....,[xn,yn]]";
        Assertions.assertEquals(expected, outContent.toString().trim());
    }

    @Test
    public void testTooLargeRow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AliveOnlyGame.main(new String[]{"[[1,1],[2,5]]", "5", "5"});
        });
    }

    @Test
    public void testTooLargeColumn() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new AliveOnlyGame().main(new String[]{"[[1,1],[4,4],[5,3],[4,4]]", "5", "5"});
        });
    }

    @Test
    public void testTooSmallRow() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AliveOnlyGame.main(new String[]{"[[1,1],[2,-1]]", "5", "5"});
        });
    }

    @Test
    public void testTooSmallColumn() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AliveOnlyGame.main(new String[]{"[[1,1],[4,4],[-1,3],[4,4]]", "5", "5"});
        });
    }


    @Test
    public void testRubbishInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            AliveOnlyGame.main(new String[]{"asdfasdf", "5", "5"});
        });
    }

    @Test
    public void testDuplicate() throws Exception {
        this.captureOutput();
        AliveOnlyGame.main(new String[]{"[[5,5],[6,5],[6,5],[7,5],[5,6],[7,5],[6,6],[7,6]]"});
        this.resetOutput();

        String expectedResult = new TestUtil().loadResultFromFile("givenResult.txt");
        Assertions.assertEquals(expectedResult, outContent.toString().trim());
    }


    private static String generateStringGrid(int rows, int columns, double probability) {
        RandomGenerator random = new SplittableRandom();
        StringBuilder builder = new StringBuilder("[");
        int[][] grid = new int[rows][columns];
        boolean first = true;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (random.nextDouble() > probability) {
                    if (!first) {
                        builder.append(",");
                    }
                    builder.append("[").append(row).append(",").append(column).append("]");
                }
            }
        }
        return builder.append("]").toString();
    }

}
