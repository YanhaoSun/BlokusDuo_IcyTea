package ui.graphical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import gameControl.*;
import ui.UI;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class GUI implements UI {

    private PipedOutputStream pipedOutputStream;
    private PipedInputStream pipedInputStream;
    private blokusGameGUI blokusGameGUI;
    private final Scanner scanner;

    public GUI() throws IOException {
        pipedOutputStream = new PipedOutputStream();
        pipedInputStream = new PipedInputStream(pipedOutputStream);
        scanner = new Scanner(pipedInputStream);
    }

    void setGame(blokusGameGUI blokusGameGUI) {
        this.blokusGameGUI = blokusGameGUI;
    }

    public PipedOutputStream getPipedOutputStream() {
        return pipedOutputStream;
    }

    public String getName() {
        return scanner.next();
    }

    public void addPieceSet(Players player1, Players player2) {
        blokusGameGUI.storeTileMapCoordinate(player1, player2);
    }

    public void passBoard(Board board) {
        blokusGameGUI.storeTileMapBoardObject(board);

    }

    public void getMove(Players player) {
        // pass piece's new board coordinate to game control thread
    }

    public void setGameState(GameState state){
        blokusGameGUI.postRunnable(new Runnable() {
            @Override
            public void run() {
                blokusGameGUI.setGameState(state);
            }
        });
    }


    // tell GUI to draw update the pieces
    @Override
    public void viewPieces(PieceList list) {

    }

    @Override
    public void viewPiece(Piece piece) {
        System.out.println(piece);
    }

    @Override
    public void error(String str) {
        System.err.println(str);
    }

    @Override
    public void log(Object obj){
        System.out.println(obj);
    }

    @Override
    public void prompt(Players players) {
        print(String.format("%s click and drag on a piece.", players.getPlayerName()));
    }


    @Override
    public String next() {
        return scanner.next();
    }

    @Override
    public String readLine(String prompt) {
        return scanner.nextLine();
    }

    @Override
    public int[] getCoordinates(String prompt) {

        // keep asking until a valid answer is given
        while (true) {
            try {
                return getXY();
            } catch (NumberFormatException exception) {
                // ignore and ask again
            } finally {
                scanner.skip("(\\r\\n|[\\n\\r\\u2028\\u2029\\u0085])?"); // skip linebreak and line separator stuff
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public char getOperation(String prompt) {
        char buffer;
        while (true) {
            buffer = (scanner.next()).toUpperCase().charAt(0);
            switch (buffer) {
                case 'R':
                case 'F':
                case 'P':
                case 'Q':
                case 'N':
                case 'Z':
                    return buffer;
                default:
                    break;
            }
        }
    }

    /**
     * Display a text representation of an object
     * Calls the toString() method on the object to get the text representation
     *
     * @param obj object to be printed
     */
    @Override
    public void print(Object obj) {
        blokusGameGUI.setBannerText(obj.toString());
    }

    @Override
    public void endScreen(String str) {
        float delay = 2; //hold play screen for 2 seconds
        blokusGameGUI.postRunnable(new Runnable() {
            @Override
            public void run() {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        blokusGameGUI.activateEndScreen();
                        blokusGameGUI.showEndResult(str);
                    }
                }, delay);
            }
        });
    }

    @Override
    public void updateBoard(Board brd) {
        System.out.println(brd);
        passBoard(brd);
    }

    private int nextInt() {
        return Integer.parseInt(scanner.next());
    }

    private int[] getXY() {
        int x, y;

        x = nextInt();
        y = nextInt();
        return new int[]{x, y};

    }

    public void displayStarter(String text) {
        blokusGameGUI.postRunnable(new Runnable() {
            @Override
            public void run() {
                blokusGameGUI.showDialog(text);
            }
        });
    }

    @Override
    public void startPlayer2FirstTurn() {
        blokusGameGUI.startPlayer2FirstTurn();
    }
}
