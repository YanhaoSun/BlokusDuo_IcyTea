package ui.graphical;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen extends ScreenAdapter {

    blokusGameGUI blokusGameGUI;

    Stage stage;
    Skin skin;

    public EndScreen(blokusGameGUI blokusGameGUI) {
        this.blokusGameGUI = blokusGameGUI;
        this.stage = blokusGameGUI.stage;
        this.skin = blokusGameGUI.skinHelloGame;

    }


    public void show() {
        super.show();
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
