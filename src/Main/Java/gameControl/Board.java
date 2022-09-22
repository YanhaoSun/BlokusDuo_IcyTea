package gameControl;

/**
 * Game board for Blokus Duo by IcyTea
 * Group name: IcyTea
 * Members:
 * Cathal Poon (20343243)
 * YanHao Sun (19205434)
 * Rubing Wang (20206497)
 */

public class Board {
    // how big is the board     14 x 14
    // how to store the board   2D arrays
    // how to show the board?   Copy example
    // display method?          printing

    // starting board
    // add piece to board

    public static final char EMPTYCHAR = '~';

    private final int width, height; //set size of the board
    private final char[][] gameBoard; // store the board, should be 14 x 14

    // constructor
    public Board() {
        this(14, 14);
    }

    // NOTE update has no error checking regarding size. EXPERIMENTAL!!!
    private Board(int width, int height) {
        this.height = height;
        this.width = width;
        gameBoard = new char[height][width];
        boardInit();
    }
    /*
    END EXPERIMENTAL
     */

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void boardInit() {
        //initialise starting point in the board(because the y-axis in our board
        // is in reverse with the y-axis in assignment 1 slides
        //so, the board printed out looks different
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // set all the points to EMPTYCHAR
                gameBoard[y][x] = EMPTYCHAR;
            }
        }
        // show 2 starting point on the board
        update(9, 4, '*');
        update(4, 9, '*');

    }

    public boolean inRange(int value, int limit) {
        return 0 <= value && value < limit;
    }

    /**
     * @param x  x-coordinate of character to be changed
     * @param y  y-coordinate of character to be changed
     * @param ch character to change to
     * @throws IllegalArgumentException Thrown if x or y are off the board
     */
    public void update(int x, int y, char ch) throws IllegalArgumentException {
        if (!inRange(x, width)) {
            throw new IllegalArgumentException(String.format("%c must be between 0 - %d\n", 'x', width-1));
        }
        if (!inRange(y, height)) {
            throw new IllegalArgumentException(String.format("%c must be between 0 - %d\n", 'y', height-1));
        }

        gameBoard[y][x] = ch;
    }

    /**
     * Check to see if a location is free on the gameBoard
     *
     * @param x integer x co-ordinate
     * @param y integer y co-ordinate
     * @return True if location given is empty, False if it's not or off the board
     */
    public boolean isEmpty(int x, int y) {
        if (!inRange(x, width)) {
            return false;
        }
        if (!inRange(y, height)) {
            return false;
        }

        return getCharFromBoard(x, y) == '*' || getCharFromBoard(x, y) == EMPTYCHAR;

    }


    public char getCharFromBoard(int x, int y) throws IllegalArgumentException {
        // gate
        if (!inRange(x, width)) {
            throw new IllegalArgumentException(String.format("%c must be between 0 - %d\n", 'x', width));
        }
        if (!inRange(y, height)) {
            throw new IllegalArgumentException(String.format("%c must be between 0 - %d\n", 'y', height));
        }

        return gameBoard[y][x];
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();


        //add 13 - 0 before each row
        for (int i = height - 1; i >= 0; i--) {

            buffer.append(String.format("%2d ", i));

            for (int j = 0; j < width; j++) {
                buffer.append(String.format(" %1c", gameBoard[i][j]));
            }
            buffer.append("\n");
        }

        //add '0' - '13' on top of each column
        buffer.append(String.format("%2c ", ' ')); // white space at beginning to align stuff
        for (int i = 0; i < width; i++) {
            buffer.append(String.format("%2d", i));
        }
        buffer.append("\n");

        return buffer.toString();
    }
}
