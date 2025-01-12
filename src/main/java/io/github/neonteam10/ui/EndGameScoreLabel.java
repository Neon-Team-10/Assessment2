package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;

public class EndGameScoreLabel extends Table {
    private GameLogic gameLogic;
    private UiAssets uiAssets;
    private Image image;
    private Label label;
    private Stack stack;
    public EndGameScoreLabel(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (uiAssets.hasFontsLoaded() && uiAssets.hasSpritesheetLoaded() && image == null && label == null) {
            image= new Image(new TextureRegion(uiAssets.getSpritesheet(), 288, 128, 64, 28));
            Label.LabelStyle labelStyle = new Label.LabelStyle(uiAssets.getLargeFont(), Color.BLACK);
            label = new Label(String.format("%06d", (int) gameLogic.getSatisfaction()), labelStyle);
            label.setAlignment(Align.center);
            stack = new Stack();
            stack.add(image);
            stack.add(label);
            add(stack).size(64 * 4f, 28 * 4f);
        }
        if (label != null) {
            label.setText(String.format("%06d", (int) gameLogic.getSatisfaction()));
        }
    }
}
