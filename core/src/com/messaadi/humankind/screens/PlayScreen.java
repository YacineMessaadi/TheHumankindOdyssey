package com.messaadi.humankind.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.messaadi.humankind.HumankindOdyssey;
import com.messaadi.humankind.models.Main;
import com.messaadi.humankind.scenes.Hud;

public class PlayScreen implements Screen {

    private HumankindOdyssey game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private Main main;

    public PlayScreen(HumankindOdyssey game) {
        this.game = game;
        main = new Main();
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(HumankindOdyssey.V_WIDTH, HumankindOdyssey.V_HEIGHT, gameCam);
        hud = new Hud(game.batch, main, this);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        game.batch.begin();
        game.batch.draw(new Texture("bg.jpg"), 0, 0);
        game.batch.end();
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        hud.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
