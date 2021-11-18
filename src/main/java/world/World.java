package world;

import entity.Enemy;
import entity.Player;

import java.util.ArrayList;

public class World {
    private final Tile[][] worldTiles;
    private final int col;
    private final int row;
    public Player player;
    ArrayList<Enemy> enemies = new ArrayList<>();

    public World(int width, int height) throws Exception {
        this.worldTiles = new Tile[width][height];
        if (height > 32) {
            throw new Exception("Height is too big");
        }
        if (width > 32) {
            throw new Exception("Width is too big");
        }
        this.col = height;
        this.row = width;
        this.player = new Player(this);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Tile[][] getWorldTiles() {
        return worldTiles;
    }

    public void emptyWorldGen() {
        for (int row = 0; row < col; row++) {
            for (int col = 0; col < this.row; col++) {
                Tile tile = new Tile(row, col, null, false, 0, true);
                worldTiles[col][row] = tile;
            }
        }
    }

    public Tile[][] getNeighborTiles(Tile tile) {
        return getNeighborTiles(tile.getY(), tile.getX());
    }

    public Tile[][] getNeighborTiles(int tileX, int tileY) {
        Tile[][] neighborTiles = new Tile[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (i != 1 || j != 1) {
                        neighborTiles[i][j] = worldTiles[tileX + (i - 1)][tileY + (j - 1)];
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return neighborTiles;
    }


}
