package world;

import entity.Enemy;

public class Tile {
    private int tileID;
    private int x;
    private int y;
    private Enemy enemy;
    private boolean hasPlayer;
    private boolean passable;

    public Tile(int x, int y, Enemy enemy, boolean hasPlayer, int tileID, boolean passable) {
        this.x = x;
        this.y = y;
        this.enemy = enemy;
        this.hasPlayer = hasPlayer;
        this.tileID = tileID;
        this.passable = passable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTileID() {
        return tileID;
    }

    public void setTileID(int tileID) {
        this.tileID = tileID;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", hasEnemy=" + enemy +
                ", hasPlayer=" + hasPlayer +
                ", tileID=" + tileID +
                '}';
    }

    public boolean isHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}
