/**
 * Group name: IcyTea
 * Members:
 *  Cathal Poon (20343243)
 *  YanHao Sun (19205434)
 *  Rubing Wang (20206497)
 */

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import gameControl.GameHandler;
import ui.UI;
import ui.graphical.GUI;
import ui.graphical.blokusGameGUI;
import ui.text.TextUI;

import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        UI ui;
        Runnable gameControl;
        Thread gameControlThread;
        Application app = null;

        char startFirstSymbol = ' ';
        boolean randomChoose = true;
        boolean useGUI = false;
        System.out.println("Blokus Duo by IcyTea");
        for (String arg : args) {
            switch (arg) {
                case "-X":
                    System.out.println("player (X) play first, start at x=4, y=9");
                    startFirstSymbol='X';
                    randomChoose = false;
                    break;
                case "-O":
                    System.out.println("player (O) play first, start at x=9, y=4");
                    startFirstSymbol='O';
                    randomChoose = false;
                    break;
                case "-gui":
                    useGUI = true;
                    break;
                default:
                    System.out.println("unexpected argument, program will chose a player to start with");
                    randomChoose=true;
                    break;
            }
        }

        //random choose if needed
        if (randomChoose) {
            System.out.println("program will chose a player to start with");
            Random randomNumber = new Random();
            if ((randomNumber.nextInt(3)%2 == 0)) {
                System.out.println("player (X) play first, start at x=4, y=9");
                startFirstSymbol='X';
            } else {
                System.out.println("player (O) play first, start at x=9, y=4");
                startFirstSymbol='O';
            }
        }

        //if '-gui' is in the command line, set ui to GUI(), else set to TextUI()
        if (useGUI) {
            ui = new GUI();
        } else {
            ui = new TextUI();
        }

        //set up game thread
        gameControl = new GameHandler(ui, startFirstSymbol);
        gameControlThread = new Thread(gameControl);
        gameControlThread.start();

        //if '-gui' is chosen, create a libGDX game
        if (useGUI) {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            //set the size of graphic user interface window
            config.setWindowedMode(blokusGameGUI.WIDTH,blokusGameGUI.HEIGHT);
            config.setResizable(false);
            app = new Lwjgl3Application(new blokusGameGUI(gameControlThread,ui), config);
        }

        try {
            gameControlThread.join();
            if (app != null) {
                app.exit();
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}
