package sample;

import java.util.Random;

/**
 * Created by Marius on 1/7/2017.
 */
public class Templates {

    public static CycleOfLife.blockType[][] Random(int high, int wide, int chanceOfLife) {
        Random random = new Random();
        CycleOfLife.blockType[][] board = new CycleOfLife.blockType[high][wide];
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < wide; j++) {
                if (random.nextInt(chanceOfLife) == 0) {
                    board[i][j] = CycleOfLife.blockType.LIFE;
                } else
                    board[i][j] = CycleOfLife.blockType.EMPTY;
            }
        }
        return board;
    }
    /** Acorn Template*/
    public static CycleOfLife.blockType[][] Acorn(int high, int wide) {

        final int[][] acorn = {{2, 0}, {2, 1}, {0, 1}, {1, 3}, {2, 4}, {2, 5}, {2, 6}};
        return createTemplateBoard(acorn, high, wide);
    }

    /** Glider Gun Template*/
    public static CycleOfLife.blockType[][] GliderGun(int high, int wide) {

        final int[][] gliderRun = {{4, 0},{4, 1},{5, 0},{5, 1},{4, 10},{5, 10},{6, 10},
                {3, 11},{2, 12},{2, 13},{3, 15},{4, 16},{5, 16},{6, 16},{5, 17},
                {7, 15},{8, 13},{8, 12},{7, 11},{5, 14},{4, 20},{3, 20},{2, 20},
                {2, 21},{3, 21}, {4, 21},{5, 22},{5, 24},{6, 24},{1, 22},{1, 24},
                {0, 24},{2, 34},{2, 35},{3, 34},{3, 35}};
        return createTemplateBoard(gliderRun, high, wide);
    }

    private static CycleOfLife.blockType[][] createTemplateBoard(int[][] template, int high, int wide){

        CycleOfLife.blockType[][] board = new CycleOfLife.blockType[high][wide];
        int midX = high / 2;
        int midY = wide / 2;
        /** read from the template array and ini the board*/
        for (int i = 0; i < template.length; i++) {
            board[template[i][0] + midX][template[i][1] + midY] = CycleOfLife.blockType.LIFE;
        }
        for (int i = 0; i < high; i++)
            for (int j = 0; j < wide; j++) {
                if (! CycleOfLife.blockType.LIFE.equals(board[i][j]))
                    board[i][j] = CycleOfLife.blockType.EMPTY;
            }
        return board;
    }
}