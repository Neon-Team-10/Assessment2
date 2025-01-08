package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;

/**
 * A class which represents the student satisfaction UI element.
 */
public class SatisfactionMeter extends Table {
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;
    private Image backgroundImage;
    private Label satisfactionLabel;

    public SatisfactionMeter(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (satisfactionLabel == null && uiAssets.hasFontsLoaded()) {
            var labelStyle = new Label.LabelStyle(uiAssets.getLargeFont(), Color.BLACK);
            satisfactionLabel = new Label("000000", labelStyle);
            satisfactionLabel.setAlignment(Align.center);
        }

        // Create background image once spritesheet has been loaded.
        if (backgroundImage == null && uiAssets.hasSpritesheetLoaded()) {
            var textureRegion = new TextureRegion(uiAssets.getSpritesheet(), 290, 166, 62, 18);
            backgroundImage = new Image(textureRegion);
            add(backgroundImage).size(textureRegion.getRegionWidth() * GameTimer.IMAGE_SCALE,
                    textureRegion.getRegionHeight() * GameTimer.IMAGE_SCALE);
            row();
            add(satisfactionLabel).padTop(-48.0f * 1.25f);
        }



    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (satisfactionLabel != null) {
<<<<<<< HEAD
            satisfactionLabel.setText(String.format("%06d", ((int) (gameLogic.getSatisfaction()*1000))));
=======
            satisfactionLabel.setText(String.format("%06d", ((int) (gameLogic.getSatisfaction()))));
>>>>>>> 6f34ddd (Changed the scoring calculation in GameLogic, includin adding bobuses for accommodation and cantee radii, which I've had to put in BuildingGrapgh to get the distances.)
        }
    }
}
