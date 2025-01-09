package io.github.neonteam10.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.neonteam10.GameLogic;
import io.github.neonteam10.Leaderboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderBoardBox extends Table {
    private Leaderboard leaderboard;
    private Image backGround;
    private ArrayList<Label> labelList;
    private Stack boxStack;
    private Table stackTable;
    private final UiAssets uiAssets;
    private final GameLogic gameLogic;
    Table scoreTable;

    public LeaderBoardBox(UiAssets uiAssets, GameLogic gameLogic) {
        this.uiAssets = uiAssets;
        this.gameLogic = gameLogic;
        this.leaderboard = new Leaderboard("scores.txt");
    }
    public void act(float delta) {
        super.act(delta);
        if (uiAssets.hasSpritesheetLoaded() && uiAssets.hasFontsLoaded()) {
            if (backGround == null) {
                backGround = new Image(new TextureRegion(uiAssets.getSpritesheet(), 320, 32, 64, 32));
            }
            if (labelList == null) {
                Label.LabelStyle labelStyle = new Label.LabelStyle(uiAssets.getSmallFont(), Color.BLACK);
                List<Map.Entry<String, Integer>> scoreMap = leaderboard.getTopFive();
                labelList = new ArrayList<>();
                if (scoreMap != null) {
                    for (Map.Entry<String, Integer> stringIntegerEntry : scoreMap) {
                        labelList.add(new Label(String.format("%s: %d", stringIntegerEntry.getKey(), stringIntegerEntry.getValue()), labelStyle));
                    }
                }
            }
            if (boxStack == null && stackTable == null && backGround != null && labelList != null) {
                boxStack = new Stack();
                boxStack.setFillParent(true);
                boxStack.add(backGround);

                stackTable = new Table();
                stackTable.setFillParent(true);
                scoreTable = new Table();
                scoreTable.add(new ScoreTitleImageButton(uiAssets, gameLogic));
                scoreTable.row();
                for (Label label : labelList) {
                    scoreTable.add(label).pad(10);
                    scoreTable.row();
                }
                stackTable.add(scoreTable);
                stackTable.row();
                stackTable.add(new AddScoreButton(uiAssets, gameLogic, leaderboard)).bottom().expand().padBottom(25);
                boxStack.add(stackTable);
                add(boxStack).size(64 * 4, 32 * 16);
            }
            leaderboard.readLeaderboard();
            List<Map.Entry<String, Integer>> scoreMap = leaderboard.getTopFive();
            for (int i = 0; i < labelList.size(); i++) {
                labelList.get(i).setText(String.format("%s: %d", scoreMap.get(i).getKey(), scoreMap.get(i).getValue()));
            }
            updateLeaderBoard();
        }
    }
    public void updateLeaderBoard() {
        leaderboard.readLeaderboard();
        List<Map.Entry<String, Integer>> scoreMap = leaderboard.getTopFive();
        if (scoreMap.size() != labelList.size()) {
            System.out.println("made");
            labelList.add(new Label("", new Label.LabelStyle(uiAssets.getSmallFont(), Color.BLACK)));
            scoreTable.add(labelList.get(labelList.size()-1)).pad(10);
        }
        for (int i = 0; i < labelList.size(); i++) {
            System.out.println(i);
            labelList.get(i).setText(String.format("%s: %d", scoreMap.get(i).getKey(), scoreMap.get(i).getValue()));
        }
    }
}
