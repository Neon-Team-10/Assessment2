package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;


public class RestartTextButton extends Table {
    private ImageTextButton restartButton;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;

    public RestartTextButton(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }
    public void act(float delta) {
        if (restartButton == null && uiAssets.hasSpritesheetLoaded() && uiAssets.hasFontsLoaded()) {
            ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();

            style.up = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 166, 64, 18));
            style.over = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 18));
            style.down = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 18));

            style.font = uiAssets.getSmallFont();
            style.fontColor = Color.BLACK;

            restartButton = new ImageTextButton("Restart", style);
            restartButton.align(Align.center);
            restartButton.setScale(2);
            restartButton.setTransform(true);

            restartButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameLogic.restart();
                }
            });

            add(restartButton);
        }
    }
}
