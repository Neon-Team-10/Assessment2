package io.github.neonteam10.Achievements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;
import io.github.neonteam10.map.GameMap;
import io.github.neonteam10.ui.UiAssets;

import java.util.ArrayList;
import java.util.List;

public class ShowAchievement extends Table {
    private final UiAssets uiAssets;
    private Label nameLabel;
    private Label descriptionLabel;
    private Image achievementImage;

    public ShowAchievement(UiAssets uiAssets) {
        this.uiAssets = uiAssets;
        setFillParent(true);
        center();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Create label once fonts have been loaded.
        if (nameLabel == null && uiAssets.hasFontsLoaded()) {
            var nameStyle = new Label.LabelStyle(uiAssets.getSmallFont(), Color.BLACK);
            nameLabel = new Label("", nameStyle);
            descriptionLabel = new Label("", nameStyle);
        }

        // Create image once sprite sheet has been loaded.
        if (achievementImage == null && uiAssets.hasSpritesheetLoaded()) {
            var textureRegion = new TextureRegion(uiAssets.getSpritesheet(), 288, 132, 64, 32);
            achievementImage = new Image(textureRegion);
        }

        // Add children once they have been created.
        if (getChildren().isEmpty() && nameLabel != null && achievementImage != null) {
            add(achievementImage).size(64.0f * 3.0f, 32.0f * 3.0f);
            row();
            add(nameLabel).align(Align.center);
            row();
            add(descriptionLabel).align(Align.center);
        }

    }

    public void AchievementAppear() {
        setVisible(true);
        addAction(Actions.sequence(
                Actions.fadeIn(0.3f),
                Actions.delay(1f),
                Actions.fadeOut(0.2f),
                Actions.run(() -> setVisible(false))

        ));
    }

}
