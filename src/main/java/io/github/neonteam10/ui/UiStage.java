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

    public UiStage(AssetManager assetManager, GameLogic gameLogic) {
        // The UI spans the whole screen.
        super(new ScreenViewport());
        assets = new UiAssets(assetManager);

        // Create a table to fill the whole screen.
        Stack mainStack = new Stack();
        Table mainTable = new Table();
        mainStack.setFillParent(true);
        mainTable.setFillParent(true);
        addActor(mainStack);


        // Create a table anchored to the top left for the timer and stats.
        Table topLeftTable = new Table();
        topLeftTable.add(new GameTimer(assets, gameLogic));
        topLeftTable.row();
        topLeftTable.add(new BuildingStatsBox(assets, gameLogic));
        topLeftTable.row();
        topLeftTable.add(new SatisfactionMeter(assets, gameLogic)).align(Align.left).padTop(16.0f);
        topLeftTable.row();
        topLeftTable.add(new CurrentEventBox(assets, gameLogic)).padTop(16.0f);

        Table pauseTable = new Table();
        pauseTable.setOrigin(Align.right);
        pauseTable.center();
        pauseTable.add(new RestartTextButton(assets, gameLogic)).pad(25.f);
        pauseTable.row();
        pauseTable.add(new QuitTextButton(assets, gameLogic)).pad(25.0f);

        Table topRightTable = new Table();
        topRightTable.add(new PauseButton(assets, gameLogic, pauseTable));

        // Create the building toolbar anchored to the bottom center.
        Stack buildingToolbar = new BuildingToolbar(assets, gameLogic);
        mainTable.add(topLeftTable).expand().top().left().pad(25.0f);
        mainTable.add(topRightTable).expand().top().right().pad(25.0f);
        mainTable.row();
        mainTable.add(buildingToolbar).bottom().center().padBottom(5.0f).colspan(3);

        mainStack.add(mainTable);
        mainStack.add(pauseTable);

        Table endGameTable = new EndGameUI(assets, gameLogic);
        mainStack.add(endGameTable);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        assets.update();
    }
}
