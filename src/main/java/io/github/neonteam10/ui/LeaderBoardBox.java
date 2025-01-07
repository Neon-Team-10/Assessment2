package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;

public class LeaderBoardBox extends Table {
    private Image backGround;
    private Stack boxStack;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;

    public LeaderBoardBox(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }
    public void act(float delta) {
        if (uiAssets.hasSpritesheetLoaded()) {
            if (backGround == null) {
                backGround = new Image(new TextureRegion(uiAssets.getSpritesheet(), 320, 32, 64, 32));
            }
        }
    }
}
