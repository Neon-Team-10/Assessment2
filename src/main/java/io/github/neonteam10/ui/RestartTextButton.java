package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.neonteam10.GameLogic;


public class RestartTextButton extends Table {
    private ImageButton restartButton;
    private Label textLabel;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;

    public RestartTextButton(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }
    public void act(float delta) {
        if (restartButton == null && uiAssets.hasSpritesheetLoaded() && uiAssets.hasFontsLoaded()) {
            Label.LabelStyle labelStyle = new Label.LabelStyle(uiAssets.getLargeFont(), Color.BLACK);
            textLabel = new Label("Restart", labelStyle);
            add(restartButton);
            ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(
                    new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 166, 96, 32)));
        }
    }
}
