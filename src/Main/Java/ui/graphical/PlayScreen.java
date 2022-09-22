package ui.graphical;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class PlayScreen extends ScreenAdapter {
    blokusGameGUI blokusGame;

    TiledMapRenderer mapRenderer;

    SpriteBatch batch;
    OrthographicCamera camera;

    TextureRegion blackSquare;
    TextureRegion whiteSquare;

    BitmapFont helvetique;

    float bannerX;
    float bannerY;

    Stage stage;
    Skin skin;

    public PlayScreen(blokusGameGUI blokusGame) {
        this.blokusGame = blokusGame;
        this.stage = blokusGame.stage;
        this.skin = blokusGame.skinHelloGame;
        this.batch = blokusGame.batch;
        this.camera = blokusGame.camera;
        this.blackSquare = blokusGame.blackSquare;
        this.whiteSquare = blokusGame.whiteSquare;
        this.mapRenderer = blokusGame.mapRenderer;
        this.helvetique = blokusGame.helvetique;
        this.bannerX = blokusGame.bannerX;
        this.bannerY = blokusGame.bannerY;
    }

    public void setBanner(String text) {
        blokusGame.bannerText = text;
    }


    public void show() {
        super.show();
    }

    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.WHITE);
        //set window size and camera
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth()  * 0.5f, Gdx.graphics.getHeight() * 0.5f, 0.0f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.act(delta);
        stage.draw();

        //draw tiles map
        mapRenderer.setView(camera);
        mapRenderer.render();

        //draw game piece and banner
        batch.begin();
        for (PieceGUI piece: blokusGame.graphicalGamepiece) {
            piece.draw(batch);
        }

        blokusGame.graphicalBoard.draw(batch);
//        batch.draw(blokusGame.imageLayer.getObjects("Image Layer"));
        helvetique.draw(batch, blokusGame.bannerText, bannerX, bannerY);
        batch.end();
    }

    public void hide() {
        super.hide();
        stage.clear();
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

}