package game;

import entity.Enemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.TileSetReader;
import util.Util;
import world.Tile;
import world.World;
import world.WorldGenerator;

import java.util.HashMap;

public class Main extends Application {

    World world = new World(16, 16);
    TileSetReader tileReader = new TileSetReader();
    Image image = tileReader.readInImage("C:\\Code\\Projects\\Trotan\\asset\\tileSet.png");
    HashMap<Integer, Image> imageGrid = tileReader.Slicer(image);

    GridPane tileGrid = new GridPane();
    GridPane infoGrid = new GridPane();


    public Main() throws Exception {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            world.emptyWorldGen();

            Timeline twoSecondTimer = new Timeline(
                    new KeyFrame(Duration.seconds(2),
                            event -> moveEnemies()));
            twoSecondTimer.setCycleCount(Timeline.INDEFINITE);


            WorldGenerator worldGenerator = new WorldGenerator(world, "world_data.txt");
            worldGenerator.convertCharsToTiles();

            twoSecondTimer.play();

            BorderPane borderPane = new BorderPane();
            GridPane container = new GridPane();

            container.add(tileGrid, 0, 0);
            container.add(infoGrid, 1, 0);
            infoGrid.setMinWidth(320);

            borderPane.setCenter(container);
            refreshScreen();

            Scene scene = new Scene(borderPane);
            scene.setOnKeyPressed(this::onKeyPressed);


            primaryStage.setScene(scene);
            primaryStage.setTitle("Trotan");
            scene.setRoot(borderPane);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void refreshScreen() {
        tileGridRefresh();
        infoPanelRefresh();
    }

    private void infoPanelRefresh() {
        infoGrid.getChildren().clear();
        Tile[][] tiles = world.getNeighborTiles(world.player.currentTile);
        String[] verticalPos = new String[]{"top", "bottom"};
        String[] horizontalPos = new String[]{"left", "right"};

        int rowIndex = 0;
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                Enemy enemy = null;
                try {
                    enemy = tiles[row][col].getEnemy();
                } catch (Exception ignored) {
                }
                if (enemy != null) {
                    String verticalString = "", horizontalString = "";
                    int verticalInt = 0, horizontalInt = 0;
                    if (row == 2) {
                        verticalInt = 1;
                    }
                    if (col == 2) {
                        horizontalInt = 1;
                    }
                    if (row != 1) {
                        verticalString = verticalPos[verticalInt];
                    }
                    if (col != 1) {
                        horizontalString = horizontalPos[horizontalInt];
                    }
                    TilePane enemyInfoPane = createEnemyInfo(verticalString + " " + horizontalString, enemy.getHealth(), enemy.getDamage(), enemy.getArmor(), enemy.getHealthPercent());
                    infoGrid.add(enemyInfoPane, 0, rowIndex);
                    rowIndex++;
                }
            }
        }
    }

    private TilePane createEnemyInfo(String pos, int health, int damage, int armor, int healthPercent) {
        String[] colorStyle = new String[]{"#42ff30", "#91cc52", "#ffbf66", "#fc3f3f"};
        int colorStyleIndex = 0;
        if (healthPercent <= 25) {
            colorStyleIndex = 3;
        } else if (healthPercent <= 50) {
            colorStyleIndex = 2;
        } else if (healthPercent <= 75) {
            colorStyleIndex = 1;
        }
        Label posLabel = new Label("Enemy on the " + pos);
        Label healthLabel = new Label("Health: " + health);
        Label damageLabel = new Label("Damage:  " + damage);
        Label armorLabel = new Label("Armor:  " + armor);
        TilePane tilePane = new TilePane(posLabel, healthLabel, damageLabel, armorLabel);
        tilePane.setPrefColumns(2);
        tilePane.setPadding(new Insets(5, 5, 5, 5));
        tilePane.setStyle("-fx-background-color: " + colorStyle[colorStyleIndex] + "; -fx-border-color: black; -fx-border-radius: 4; -fx-border-width:2;");


        return tilePane;
    }

    private void tileGridRefresh() {
        int j = 0;
        tileGrid.getChildren().clear();
        for (Tile[] col : world.getWorldTiles()) {
            int i = 0;
            for (Tile row : col) {
                ImageView displayTile;
                if (row.isHasPlayer()) {
                    displayTile = new ImageView(imageGrid.get(25));
                } else if (row.getEnemy() != null) {
                    displayTile = new ImageView(imageGrid.get(row.getEnemy().getEnemyID()));
                } else {
                    displayTile = new ImageView(imageGrid.get(row.getTileID()));
                }

                displayTile.setFitHeight(32);
                displayTile.setFitWidth(32);
                tileGrid.add(displayTile, i, j);
                i++;
            }
            j++;
        }
    }

    private void moveEnemies() {


        for (Enemy enemy : world.getEnemies()) {

            if (enemy != null && !enemy.isAlive()) {
                world.removeEnemyFromList(enemy);
                continue;
            }
            if (enemy != null) {
                int randomX = Util.randomIntBetweenRange(-1, 1);
                int randomY = Util.randomIntBetweenRange(-1, 1);
                enemy.move(randomX, randomY);
            }
        }
        refreshScreen();
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {
                world.player.move(0, 1);
                refreshScreen();
            }
            case DOWN -> {
                world.player.move(0, -1);
                refreshScreen();
            }
            case LEFT -> {
                world.player.move(-1, 0);
                refreshScreen();
            }
            case RIGHT -> {
                world.player.move(1, 0);
                refreshScreen();
            }
        }
    }
}
