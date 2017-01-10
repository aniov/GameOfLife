package sample;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marius on 1/7/2017.
 */
public class CycleOfLife {

    private int wide = WindowSize.WIDE / Block.getBlockSize();
    private int high = WindowSize.HIGH / Block.getBlockSize();
    /**
     * Number of Live Cells: lower Value, Higher Chance
     */
    private int chanceOfLife = 10;

    private blockType[][] board = new blockType[high][wide];
    private blockType[][] nextBoard = new blockType[high][wide];
    List<Rectangle> life = new ArrayList<>();
    private boolean acorn, gliderGun, random;
    public enum blockType {
        LIFE, EMPTY, PASSED
    }

    public void createRandomBoard() {
        this.board = Templates.Random(high, wide, chanceOfLife);
        setRandom(true);
    }

    public void createAcornBoard(){
        this.board = Templates.Acorn(high, wide);
        setAcorn(true);
    }

    public void createGliderGunBoard(){
        this.board = Templates.GliderGun(high, wide);
        setGliderGun(true);
    }

    public List<Rectangle> getBoard() {
        life.clear();
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < wide; j++) {
                if (blockType.LIFE.equals(board[i][j])) {
                    life.add(Block.createLife(j, i));
                } else if (blockType.PASSED.equals(board[i][j]))
                    life.add(Block.createPassed(j, i));
                /** Show as a grid*/
               /* else if (blockType.EMPTY.equals(board[i][j]))
                    life.add(Block.createTransparent(j, i));*/
            }
        }
        /** Copy nextBoard to board*/
        board = Arrays.stream(runCycleOfLife()).map(blockType[]::clone).toArray(blockType[][]::new);
        return life;
    }

    private blockType[][] runCycleOfLife() {

        int neighbors;
        /** Copy board to nextBoard if we want to see the "PASSED" history*/
        /*nextBoard = Arrays.stream(board).map(blockType[]::clone).toArray(blockType[][]::new);*/

        for (int i = 0; i < high; i++) {
            for (int j = 0; j < wide; j++) {
                neighbors = nrOfNeighbors(i, j);
                nextBoard[i][j] = blockType.EMPTY; // Comment

                if (blockType.LIFE.equals(board[i][j])) {
                    if (neighbors == 2 || neighbors == 3)
                        nextBoard[i][j] = blockType.LIFE;
                    else if (neighbors <= 1 || neighbors >= 4)
                        nextBoard[i][j] = blockType.PASSED;
                } else if (blockType.EMPTY.equals(board[i][j]) || blockType.PASSED.equals(board[i][j])) {
                    if (neighbors == 3)
                        nextBoard[i][j] = blockType.LIFE;
                }/*else
                    nextBoard[i][j] = blockType.EMPTY;*/ // Uncomment
            }
        }
        return nextBoard;
    }

    private int nrOfNeighbors(int pos_Y, int pos_X) {

        int neighbors = 0;

        /** UP*/
        if (pos_Y - 1 >= 0)
            if (blockType.LIFE.equals(board[pos_Y - 1][pos_X]))
                neighbors++;
        /** UP-RIGHT*/
        if (pos_Y - 1 >= 0 && pos_X + 1 < wide)
            if (blockType.LIFE.equals(board[pos_Y - 1][pos_X + 1]))
                neighbors++;
        /** RIGHT*/
        if (pos_X + 1 < wide) {
            if (blockType.LIFE.equals(board[pos_Y][pos_X + 1]))
                neighbors++;
        }
        /** DOWN-RIGHT*/
        if (pos_X + 1 < wide && pos_Y + 1 < high)
            if (blockType.LIFE.equals(board[pos_Y + 1][pos_X + 1]))
                neighbors++;
        /** DOWN*/
        if (pos_Y + 1 < high)
            if (blockType.LIFE.equals(board[pos_Y + 1][pos_X]))
                neighbors++;
        /** DOWN-LEFT*/
        if (pos_X - 1 > 0 && pos_Y + 1 < high)
            if (blockType.LIFE.equals(board[pos_Y + 1][pos_X - 1]))
                neighbors++;
        /** LEFT*/
        if (pos_X - 1 > 0)
            if (blockType.LIFE.equals(board[pos_Y][pos_X - 1]))
                neighbors++;
        /** UP-LEFT*/
        if (pos_X - 1 > 0 && pos_Y - 1 > 0)
            if (blockType.LIFE.equals(board[pos_Y - 1][pos_X - 1]))
                neighbors++;

        return neighbors;
    }

    public  int getChanceOfLife() {
        return chanceOfLife;
    }

    public  void setChanceOfLife(int chanceOfLife) {
        if (chanceOfLife > 4 && chanceOfLife < 20)
            this.chanceOfLife = chanceOfLife;
    }

    public boolean isAcorn() {
        return acorn;
    }

    public void setAcorn(boolean acorn) {
        if (acorn == true) {
            this.acorn = acorn;
            this.random = false;
            this.gliderGun = false;
        }
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        if (random == true) {
            this.random = random;
            this.acorn = false;
            this.gliderGun = false;
        }
    }

    public boolean isGliderGun() {
        return gliderGun;
    }

    public void setGliderGun(boolean gliderGun) {
        if (gliderGun == true) {
            this.gliderGun = gliderGun;
            this.acorn = false;
            this.random = false;
        }
    }


}
