package gameControl;

import gameControl.Board;
import java.util.Hashtable;


public class Piece {
    String name;
    int[][] shape;
    char symbol;
    static Hashtable<String, int[][]> shapesTable = new Hashtable<>() {{
        put("I1", new int[][]{{0, 0}});
        put("I2", new int[][]{{0, 1}, {0, 0}});
        put("I3", new int[][]{{0, 2}, {0, 1}, {0, 0}});
        put("I4", new int[][]{{0, 3}, {0, 2}, {0, 1}, {0, 0}});
        put("I5", new int[][]{{0, 4}, {0, 3}, {0, 2}, {0, 1}, {0, 0}});
        put("V3", new int[][]{{0, 1}, {0, 0}, {1, 0}});
        put("L4", new int[][]{{0, 2}, {0, 1}, {0, 0}, {1, 0}});
        put("Z4", new int[][]{{0, 1}, {1, 1}, {-1, 0}, {0, 0}});
        put("O4", new int[][]{{0, 1}, {1, 1},
                {0, 0}, {1, 0}});
        put("L5", new int[][]{{0, 1},
                {0, 0}, {1, 0}, {2, 0}, {3, 0}});
        put("T5", new int[][]{{0, 2},
                {0, 1},
                {-1, 0}, {0, 0}, {1, 0}});
        put("V5", new int[][]{{0, 2},
                {0, 1},
                {0, 0}, {1, 0}, {2, 0}});
        put("N", new int[][]{{0, 0}, {1, 0}, {2, 0},
                {-1, -1}, {0, -1}});
        put("Z5", new int[][]{{1, 1},
                {-1, 0}, {0, 0}, {1, 0},
                {-1, -1}});
        put("T4", new int[][]{{0, 1},
                {-1, 0}, {0, 0}, {1, 0}});
        put("P", new int[][]{{0, 0}, {1, 0},
                {0, -1}, {1, -1},
                {0, -2}});
        put("W", new int[][]{{0, 1}, {1, 1},
                {-1, 0}, {0, 0},
                {-1, -1}});
        put("U", new int[][]{{0, 1}, {1, 1},
                {0, 0},
                {0, -1}, {1, -1}});
        put("F", new int[][]{{0, 1}, {1, 1},
                {-1, 0}, {0, 0},
                {0, -1}});
        put("X", new int[][]{{0, 1},
                {-1, 0}, {0, 0}, {1, 0},
                {0, -1}});
        put("Y", new int[][]{{0, 1},
                {-1, 0}, {0, 0}, {1, 0}, {2, 0}});

    }};

    public Piece(String name, char symbol) {
        this.name = name;
        // duplicate object not reference
        if ((this.shape = shapesTable.get(name).clone()) == null) {
            throw new IllegalArgumentException("");
        }
        this.symbol = symbol;
    }

    public int[][] getShape() {
        return shape;
    }

    public String getName() {
        return name;
    }

    public void resetPiece() {
        shape = shapesTable.get(name).clone();
    }

    public boolean place(Board board, int x, int y, boolean starter){
        return ((starter)? placeStartingPiece(board, x, y) : placeNormalPiece(board, x, y));
    }

    public boolean check(Board board, int x, int y, boolean starter){
        return ((starter)? startPieceCheck(board, x, y) : placeNormalPieceCheck(board, x, y));
    }

    //DON'T PRINT ANY MESSAGES TO SCREEN
    private boolean placeStartingPiece(Board board, int moveToX, int moveToY) {
        //If the first move is valid
        if (startPieceCheck(board, moveToX, moveToY)) {
            //update piece's coordinates and then update the board
            updatePieceShape(moveToX, moveToY);
            placeOnBoard(board);
            return true;
        }
        //if the move is not valid, reset the piece shape and return false
        resetPiece();
        return false;
    }

    private boolean placeNormalPiece(Board board, int moveToX, int moveToY) {
        //If the piece can be placed on the new position
        if (placeNormalPieceCheck(board, moveToX, moveToY)) {
            //update piece's coordinates and then update the board
            updatePieceShape(moveToX, moveToY);
            placeOnBoard(board);
            return true;
        }
        //if the move is not valid, reset the piece shape, return false
        resetPiece();
        return false;
    }

