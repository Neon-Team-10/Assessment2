package io.github.neonteam10.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.neonteam10.GameLogic;

public class ScoreBoardButton extends Table {
    UiAssets assets;
    GameLogic gameLogic;
    public ScoreBoardButton(UiAssets assets, GameLogic gameLogic) {
        this.assets = assets;
        this.gameLogic = gameLogic;
    }

    public void act(float delta) {

    }
}
