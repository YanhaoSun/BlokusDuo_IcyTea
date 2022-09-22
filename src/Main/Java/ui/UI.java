package ui;

import gameControl.*;

public interface UI {
    String next();
    String readLine(String prompt);
    int[] getCoordinates(String prompt);
    char getOperation(String prompt);
    void print(Object obj);
    void endScreen(String str);
    void updateBoard(Board brd);
    void setGameState(GameState state);
    void startPlayer2FirstTurn();
    void viewPieces(PieceList list);
    void viewPiece(Piece piece);
    void error(String str);
    void log(Object obj);
    void prompt(Players players);
    //public String getName();
//    public String getName1();
//    public void displayGreeting(String greeting);
}