    //check if the move is a valid first move
    private boolean startPieceCheck(Board board, int moveToX, int moveToY) {
        boolean isValid = false; //indicate validation of move, initialise to false
        int offsetX, offsetY; //store new position coordinates
        //for each piece block
        for (int[] pieceBlock: this.shape) {
            //find new positions where each block will be moved to
            offsetX=pieceBlock[0]+moveToX;
            offsetY=pieceBlock[1]+moveToY;
            /*
            red point is at {0,0}
            other points are relevant to the red point
             */

            //if any block move to a non-empty position, return false
            if (!(board.isEmpty(offsetX,offsetY))) {
                return false;
            }

            //if the block is on the starting point, set isValid to true
            if (onStartingPoint(offsetX,offsetY)) {
                isValid=true;
            }
        }

        //check after every block is checked
        //if isValid is not false, return true
        return isValid;
    }

    //used in Players.java, moveValidMove(), keep public
    //check if the move is a valid move
    protected boolean placeNormalPieceCheck(Board board, int moveToX, int moveToY) {
        boolean isValid = false; //indicate validation of move, initialise to false
        int offsetX, offsetY; //store new position coordinates

        //for each piece block
        for (int[] pieceBlock: shape) {
            //find new positions where each block will be moved to
            offsetX=pieceBlock[0]+moveToX;
            offsetY=pieceBlock[1]+moveToY;

            //if any block move to a non-empty position, return false
            if (!(board.isEmpty(offsetX,offsetY))) {
                return false;
            }

            //if the block is adjacent to other piece, return false
            if (isAdjacent(board, offsetX,offsetY)) {
                return false;
            }

            //if touch at the corner of placed piece, set isValid to true
            if (atCorner(board, offsetX,offsetY)) {
                isValid=true;
            }
        }

        //check after every block is checked
        //if isValid is not false, return true
        return isValid;
    }

    //rotate the piece 90 degree clockwise
    public void rotate() {
        //use matrix multiplication on coordinate in pieceShape
        // last version:       int[][] rotateMatrix = new int[][]{{0,1}, {-1,0}}; // for rotation 90 degree clockwise
        int[][] rotateMatrix = new int[][]{{0,-1}, {1,0}}; // for rotation 90 degree clockwise
        //update piece shape
        shape = matrixMultiplication(shape, rotateMatrix);
    }

    //flip the piece along y-axis
    public void flip() {
        //use matrix multiplication on coordinate in pieceShape
        int[][] flipMatrix = new int[][]{{-1,0}, {0,1}}; // for flip along y-axis
        //update piece shape
        shape = matrixMultiplication(shape, flipMatrix);

    }

    //ALL METHODS BELOW WILL BE SET TO PRIVATE AFTER TESTINGS ARE DONE

    //matrix multiplication (used in flip and rotate)
    private int[][] matrixMultiplication(int[][] pieceCoordinate, int[][] transformMatrix) {
        int[][] result = new int[pieceCoordinate.length][transformMatrix[0].length];
        int tmp_Sum;
        //for each coordinates
        for (int i=0; i< pieceCoordinate.length; i++) {
            for (int j=0; j< transformMatrix[0].length; j++) {
                tmp_Sum=0;
                for (int k=0; k<transformMatrix.length; k++) {
                    tmp_Sum += pieceCoordinate[i][k] * transformMatrix[k][j];
                }
                //update value at each position
                result[i][j] = tmp_Sum;
            }
        }
        //return the calculated result
        return result;
    }

    //check if a block is adjacent to another block of the same symbol(color)
    private boolean isAdjacent(Board board, int xCoordinate, int yCoordinate) {
        int[][] positionsAround = new int[][] {{xCoordinate-1, yCoordinate},  //left
                {xCoordinate+1, yCoordinate},  //right
                {xCoordinate, yCoordinate+1},  //top
                {xCoordinate, yCoordinate-1}}; //bottom
        //check if positions around is empty
        for (int[] coordinate:positionsAround) {
            if ((board.inRange(coordinate[0],board.getWidth()) && (board.inRange(coordinate[1], board.getHeight()))) && !(board.isEmpty(coordinate[0], coordinate[1]))) {
                if (board.getCharFromBoard(coordinate[0], coordinate[1]) == symbol) {
                    //if is adjacent to other piece, return true
                    return true;
                }
            }
        }

        //if not returned after the for loop, return false
        return false;
    }

