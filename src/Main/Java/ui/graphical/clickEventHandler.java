package ui.graphical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import gameControl.GameState;

public class clickEventHandler extends InputAdapter {
    blokusGameGUI game;
    PieceGUI selectedPiece;
    BoardGUI graphicalBoard;

    GameState tmpState;

    public clickEventHandler(blokusGameGUI game) {
        this.game = game;
        this.selectedPiece = null;
        this.graphicalBoard = game.graphicalBoard;
        this.tmpState = GameState.PLAYER1;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean eventHandled = false;

        if (null != selectedPiece) {
            selectedPiece.reset();
            selectedPiece = null;
        }
        System.out.println("in touchDown, state = "+game.state);
        int i = 0; //indicate which player is placing
        //need to control players' turn, black piece move first
       if (game.state == GameState.PLAYER1 || game.startPlayer2 == false) {
           i=0;
        } else if (game.state == GameState.PLAYER2 ||game.state == GameState.START){
            i=21;
        }
        int upperBound = i+21;

        if (button == Input.Buttons.LEFT) {
            Vector3 coord = unprojectScreenCoordinates(Gdx.input.getX(),Gdx.input.getY());
            PieceGUI[] gamepiece = game.graphicalGamepiece;
            for (; i<upperBound; i++) {
                if (gamepiece[i].isHit(coord.x, coord.y)) {
                    selectedPiece = gamepiece[i];
                    //pass name of selected piece to game control
                    selectedPiece.choosePiece();
                    game.setBannerText(blokusGameGUI.FLIP_OR_ROTATE_MESSAGE);
                    eventHandled = true;
                }
            }
        }
        return eventHandled;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        boolean eventHandled = false;
        if (null != selectedPiece) {
            Vector3 coord = unprojectScreenCoordinates(screenX,screenY);
            int boardColumn = graphicalBoard.getBoardColumn(coord.x);
            int boardRow = graphicalBoard.getBoardRow(coord.y);
            if (graphicalBoard.isHit(coord.x,coord.y) &&
                    (graphicalBoard.isValid(game.state, selectedPiece.piece, boardColumn, boardRow))) {
                // send coordinates of the board location chosen by the user to the game control.
                // This action will "unfreeze" game control thread waiting for input in GuiPlayer.getMove()
                //pass back the 'p' and coordinate
                System.out.println(String.format("%d %d", boardColumn, boardRow));
                selectedPiece.setPosition(boardColumn,boardRow);
                selectedPiece.setVisible(false);
            } else {
                selectedPiece.reset();
                game.UIStream.println('Z');
            }
            selectedPiece = null;
        }
        return eventHandled;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        boolean eventHandled = false;
        if (null != selectedPiece) {
            Vector3 coord = unprojectScreenCoordinates(screenX,screenY);
            selectedPiece.showPosition(coord.x,coord.y);
            eventHandled = true;
        }
        return eventHandled;
    }

    Vector3 unprojectScreenCoordinates(int x, int y) {
        Vector3 screenCoordinates = new Vector3(x, y,0);
        return game.camera.unproject(screenCoordinates);
    }

    @Override
    public boolean keyDown (int keycode) {
        boolean eventHandled = false;
        if (null != selectedPiece) {
            switch(keycode) {
                case Input.Keys.F:
                    selectedPiece.flip();
                    eventHandled = true;
                    break;
                case Input.Keys.R:
                    selectedPiece.rotate();
                    eventHandled = true;
                    break;
            }
        }
        return eventHandled;
    }
}
