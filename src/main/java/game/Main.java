package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    World world = new World(16, 16);
    TileSetReader tileReader = new TileSetReader();
    Image image = tileReader.readInImage("C:\\Code\\Projects\\Trotan\\asset\\tileSet.png");
    HashMap<Integer, Image> imageGrid = tileReader.Slicer(image);

    GridPane gridSystem = new GridPane();


    public Main() throws Exception {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            world.emptyWorldGen();

            WorldGenerator worldGenerator = new WorldGenerator(world, "world_data.txt");
            worldGenerator.convertCharsToTiles();

            BorderPane borderPane = new BorderPane();

            borderPane.setCenter(gridSystem);
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
        int j = 0;
        for (Tile[] col : world.getWorldTiles()) {
            int i = 0;
            for (Tile row : col) {
                ImageView displayTile;
                if (row.isHasPlayer()) {
                    displayTile = new ImageView(imageGrid.get(25));
                } else {
                    displayTile = new ImageView(imageGrid.get(row.getTileID()));
                }
                displayTile.setFitHeight(32);
                displayTile.setFitWidth(32);
                gridSystem.add(displayTile, i, j);
                i++;
            }
            j++;
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {world.player.move(0, 1); refreshScreen();}
            case DOWN -> {world.player.move(0, -1); refreshScreen();}
            case LEFT -> world.player.move(-1, 0);
            case RIGHT -> world.player.move(1, 0);
        }
    }
}
