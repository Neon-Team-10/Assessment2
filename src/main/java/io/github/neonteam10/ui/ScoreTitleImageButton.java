package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;

public class ScoreTitleImageButton extends Table {
    private UiAssets uiAssets;
    private GameLogic gameLogic;
    private Stack stack;
    public ScoreTitleImageButton(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (stack == null && uiAssets.hasSpritesheetLoaded() && uiAssets.hasFontsLoaded()) {

            Image background = new Image(new TextureRegion(uiAssets.getSpritesheet(), 288, 128, 64, 28));
            Label.LabelStyle labelStyle = new Label.LabelStyle(uiAssets.getLargeFont(), Color.BLACK);
            Label label = new Label("Top 5" , labelStyle);
            label.setAlignment(Align.center);

            stack = new Stack();
            stack.add(background);
            stack.add(label);
            add(stack).size(64*2.5f, 28*2.5f).pad(10);
        }
    }
}