    //check if cover position (4,9) or (9,4)
    private boolean onStartingPoint (int xCoordinate, int yCoordinate) {
        int[] startPoints = new int[2];

        //if player's symbol is 'X', it has to start on (4,9)
        if (symbol=='X') {
            startPoints[0] = 4;
            startPoints[1] = 9;
        }
        //if player's symbol is 'O', force it to start on (9,4)
        if (symbol=='O') {
            startPoints[0] = 9;
            startPoints[1] = 4;
        }


        if (xCoordinate==startPoints[0] && yCoordinate==startPoints[1]) {
            return true;
        } else {
            return false;
        }
    }

    //check if touch the corner of another block with the same symbol
    private boolean atCorner(Board board, int newCoordinateX,int newCoordinateY) {
        //store the coordinate of positions at corners of the block
        int[][] blocksAtCorner = new int[][]{{newCoordinateX-1, newCoordinateY+1},
                {newCoordinateX-1, newCoordinateY-1},
                {newCoordinateX+1, newCoordinateY+1},
                {newCoordinateX+1, newCoordinateY-1}};
        // 0 = top-left: (newCoordinateX-1, newCoordinateY+1)
        // 1 = bottom-left: (newCoordinateX-1, newCoordinateY-1)
        // 2 = top-right: (newCoordinateX+1, newCoordinateY+1)
        // 3 = bottom-right: (newCoordinateX+1, newCoordinateY-1)

        for (int[] coordinate: blocksAtCorner) {
            if ((board.inRange(coordinate[0],board.getWidth()) && (board.inRange(coordinate[1], board.getHeight()))) && !board.isEmpty(coordinate[0], coordinate[1])) {
                //check if the position at the corner is the same color as the piece
                if (board.getCharFromBoard(coordinate[0], coordinate[1]) == symbol) {
                    //if touch corner of other piece, return true
                    return true;
                }
            }
        }

        //if not returned after the for loop
        return false;

    }

    //update the coordinate of each piece
    public void updatePieceShape(int xMoveTo, int yMoveTo) {
        for (int i = 0; i < shape.length ; i++) {
            //reset variable newCoordinate
            int[] newCoordinate = new int[2];
            // get the new X coordinate
            newCoordinate[0] = shape[i][0]+xMoveTo;
            // get the new Y coordinate
            newCoordinate[1] = shape[i][1]+yMoveTo;
            //update coordinate in pieceShape
            shape[i] = newCoordinate;
        }
    }

    //- modify the game-board
    public void placeOnBoard(Board board) {
        //update the board with the symbol
        for (int[] block: shape) {
            board.update(block[0], block[1], symbol);
        }
    }


    @Override
    public String toString() {
        final int MAX = 100;
        StringBuilder outputPiece =  new StringBuilder();
        boolean wholeLineSpace = true;
        boolean wholeColumnSpace = true;
        int countEmptyCol=0;
        char[][] str = new char[MAX][MAX];

        //initialise with ' '
        for (int row=0; row<str.length; row++) {
            for (int col=0; col<str[0].length; col++) {
                str[row][col]=' ';
            }
        }

        //initialise with coordinate in pieceShape

        for (int[] block:shape) {
            if (block[0]==0 && block[1]==0) {
                str[(block[1]+20)][(block[0]+20)] = '*';
            } else {
                str[(block[1] + 20)][(block[0] + 20)] = symbol;
            }
        }

        //print out to screen
        //check for empty columns on the left
        for (int col=0; col<str[0].length; col++) {
            for (int n = str.length-1; n >=0; n--) {
                if (str[n][col]!=' ' ) {
                    wholeColumnSpace=false;
                }
            }
            if (wholeColumnSpace) {
                countEmptyCol++;
            } else {
                break;
            }
        }

        //check for empty lines
        for (int i=str.length-1; i>=0; i--) {
            wholeLineSpace=true;
            wholeColumnSpace=true;
            for (char block:str[i]) {
                if (block != ' ') {
                    wholeLineSpace=false;
                }
            }
            if (!wholeLineSpace) {
                for (int j = countEmptyCol; j<str[i].length; j++) {
                    outputPiece.append(String.format("%2c",str[i][j]));
                }
                outputPiece.append("\n");
            }

        }

        return outputPiece.toString();
    }
}
