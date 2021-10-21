package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, InterruptedException {
        HashMap<Integer, WritableImage> imagegrid = new HashMap<>();
        TileSetReader tileReader = new TileSetReader();
        Image image = tileReader.ReadInImage("C:\\Code\\Projects\\Trotan\\asset\\tileSet.png");


        //Displays the image
        ImageView imageView = new ImageView();

        imageView.setFitHeight(32);
        imageView.setFitWidth(32);
        imageView.setPreserveRatio(true);

        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 640, 640);
        primaryStage.setScene(scene);

        //TODO külön classba
        Rectangle rect = null;
        int col = 16;
        int row = 16;
        int width = 50;
        int height = 50;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                int[][] tile = new int[i][j];
                rect = new Rectangle(width * j, height * i, width, height);
                rect.setStroke(Color.RED);
                root.getChildren().add(rect);
            }
        }

        imagegrid = tileReader.Slicer(image);
        WritableImage newImage;
        newImage = imagegrid.get(6);
        //imageView.setImage(newImage);
        //Group root = new Group(imageView);
        scene.setRoot(root);
        primaryStage.show();
    }
}
