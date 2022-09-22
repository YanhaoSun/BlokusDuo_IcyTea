package ui.graphical;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import gameControl.Board;
import gameControl.GameState;
import gameControl.Players;
import ui.UI;

import java.io.PrintStream;

public class blokusGameGUI extends Game {
    //size of graphic user interface window
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 812;

    public final static String CLICK_AND_DRAG_MESSAGE = "Click and drag the game piece.";
    public final static String FLIP_OR_ROTATE_MESSAGE = "Press 'f' to flip, or 'r' to rotate the game piece.";

    // blokusGameGUI game; // replaced with 'this' keyword
    Thread gameControlThread;
    PrintStream UIStream;
    SpriteBatch batch;

    OrthographicCamera camera;
    Stage stage;
    Skin skinHelloGame;
    Skin skinMySkin;

    TiledMap tiledMap;
    MapObjects mapObjects;
    TiledMapRenderer mapRenderer;
    TiledMapImageLayer imageLayer;

    Screen startScreen;
    Screen playScreen;
    Screen endScreen;

    TextureRegion blackSquare;
    TextureRegion whiteSquare;

    BitmapFont helvetique;

    String bannerText;

    GameState state;

    float bannerX;
    float bannerY;

    //store coordinates of all black object
    float[] gamepieceXBlack = new float[21];
    float[] gamepieceYBlack = new float[21];
    //store coordinates of all white object
    float[] gamepieceXWhite = new float[21];
    float[] gamepieceYWhite = new float[21];

    PieceGUI[] graphicalGamepiece = new PieceGUI[42];
    BoardGUI graphicalBoard;

    clickEventHandler eventHandler;

    boolean startPlayer2 = false;

    public blokusGameGUI(Thread gameControlThread, UI ui) {
        GUI gui = (GUI)ui;
        this.gameControlThread = gameControlThread;

        // establishing communication between GameControl thread and HelloGame (libGDX thread)
        // control -> game
        gui.setGame(this); // for posting runnables into libGDX game loop
        // game -> control
        this.UIStream = new PrintStream(gui.getPipedOutputStream()) ;  // for sending text to control

    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.setToOrtho(false);
        //this may have a problem....
        stage = new Stage(new FitViewport(WIDTH,HEIGHT,camera));

        skinHelloGame = new Skin(Gdx.files.internal("HelloGameSkin.json"));

        startScreen = new StartScreen(this);

        tiledMap = new TmxMapLoader().load("play.tmx");
        imageLayer = (TiledMapImageLayer) tiledMap.getLayers().get(0);
        MapLayer objectLayer = tiledMap.getLayers().get(2);

        mapObjects = objectLayer.getObjects();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        skinMySkin = new Skin(Gdx.files.internal("myskin.json"));
        blackSquare = skinMySkin.getRegion("game_square_black");
        whiteSquare = skinMySkin.getRegion("game_square_white");

        helvetique = skinMySkin.getFont("helvetique");
        bannerText = "";
        bannerX = 10.0f;
        bannerY = imageLayer.getTextureRegion().getRegionHeight() + helvetique.getCapHeight()*1.5f;


        playScreen = new PlayScreen(this);
        endScreen = new EndScreen(this);

        activateStartScreen();
    }

    public void activateStartScreen() {
        Gdx.input.setInputProcessor(stage);
        setScreen(startScreen);
    }

    public void activateEndScreen() {
        Gdx.input.setInputProcessor(stage);
        setScreen(endScreen);
    }

    public void setGameState(GameState state){
        this.state = state;
    }

    public void setBannerText(String text) {
        ((PlayScreen) playScreen).setBanner(text);
    }

    //get the coordinate of the objects on the tiled map which should be mapped to pieces
    // and link tiled map object with the texture
    //construct graphical gamepiece object for each piece
    public void storeTileMapCoordinate(Players player1, Players player2) {
        //player1 is always black, player2 is always white
        int count=0;
        for (String piece:player1.getPieceList().getPiecesAvailable()) {
            gamepieceXBlack[count] = (float) (mapObjects.get("0"+piece)).getProperties().get("x");
            gamepieceYBlack[count] = (float) (mapObjects.get("0"+piece)).getProperties().get("y");
            blackSquare.setRegion(new TextureRegion(blackSquare, 0, 0, 32, 32));
            graphicalGamepiece[count] = new PieceGUI(this, player1.getPieceList().getPiecesAvailableElement(piece),blackSquare,gamepieceXBlack[count],gamepieceYBlack[count]);
            count++;
        }

        //get the coordinate of the board block objects on the tiled map and link tiled map object with the texture
        //construct graphical board block
        count=0;
        for (String piece:player2.getPieceList().getPiecesAvailable()) {
            gamepieceXWhite[count] = (float) (mapObjects.get("1"+piece)).getProperties().get("x");
            gamepieceYWhite[count] = (float) (mapObjects.get("1"+piece)).getProperties().get("y");
            graphicalGamepiece[count+21] = new PieceGUI(this, player2.getPieceList().getPiecesAvailableElement(piece),whiteSquare,gamepieceXWhite[count],gamepieceYWhite[count]);
            count++;
        }
    }

    public void storeTileMapBoardObject(Board board) {
        graphicalBoard = new BoardGUI(board, blackSquare, whiteSquare, (float) (mapObjects.get("Board")).getProperties().get("x"), (float) (mapObjects.get("Board")).getProperties().get("y"), (float) (mapObjects.get("Board")).getProperties().get("width"), (float) (mapObjects.get("Board")).getProperties().get("height"));
        this.eventHandler = new clickEventHandler(this);
    }

    public void postRunnable(Runnable r) {
        Gdx.app.postRunnable(r);
    }

    public void showDialog(String playerName) {
        Dialog dialog = new Dialog("Attention", skinHelloGame, "dialog") {
            @Override
            protected void result(Object object) {
                Gdx.input.setInputProcessor(eventHandler);
                setScreen(playScreen);
            }
        };
        dialog.text(String.format("Player %s goes first!", playerName));
        dialog.button("Let's Play!");
        dialog.getContentTable().pad(10);
        dialog.getButtonTable().pad(10);
        dialog.show(stage);
    }

    public void showEndResult(String text) {
        Dialog dialog = new Dialog("END", skinHelloGame, "dialog");
        dialog.text(String.format(text));
        dialog.button("CLOSE");
        dialog.getContentTable().pad(10);
        dialog.getButtonTable().pad(10);
        dialog.show(stage);
    }

    public void startPlayer2FirstTurn() {
        this.startPlayer2 = true;
    }
    @Override
    public void dispose() {
        super.dispose();
        if (startScreen != null) startScreen.dispose();
        if (playScreen != null) playScreen.dispose();
        if (skinHelloGame != null) skinHelloGame.dispose();
        if (skinMySkin != null) skinMySkin.dispose();
        if (stage != null) stage.dispose();
        if (gameControlThread != null) gameControlThread.stop();
    }
}
