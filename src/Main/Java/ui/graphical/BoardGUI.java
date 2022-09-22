package ui.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import gameControl.Board;
import gameControl.GameState;
import gameControl.Piece;

public class BoardGUI {
    float boardX;
    float boardY;
    float boardWidth;
    float boardHeight;
    float cellWidth;
    float cellHeight;
    TextureRegion blackSquare;
    TextureRegion whiteSquare;
    Board board;

    public BoardGUI(Board board, TextureRegion blackSquare, TextureRegion whiteSquare, float boardX, float boardY, float boardWidth, float boardHeight) {
        this.boardX = boardX;
        this.boardY = boardY;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.blackSquare =blackSquare;
        this.whiteSquare = whiteSquare;
        this.board = board;
        cellHeight = boardHeight / board.getHeight();
        cellWidth = boardWidth / board.getWidth();
    }

    public void draw(SpriteBatch batch) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (!board.isEmpty(x, y)) {
                    TextureRegion picture;
                    picture = (board.getCharFromBoard(x,y) == 'X') ? blackSquare : whiteSquare;
                    batch.draw(
                            picture,
                            (boardX + x * cellWidth + cellWidth * -0f),
                            (boardY + y * cellHeight  + cellHeight * -0f)
                    );
                }
            }
        }
    }

    public int getBoardColumn(float x) {
        int result = -1;
        if ((boardX < x) && ((boardX + boardWidth) > x)) {
            result = (int)((x - boardX) / cellWidth);
        }
        return result;
    }

    public int getBoardRow(float y) {
        int result = -1;
        if ((boardY < y) && ((boardY + boardHeight) > y)) {
            result = (int)((y - boardY) / cellHeight);
        }
        return result;
    }

    public boolean isHit(float x, float y) {
        return  (getBoardColumn(x) != -1) && (getBoardRow(y) != -1);
    }

    public boolean isOccupied(int boardColumn, int boardRow) {
        return !board.isEmpty(boardColumn,boardRow);
    }

    public boolean isValid(GameState state, Piece piece, int x, int y){
        if (state == GameState.START){
            return piece.check(board, x, y, true);
        }else {
            return piece.check(board, x, y, false);
        }
    }


}
