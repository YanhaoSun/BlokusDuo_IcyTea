package ui.graphical;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class StartScreen extends ScreenAdapter {

    blokusGameGUI blokusGameGUI;

    Stage stage;
    Skin skin;
    Table table;

    public StartScreen(blokusGameGUI blokusGameGUI) {
        this.blokusGameGUI = blokusGameGUI;
        this.stage = blokusGameGUI.stage;
        this.skin = blokusGameGUI.skinHelloGame;

        table = new Table();
        table.setFillParent(true);

        //add Blokus Duo
        Label label = new Label("Blokus Duo",skin,"font", Color.BLACK );
        table.add(label).pad(10);
        table.row();

        //for player 1
        Label player1 = new Label("Enter the name of Player 1",skin,"font", Color.BLACK );
        table.add(player1).pad(10);
        TextField textFieldPlayer1 = new TextField(null,skin);
        textFieldPlayer1.setMessageText("John");
        table.add(textFieldPlayer1).pad(10);
        table.row();

        //for player 2
        Label player2 = new Label("Enter the name of Player 2",skin,"font", Color.BLACK );
        table.add(player2).pad(10);
        TextField textFieldPlayer2 = new TextField(null,skin);
        textFieldPlayer2.setMessageText("Mary");
        table.add(textFieldPlayer2).pad(10);
        table.row();


        TextButton textButton = new TextButton("Start Game",skin);
        table.add(textButton).pad(10);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String namePlayer1 = textFieldPlayer1.getText();
                String namePlayer2 = textFieldPlayer1.getText();
                if (namePlayer1.isEmpty()) {
                    namePlayer1 = textFieldPlayer1.getMessageText();
                }
                if (namePlayer2.isEmpty()) {
                    namePlayer2 = textFieldPlayer2.getMessageText();
                }
                // send name to game control thread
                blokusGameGUI.UIStream.println(namePlayer1);
                blokusGameGUI.UIStream.println(namePlayer2);
            };
        });
        table.row();


    }

    public void show() {
        super.show();
        stage.addActor(table);
    }

    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.WHITE);
        stage.act(delta);
        stage.draw();
    }

    public void hide() {
        super.hide();
        stage.clear();
    }

    public void resize(int width, int height) {
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
    }
}
