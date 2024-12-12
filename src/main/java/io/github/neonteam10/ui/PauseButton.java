package io.github.neonteam10.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.neonteam10.GameLogic;

public class PauseButton extends Table {
    ImageButton pause;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;

    public PauseButton(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }

}
