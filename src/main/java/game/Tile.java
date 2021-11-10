package game;

public class Tile {
    private int tileID;
    private int x;
    private int y;
    private boolean hasEnemy;
    private boolean hasPlayer;
    private boolean passable;

    public void setTileID(int tileID) {
        this.tileID = tileID;
    }

    public Tile(int x, int y, boolean hasEnemy, boolean hasPlayer, int tileID, boolean passable) {
        this.x = x;
        this.y = y;
        this.hasEnemy = hasEnemy;
        this.hasPlayer = hasPlayer;
        this.tileID = tileID;
        this.passable = passable;
    }

    public int getTileID() {
        return tileID;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", hasEnemy=" + hasEnemy +
                ", hasPlayer=" + hasPlayer +
                ", tileID=" + tileID +
                '}';
    }

    public boolean isHasEnemy() {
        return hasEnemy;
    }

    public void setHasEnemy(boolean hasEnemy) {
        this.hasEnemy = hasEnemy;
    }

    public boolean isHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }
}
