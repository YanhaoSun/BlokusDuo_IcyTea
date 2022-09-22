/**
 * Group name: IcyTea
 * Members:
 * Cathal Poon (20343243)
 * YanHao Sun (19205434)
 * Rubing Wang (20206497)
 */

package gameControl;

import ui.UI;
import ui.graphical.GUI;
import ui.text.TextUI;

public class GameHandler implements Runnable {
    UI ui;
    char startFirstSymbol;

    @Override
    public void run() {
        init();
    }

    //a game have 2 players, 1 board and 1 state
    private Players player1, player2;
    private final Board currentBoard;
    private GameState currentState;

    // store the skip state of the game
    private int skip = 0;

    //constructor
    public GameHandler() {
        this(new TextUI(), 'X');
    }

    public GameHandler(UI ui, char symbol) {

        this.ui = ui;
        this.startFirstSymbol = symbol;

        currentBoard = new Board();         // set up a board
        currentState = GameState.START;     // set game states to START

    }

    private void setPlayers(char symbol){
        final char x = 'X', o = 'O';

        //ScannerStuff in = new ScannerStuff()
        switch (symbol) {
            case o:
                player1 = new Players(o, this.ui.readLine("Please enter a name")); // 'O' to represent player2's pieces
                player2 = new Players(x, this.ui.readLine("Please enter a name")); // 'X' to represent player2's pieces
                break;
            case x:
                player1 = new Players(x, this.ui.readLine("Please enter a name")); // 'O' to represent player2's pieces
                player2 = new Players(o, this.ui.readLine("Please enter a name")); // 'X' to represent player2's pieces
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + symbol);
        }

    }

    // player requires some error checking (add later)

    // For now, game will finish after player2Turn
    // player1Turn and player2Turn is not implemented as it is not required for assignment 1,
    // only print message will show (to indicate functioning of switch and state) when player1Turn and player2Turn are called
    public void init() {
        setPlayers(startFirstSymbol);
        if (ui instanceof GUI){
            ((GUI) ui).displayStarter(player1.getPlayerName());
            ((GUI) ui).addPieceSet(player1, player2);
            ((GUI) ui).passBoard(currentBoard);
        }

        // ui = new TextUI(); // change to text based cli interface
        gate:
        while (true) {
            switch (currentState) {
                case START:
                    ui.setGameState(currentState);
                    playerStart(player1); // player1 place starting piece
                    ui.startPlayer2FirstTurn();
                    playerStart(player2); // player2 place starting piece
                    // do stuff
                    //System.out.println("Starting Location"); //indicate end of placing first piece
                    currentState = GameState.PLAYER1; // to next state
                    break;
                case PLAYER1:
                    ui.setGameState(currentState);
                    playerTurn(player1);// give player 1 a turn
                    // do stuff
                    currentState = GameState.PLAYER2; // next player
                    break;
                case PLAYER2:
                    ui.setGameState(currentState);
                    playerTurn(player2);// give player 2 a turn
                    // do stuff
                    currentState = GameState.PLAYER1; // next player
                    break;
                case END:
                    ui.setGameState(currentState);
                    endGame();  // finish up the game
                    break gate; // exit the while loop
                default:
                    throw new IllegalStateException("Unexpected value: " + currentState);
            }
            // At the end of a round check if both players were skipped, if so end the game
            if (skip == 2){
                currentState = GameState.END;
            }

//            ui.endScreen("xxooxx");
        }

        // show final stuff before exiting
    }

    private void playerStart(Players player) {
        playerTurn(player, true);
    }

    //follow method will only print message on screen to indicate method called successfully


    private void playerTurn(Players player) {
        playerTurn(player, false);
    }

    private void playerTurn(Players player, boolean firstPiece) {
        enum TurnState {
            SELECTION, // picking a piece state
            TRANSFORMATION, // rotating flipping state
            PLACEMENT, // picking a location state
            END // cleanup
        }

        //ScannerStuff in = new ScannerStuff();
        TurnState turnState = TurnState.SELECTION; // set starting case

        if (player.isSkipped()){
            return;
        }

        //check if there are no more move possible, change state to END
        if (!firstPiece && !(player.moreMovesAvailable(currentBoard))) {
            player.setSkipped(true);
            skip++;
            return;
        }

        while (true) {
            switch (turnState) {
                case SELECTION:
                    // get users input
                    // check user input
                    // if valid go to next state
                    // else do nothing
                    ui.updateBoard(currentBoard);
                    ui.viewPieces(player.getPieceList());
                    ui.prompt(player);
                    String buffer = ui.next().toUpperCase();

                    if (    // needs to be changed to select piece
                            player.selectPiece(buffer) // get user input
                    ) {
                        ui.viewPiece(player.getPieceHolding());
                        turnState = TurnState.TRANSFORMATION;
                    }

                    break;
                case TRANSFORMATION:
                    // get user input
                    // check user input
                    // if transformation do it
                    // if place go to next state
                    // if unknown or invalid ask again (dealt with in scanner stuff)
                    char charBuffer = ui.getOperation("Enter 'R' for rotate, 'F' for flip, 'P' for placement ");

                    switch (charBuffer) {
                        case 'F':
                            // flip piece
                            player.getPieceHolding().flip();
                            ui.viewPiece(player.getPieceHolding());
                            break;
                        case 'R':
                            // rotate piece
                            player.getPieceHolding().rotate();
                            ui.viewPiece(player.getPieceHolding());
                            break;
                        case 'P':
                            // place piece
                            turnState = TurnState.PLACEMENT;
                            break;
                        case 'Z':
                            // reset turn
                            player.getPieceHolding().resetPiece();
                            turnState = TurnState.SELECTION;
                            break;
                        default:
                            // unknown case
                            System.err.printf("Unknown operation : %c \n", charBuffer);
                    }
                    break;
                case PLACEMENT:
                    // get co-ord
                    // check co-ord
                    // if valid place, go to next state
                    // else go back to SELECTION
                    int[] coords = ui.getCoordinates("Enter the X and Y coordinates:");


                        if (player.getPieceHolding().place(currentBoard, coords[0], coords[1], firstPiece)) {
                            turnState = TurnState.END;
                            player.updateScore();
                            player.updatePieceList();
                        } else {
                            ui.print("Invalid Location");
                            turnState = TurnState.SELECTION;
                        }
                    break;
                case END:
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + turnState);
            }

        }
    }

    private void endGame() {
        ui.updateBoard(currentBoard);
        int comp = Integer.compare(player1.getScore(), player2.getScore());

        if (comp > 0){
            ui.endScreen(String.format("Game OVER! %s won", player1.getPlayerName()));
        }else if (comp < 0){
            ui.endScreen(String.format("Game OVER! %s won", player2.getPlayerName()));
        }else {
            ui.endScreen("Game OVER! TIE");
        }
        ui.print(String.format("[%s : %s]", player1.toString(), player2.toString()));

    }

}
