package game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class WorldGenerator {
    World currentWorld;
    ArrayList<String> mapData;

    public WorldGenerator(World currentWorld, String path) throws IOException {
        this.currentWorld = currentWorld;
        this.mapData = readFromFile(path);
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public void convertCharsToTiles() throws Exception {
        if (!checkForValidity()) {
            throw new Exception("world_data.txt is not a valid map file");
        }

        for (int row = 0; row < mapData.size(); row++) {
            for (int col = 0; col < currentWorld.getWorldTiles()[row].length; col++) {
                switch (mapData.get(row).charAt(col)) {
                    case '0' -> {
                        currentWorld.getWorldTiles()[row][col].setTileID(GroupedTileSet.getRandomGroundTile());
                        currentWorld.getWorldTiles()[row][col].setPassable(true);
                    }
                    case '#' -> {
                        currentWorld.getWorldTiles()[row][col].setTileID(554);
                        currentWorld.getWorldTiles()[row][col].setPassable(false);
                    }
                    case 't' -> {
                        currentWorld.getWorldTiles()[row][col].setTileID(33);
                        currentWorld.getWorldTiles()[row][col].setPassable(false);
                    }
                    case 'p' -> {
                        currentWorld.getWorldTiles()[row][col].setHasPlayer(true);
                        currentWorld.player.setPosX(row);
                        currentWorld.player.setPosY(col);

                    }
                    case 'e' -> {
                        currentWorld.getWorldTiles()[row][col].setTileID(GroupedTileSet.getRandomEnemySprite());
                        currentWorld.getWorldTiles()[row][col].setHasEnemy(true);
                        currentWorld.getWorldTiles()[row][col].setPassable(false);
                    }
                }
            }
        }

    }

    public ArrayList<String> readFromFile(String path) throws IOException {
        Stream<String> input = Files.lines(Paths.get(path), StandardCharsets.US_ASCII);
        ArrayList<String> data = new ArrayList<>();
        input.forEach(data::add);
        return data;
    }

    public boolean checkForValidity() {
        int dimensionOfWorld = currentWorld.getRow();

        int y_size = mapData.size();
        int x_size;

        for (int i = 0; i < currentWorld.getRow(); i++) {
            x_size = mapData.get(i).length();
            if (x_size != currentWorld.getRow()) {
                return false;
            }
        }
        //Determines if world_data has a different dimension in the number of cols
        if (y_size != dimensionOfWorld) {
            return false;
        }


        char[] mapDataFromFile = String.join("", mapData).toCharArray();
        final String allowedChars = "0#tpe";
        /*
        0 Empty Tile
        # Walls
        t Trees
        p Player
        e Enemy
         */
        int amountOfplayer = 0;
        for (char letter : mapDataFromFile) {
            String letterString = Character.toString(letter);
            if (!allowedChars.contains(letterString)) {
                return false;
            }
            if (letterString.equals("p")) {
                amountOfplayer++;
            }
            if (amountOfplayer > 1) {
                return false;
            }


        }
        return true;
    }


}
