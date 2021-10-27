package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, InterruptedException {

        TileSetReader tileReader = new TileSetReader();
        Image image = tileReader.ReadInImage("C:\\Code\\Projects\\Trotan\\asset\\tileSet.png");
        HashMap<Integer, Image> imageGrid = tileReader.Slicer(image);

        World world = new World(16, 16);
        world.worldGen();

        GridPane gridSystem = new GridPane();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridSystem);
        int j = 0;
        for (Tile[] col : world.getWorldTiles()) {
            int i = 0;
            for (Tile row : col) {
                ImageView displayTile = new ImageView(imageGrid.get(row.getTileID()));
                System.out.println(row.getTileID());
                displayTile.setFitHeight(32);
                displayTile.setFitWidth(32);
                gridSystem.add(displayTile, i, j);
                i++;
            }
            j++;
        }


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        scene.setRoot(borderPane);
        primaryStage.show();
    }
}
