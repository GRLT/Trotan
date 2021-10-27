package game;

import java.util.Arrays;
import java.util.Random;

public class World {
    private final Tile[][] worldTiles;
    private final int col;
    private final int row;

    public World(int width, int height) {
        this.worldTiles = new Tile[width][height];
        this.col = height;
        this.row = width;
    }

    public Tile[][] getWorldTiles() {
        return worldTiles;
    }

    public void worldGen() {
        Random random = new Random();
        int max = Arrays.stream(GroupedTileSet.treeTiles).max().getAsInt() - 1;
        int min = Arrays.stream(GroupedTileSet.treeTiles).min().getAsInt() - 1;
        for (int i = 0; i < col; i++) {

            for (int j = 0; j < row; j++) {

                Tile tile = new Tile(i, j, false, false, random.nextInt(max + 1 - min) + min);
                worldTiles[i][j] = tile;
            }
        }
    }
}
