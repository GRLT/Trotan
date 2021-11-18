package util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class TileSetReader {

    public Image readInImage(String path) throws FileNotFoundException {
        InputStream input = new FileInputStream(path);
        return new Image(input);
    }


    public HashMap<Integer, Image> Slicer(Image image) {
        HashMap<Integer, Image> slicedImages = new HashMap<>();
        PixelReader secondReader = image.getPixelReader();
        int y = 0, x = 0, k = 0;

        for (int i = 0; i < 1024; i++) {
            WritableImage newImage = new WritableImage(secondReader, x, y, 16, 16);
            slicedImages.put(k++, (Image) newImage);
            x += 17;
            if (x == 544) {
                x = 0;
                y += 17;
            }
        }
        return slicedImages;
    }

}
