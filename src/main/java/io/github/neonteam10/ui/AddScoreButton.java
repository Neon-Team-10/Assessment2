package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;
import io.github.neonteam10.Leaderboard;

public class AddScoreButton extends Table {
    ImageTextButton addScoreButton;
    UiAssets uiAssets;
    GameLogic gameLogic;
    Leaderboard leaderBoard;
    public AddScoreButton(UiAssets uiAssets, GameLogic gameLogic, Leaderboard leaderBoard) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
        this.leaderBoard = leaderBoard;
    }

    public void act(float delta) {
        super.act(delta);
        if (addScoreButton == null && uiAssets.hasFontsLoaded() && uiAssets.hasSpritesheetLoaded()) {
            ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();

            style.up = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 166, 64, 20));
            style.over = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 20));
            style.down = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 198, 64, 20));

            style.font = uiAssets.getSmallFont();
            style.fontColor = Color.BLACK;

            addScoreButton = new ImageTextButton("Save Score", style);

            TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
            textFieldStyle.font = uiAssets.getSmallFont();
            textFieldStyle.fontColor = Color.BLACK;
            textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 288, 166, 64, 20));

            TextField inputText = new TextField("", textFieldStyle);
            inputText.setMessageText("Enter your name");
            inputText.setMaxLength(3);
            inputText.setAlignment(Align.center);

            addScoreButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    leaderBoard.addEntry(inputText.getText(), (int) gameLogic.getSatisfaction());
                    leaderBoard.writeLeaderboard();
                }
            });
            add(inputText).size(64 * 3, 20 * 3);
            row();
            add(addScoreButton).size(64 * 3, 20 * 3);
        }
    }
}
