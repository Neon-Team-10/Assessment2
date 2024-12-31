package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;

public class PauseButton extends Table {
    private ImageButton pause;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;
    private final Table pauseTable;

    public PauseButton(UiAssets uiAssets, GameLogic gameLogic, Table pauseTable) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;

        this.pauseTable = pauseTable;
    }

    public void act(float delta) {
        super.act(delta);

        if (pause == null && uiAssets.hasSpritesheetLoaded()) {
            pause = new ImageButton(new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 224, 224, 32, 32)));
            pause.getStyle().imageChecked = new TextureRegionDrawable(new TextureRegion(uiAssets.getSpritesheet(), 224, 254, 32, 32));
            pause.setTransform(true);
            pause.setOrigin(Align.center);
            pause.setScale(3);
            pause.setChecked(true);
            pause.addListener(new ClickListener() {
                  @Override
                  public void clicked(InputEvent event, float x, float y) {
                      gameLogic.setPaused(pause.isChecked());
                      pauseTable.setVisible(gameLogic.getPaused());

                  }
              });
            add(pause);
        }
    }
}
