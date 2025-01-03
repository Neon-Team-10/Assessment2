package io.github.neonteam10.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Null;
import io.github.neonteam10.GameLogic;

public class QuitTextButton extends Table {
    private ImageTextButton quitButton;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;

    public QuitTextButton(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }
    public void act(float delta) {
        if (quitButton== null && uiAssets.hasSpritesheetLoaded() && uiAssets.hasFontsLoaded()) {
            ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();

            style.up = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 166, 64, 18));
            style.over = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 18));
            style.down = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 18));

            style.font = uiAssets.getSmallFont();
            style.fontColor = Color.BLACK;

            quitButton = new ImageTextButton("Quit", style);
            quitButton.align(Align.center);
            quitButton.setScale(2);
            quitButton.setTransform(true);

            quitButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });

            add(quitButton);
        }
    }
}
