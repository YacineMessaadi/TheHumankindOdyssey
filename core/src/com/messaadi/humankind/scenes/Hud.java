package com.messaadi.humankind.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.messaadi.humankind.HumankindOdyssey;
import com.messaadi.humankind.models.Main;
import com.messaadi.humankind.screens.PlayScreen;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private PlayScreen ps;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;
    private int taps = 0;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;

    private Table buttons;
    private Table tableTitles;
    private Table table;
    private Table tableNotifs;

    public Main main;
    public SpriteBatch sb;

    Label countdownLabel, scoreLabel, timeLabel, levelLabel, QiLabel, popLabel, foodLabel, foodLevel;

    public Hud(final SpriteBatch sb, final Main main, PlayScreen ps) {
        this.sb = sb;
        this.main = main;
        this.ps = ps;
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(HumankindOdyssey.V_WIDTH, HumankindOdyssey.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        tableTitles = new Table();
        tableTitles.top();
        tableTitles.setFillParent(true);

        table = new Table();
        table.top();
        table.setFillParent(true);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Raleway-Medium.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 30;
        fontParameter.color = Color.WHITE;
        font = fontGenerator.generateFont(fontParameter);

        countdownLabel = new Label( main.getCol().Nbturn+"", new Label.LabelStyle(font, Color.WHITE));
        scoreLabel = new Label(""+main.getCol().getTaille(), new Label.LabelStyle(font, Color.WHITE));
        timeLabel = new Label("Tour", new Label.LabelStyle(font, Color.WHITE));
        levelLabel = new Label("0", new Label.LabelStyle(font, Color.WHITE));
        QiLabel = new Label("QI", new Label.LabelStyle(font, Color.WHITE));
        popLabel = new Label("Population", new Label.LabelStyle(font, Color.WHITE));
        foodLabel = new Label("Nourriture", new Label.LabelStyle(font, Color.WHITE));
        foodLevel = new Label(main.getCol().getNourriture()+"", new Label.LabelStyle(font, Color.WHITE));

        tableTitles.add(popLabel).expandX().padTop(10);
        tableTitles.add(QiLabel).expandX().padTop(10);
        tableTitles.add(foodLabel).expandX().padTop(10);
        tableTitles.add(timeLabel).expandX().padTop(10);

        table.padTop(35);
        table.padLeft(50);
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(foodLevel).expandX();
        table.add(countdownLabel).expandX();

        tableNotifs = new Table();
        tableNotifs.center();
        tableNotifs.setFillParent(true);

        button1 = new TextButton("Chasser", new Skin(Gdx.files.internal("uiskin.json")));
        button1.setTransform(true);
        button1.setScale(1.35f);
        Gdx.input.setInputProcessor(stage);
        button1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(1);
                update(sb, main);
            }
        });
        button2 = new TextButton("Explorer", new Skin(Gdx.files.internal("uiskin.json")));
        button2.setTransform(true);
        button2.setScale(1.35f);
        button2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(2);
                update(sb, main);
            }
        });
        button3 = new TextButton("Cultiver", new Skin(Gdx.files.internal("uiskin.json")));
        button3.setTransform(true);
        button3.setScale(1.35f);
        button3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(3);
                update(sb, main);
            }
        });
        button4 = new TextButton("Croisiere", new Skin(Gdx.files.internal("uiskin.json")));
        button4.setTransform(true);
        button4.setScale(1.35f);
        button4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(4);
                update(sb, main);
            }
        });
        button5 = new TextButton("Attaquer", new Skin(Gdx.files.internal("uiskin.json")));
        button5.setTransform(true);
        button5.setScale(1.35f);
        button5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(5);
                update(sb, main);
            }
        });
        button6 = new TextButton("Vacciner", new Skin(Gdx.files.internal("uiskin.json")));
        button6.setTransform(true);
        button6.setScale(1.35f);
        button6.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(6);
                update(sb, main);
            }
        });
        button7 = new TextButton("Construire", new Skin(Gdx.files.internal("uiskin.json")));
        button7.setTransform(true);
        button7.setScale(1.35f);
        button7.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(7);
                update(sb, main);
            }
        });
        button8 = new TextButton("Envoyer Lettre", new Skin(Gdx.files.internal("uiskin.json")));
        button8.setTransform(true);
        button8.setScale(1.35f);
        button8.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                main.turn(8);
                update(sb, main);
            }
        });

        buttons = new Table();
        buttons.bottom();
        buttons.setFillParent(true);
        buttons.add(button1).expandX().padBottom(80).size(150, 50);
        buttons.add(button2).expandX().padBottom(80).size(150, 50);
        buttons.add(button3).expandX().padBottom(80).size(150, 50);
        buttons.add(button4).expandX().padBottom(80).size(150, 50);
        buttons.row();
        buttons.add(button5).expandX().padBottom(80).size(150, 50);
        buttons.add(button6).expandX().padBottom(80).size(150, 50);
        buttons.add(button7).expandX().padBottom(80).size(150, 50);
        buttons.add(button8).expandX().padBottom(80).size(150, 50);
        stage.addActor(tableTitles);
        stage.addActor(table);
        stage.addActor(buttons);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void update(SpriteBatch sb, Main main) {
        if (main.failure) {
            renderFailure();
            return;
        }

        tableNotifs.clear();
        for (String s : main.getCol().getNotifs()) {
            Label label = new Label(s, new Label.LabelStyle(font, Color.WHITE));
            tableNotifs.add(label).expandX();
            tableNotifs.row();
        }

        countdownLabel.setText(main.getCol().Nbturn);
        scoreLabel.setText(main.getCol().getTaille());
        levelLabel.setText(main.getCol().getQi() + "");
        foodLevel.setText(main.getCol().getNourriture());

        table.clear();
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(foodLevel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(tableTitles);
        stage.addActor(tableNotifs);
        stage.addActor(table);
        stage.addActor(buttons);
    }

    public void renderFailure(){
        stage.clear();
        stage.dispose();
        table.clear();
        buttons.clear();

        Label message = new Label("Vous avez perdu !", new Label.LabelStyle(font, Color.WHITE));
        Label nbTours = new Label("Vous avez survécu " + main.getCol().Nbturn + " tours", new Label.LabelStyle(font, Color.WHITE));

        table.center();
        table.add(message).expandX();
        table.row();
        table.add(nbTours).expandX();

        button1 = new TextButton("Quitter", new Skin(Gdx.files.internal("uiskin.json")));
        button1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
            }
        });

        table.row();
        table.add(button1);

        stage.addActor(table);
    }


}
