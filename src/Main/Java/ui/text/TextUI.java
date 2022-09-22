/**
 * Group name: IcyTea
 * Members:
 * Cathal Poon (20343243)
 * YanHao Sun (19205434)
 * Rubing Wang (20206497)
 */

package ui.text;

import java.util.Scanner;

import gameControl.*;
import ui.UI;

public class TextUI implements UI {
//for taking inputs from the user

    private static Scanner in;

    //constructor
    public TextUI() {
        if (in == null) {
            in = new Scanner(System.in);
        }
    }


    @Override
    public String next() {
        return in.next();
    }


    @Override
    public String readLine(String prompt) {
        System.out.println(prompt);
        return in.nextLine();
    }

    @Override
    public int[] getCoordinates(String prompt) {

        // keep asking until a valid answer is given
        while (true) {
            try {
                System.out.println(prompt);
                return getXY();
            } catch (NumberFormatException exception) {
                // ignore and ask again
                System.out.println("Issue");
            } finally {
                in.skip("(\\r\\n|[\\n\\r\\u2028\\u2029\\u0085])?"); // skip linebreak and line separator stuff
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public char getOperation(String prompt) {
        char buffer;
        while (true) {
            System.out.println(prompt);
            buffer = next().toUpperCase().charAt(0);
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

    @Override
    public void print(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void endScreen(String str) {
        print(str);
    }

    @Override
    public void updateBoard(Board brd) {
        print(brd);
    }

    @Override
    public void setGameState(GameState state) {

    }

    @Override
    public void viewPieces(PieceList list) {
        print(list);
    }

    @Override
    public void viewPiece(Piece piece) {
        print(piece);
    }

    @Override
    public void error(String str) {
        System.err.println(str);
    }

    @Override
    public void log(Object object){
        System.out.println(object);
    }

    @Override
    public void prompt(Players players) {
        print(String.format("%s please pick a piece", players.getPlayerName()));
    }

    private int nextInt() {
        return Integer.parseInt(next());
    }

    private int[] getXY() {
        int x, y;

        x = nextInt();
        y = nextInt();
        return new int[]{x, y};

    }

    @Override
    public void startPlayer2FirstTurn() {
    }
}
