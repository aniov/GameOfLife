package sample;

/**
 * Created by Marius on 1/7/2017.
 */
public class WindowSize {

    /** Change Window Size HERE*/
    private final static int W_WIDE = 1600;
    private final static int W_HIGH = 900;

    public static final int WIDE = Block.getBlockSize() * (W_WIDE / Block.getBlockSize());
    public static final int HIGH = Block.getBlockSize() * (W_HIGH / Block.getBlockSize());
}
