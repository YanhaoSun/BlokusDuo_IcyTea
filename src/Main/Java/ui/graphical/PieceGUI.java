package ui.graphical;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import gameControl.Piece;

public class PieceGUI {
    blokusGameGUI blokusGameGUI;

    private final TextureRegion square;
    int[][] locations;
    Piece piece;

    private float originX; // world X coordinate of the gamepiece's origin
    private float originY; // world Y coordinate of the gamepiece's origin
    private final float homeX;
    private final float homeY;
    boolean visible;

    public PieceGUI(blokusGameGUI blokusGameGUI, Piece gamepiece, TextureRegion square, float originX, float originY) {
        this.blokusGameGUI = blokusGameGUI;
        this.piece = gamepiece;
        this.locations = gamepiece.getShape();
        this.square = square;
        this.originX = originX;
        this.originY = originY;
        this.homeX = originX;
        this.homeY = originY;
        this.visible = true;
    }

    public void draw(SpriteBatch batch) {
        if (!visible) return;
        for (int[] l : locations) {
            batch.draw(square, originX + square.getRegionWidth() * l[0], originY + square.getRegionHeight() * l[1]);
        }
    }

    public boolean isHit(float x, float y) {
        boolean isHit = false;
        for (int[] l : locations) {
            //use contains() to check the position of mouse click
            Rectangle rectangle = new Rectangle(originX + l[0] * square.getRegionWidth(),
                    originY + l[1] * square.getRegionHeight(),
                    square.getRegionWidth(),
                    square.getRegionHeight());
            if (rectangle.contains(x, y)) {
                isHit = true;
            }
        }
        return isHit;
    }

    public void setPosition(float x, float y) {
        placePiece(x, y);
        // broken code
        /*
        originX = x - square.getRegionWidth() * 0.5f;
        originY = y - square.getRegionHeight() * 0.5f;
        */
    }

    public void placePiece(float x, float y) {
        String out = String.format("p %d %d", (int)x, (int)y);
        System.out.println(out);
        blokusGameGUI.UIStream.println(out);
        /*blokusGameGUI.UIStream.println("p");
        blokusGameGUI.UIStream.println(y);
        blokusGameGUI.UIStream.println(x);*/
    }

    public void showPosition(float x, float y) {
        originX = x - square.getRegionWidth() * 0.5f;
        originY = y - square.getRegionHeight() * 0.5f;
    }

    public void reset() {
        originX = homeX;
        originY = homeY;
        piece.resetPiece();
        setLocations();
    }

    public void choosePiece() {
        System.out.println(piece.getName());
        blokusGameGUI.UIStream.println(piece.getName());
    }

    public void flip() {
        piece.flip();
        setLocations();
    }

    public void rotate() {
        piece.rotate();
        setLocations();
    }

    private void setLocations() {
        locations = piece.getShape();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
