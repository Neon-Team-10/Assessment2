package io.github.neonteam10.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.neonteam10.GameLogic;

/**
 * A class which holds all the UI elements.
 */
public class UiStage extends Stage {
    private final UiAssets assets;
    private final GameLogic gameLogic;
    Table topLeftTable;
    Table pauseTable;
    Table topRightTable;
    Stack buildingToolbar;
    Table endGameTable;
    PauseButton pauseButton;

    public UiStage(AssetManager assetManager, GameLogic gameLogic) {
        // The UI spans the whole screen.
        super(new ScreenViewport());
        this.assets = new UiAssets(assetManager);
        this.gameLogic = gameLogic;

        // Create a table to fill the whole screen.
        Stack mainStack = new Stack();
        Table mainTable = new Table();
        mainStack.setFillParent(true);
        mainTable.setFillParent(true);
        addActor(mainStack);


        // Create a table anchored to the top left for the timer and stats.
        topLeftTable = new Table();
        topLeftTable.add(new GameTimer(assets, gameLogic));
        topLeftTable.row();
        topLeftTable.add(new BuildingStatsBox(assets, gameLogic));
        topLeftTable.row();
        topLeftTable.add(new SatisfactionMeter(assets, gameLogic)).align(Align.left).padTop(16.0f);
        topLeftTable.row();
        topLeftTable.add(new CurrentEventBox(assets, gameLogic)).padTop(16.0f);

        pauseTable = new Table();
        pauseTable.add(new PlayTextButton(assets, gameLogic)).pad(15.0f);
        pauseTable.row();
        pauseTable.add(new RestartTextButton(assets, gameLogic)).pad(15.0f);
        pauseTable.row();
        pauseTable.add(new QuitTextButton(assets, gameLogic)).pad(15.0f);

        topRightTable = new Table();
        pauseButton = new PauseButton(assets, gameLogic, pauseTable);
        topRightTable.add(pauseButton);

        // Create the building toolbar anchored to the bottom center.
        buildingToolbar = new BuildingToolbar(assets, gameLogic);
        mainTable.add(topLeftTable).expand().top().left().pad(25.0f);
        mainTable.add(topRightTable).expand().top().right().pad(25.0f);
        mainTable.row();
        mainTable.add(buildingToolbar).bottom().center().padBottom(5.0f).colspan(3);

        mainStack.add(mainTable);
        mainStack.add(pauseTable);

        endGameTable = new EndGameUI(assets, gameLogic);
        mainStack.add(endGameTable);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        assets.update();
    }

    public void update() {
        if (!gameLogic.getGameOver()) {
            endGameTable.setVisible(false);
            if (gameLogic.getPaused()) {
                pauseTable.setVisible(true);
                pauseButton.setChecked(true);
            }
            else {
                pauseTable.setVisible(false);
                pauseButton.setChecked(false);
            }
        }
        else {
            topRightTable.setVisible(false);
            topLeftTable.setVisible(false);
            buildingToolbar.setVisible(false);
            pauseTable.setVisible(false);
            endGameTable.setVisible(true);
        }

    }
}
