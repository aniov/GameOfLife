package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Marius on 1/7/2017.
 */
public class Block {

    /** Change Block Size HERE*/
    private static int BLOCK_SIZE = 10;

    public static Rectangle createLife(int pos_X, int pos_Y) {
        Rectangle life = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        life.setFill(Color.BLACK);
        life.setStroke(Color.GREEN);
        life.setTranslateX(pos_X * BLOCK_SIZE);
        life.setTranslateY(pos_Y * BLOCK_SIZE);
        return life;
    }

    public static Rectangle createPassed(int pos_X, int pos_Y) {
        Rectangle life = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        life.setFill(Color.LIGHTGREEN);
        life.setTranslateX(pos_X * BLOCK_SIZE);
        life.setTranslateY(pos_Y * BLOCK_SIZE);
        return life;
    }

    public static Rectangle createTransparent(int pos_X, int pos_Y) {
        Rectangle life = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        life.setFill(Color.TRANSPARENT);
        life.setStroke(Color.GREY);
        life.setTranslateX(pos_X * BLOCK_SIZE);
        life.setTranslateY(pos_Y * BLOCK_SIZE);
        return life;
    }

    public static int getBlockSize() {
        return BLOCK_SIZE;
    }

    public static void setBlockSize(int blockSize) {
        if (blockSize > 3 && blockSize <= 50)
            BLOCK_SIZE = blockSize;
    }
}
