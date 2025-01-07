package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.neonteam10.GameLogic;


public class EndGameUI extends Table {
    UiAssets uiAssets;
    Image background;
    GameLogic gameLogic;
    LeaderBoardBox leaderBoardBox;

    public EndGameUI(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (uiAssets.hasFontsLoaded() && uiAssets.hasSpritesheetLoaded()) {
            if (background == null) {
                background = new Image(new TextureRegion(uiAssets.getSpritesheet(), 320, 32, 64, 32));
            }
            if (getChildren().isEmpty() && background != null) {
                Stack stack = new Stack();
                Table top = new Table();

                stack.add(background);
                top.add(new RestartTextButton(uiAssets, gameLogic)).pad(25);
                top.row();
                top.add(new QuitTextButton(uiAssets, gameLogic)).pad(25);
                stack.add(top);

                add(stack).size(64 * 4.0f, 32 * 16.0f);
                add(new LeaderBoardBox(uiAssets, gameLogic)).pad(25);

            }
        }
    }

}
