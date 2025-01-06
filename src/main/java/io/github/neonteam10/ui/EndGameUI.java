package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.neonteam10.GameLogic;

public class EndGameUI extends Table {
    UiAssets assets;
    GameLogic gameLogic;
    Label scoreLabel;
    QuitTextButton quitTextButton;
    RestartTextButton restartTextButton;
    ScoreBoardButton scoreBoardButton;

    public EndGameUI(UiAssets assets, GameLogic gameLogic) {
        this.assets = assets;
        this.gameLogic = gameLogic;
    }

    @Override
    public void act(float delta) {

        if (assets.hasFontsLoaded() && assets.hasSpritesheetLoaded()) {
            if (scoreLabel == null) {
                Label.LabelStyle labelStyle = new Label.LabelStyle(assets.getLargeFont(), Color.BLACK);
                scoreLabel = new Label(String.format("%f", gameLogic.getSatisfaction()), labelStyle);
            }
            if (quitTextButton == null) {
                quitTextButton = new QuitTextButton(assets, gameLogic);
            }
            if (restartTextButton == null) {
                restartTextButton = new RestartTextButton(assets, gameLogic);
            }
            if (scoreBoardButton == null) {
                scoreBoardButton = new ScoreBoardButton(assets, gameLogic);
            }

            if (scoreBoardButton != null && restartTextButton != null && quitTextButton != null) {

            }
        }
    }

}
