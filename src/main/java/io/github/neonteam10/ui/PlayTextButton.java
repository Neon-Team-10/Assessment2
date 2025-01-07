package io.github.neonteam10.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.neonteam10.GameLogic;

public class PlayTextButton extends Table{
    private ImageTextButton playButton;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;

    public PlayTextButton(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }
    public void act(float delta) {
        if (playButton == null && uiAssets.hasSpritesheetLoaded() && uiAssets.hasFontsLoaded()) {
            ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();

            style.up = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 166, 64, 20));
            style.over = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 20));
            style.down = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 20));

            style.font = uiAssets.getSmallFont();
            style.fontColor = Color.BLACK;

            playButton = new ImageTextButton("play", style);

            playButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    gameLogic.setPaused(false);
                }
            });

            add(playButton).size(64 * 3, 18 * 3);
        }
    }

}
