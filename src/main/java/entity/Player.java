package entity;

import world.Tile;
import world.World;

public class Player extends Entity {
    public Player(World world) {
        super(world);
        this.health = 100;
        this.damage = 25;
        this.armor = 3;
    }

    @Override
    public void die() {
    }

    public void move(int x, int y) {
        Tile[][] neighborTiles = world.getNeighborTiles(currentTile.getY(), currentTile.getX());
        int neighbourY = 0;
        int neighbourX = 0;
        switch (y) {
            case 0 -> neighbourY = 1;
            case -1 -> neighbourY = 2;
        }
        switch (x) {
            case 0 -> neighbourX = 1;
            case 1 -> neighbourX = neighborTiles[neighbourY].length - 1;
        }
        Tile neighbourToCheck = neighborTiles[neighbourY][neighbourX];
        if (neighbourToCheck.getEnemy() != null) {
            neighbourToCheck.getEnemy().takeDamage(damage);
        } else if (neighbourToCheck.isPassable()) {
            currentTile.setHasPlayer(false);
            neighbourToCheck.setHasPlayer(true);
            currentTile = neighbourToCheck;
        }

    }


}
