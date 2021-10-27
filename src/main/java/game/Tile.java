package game;

public class Tile {
    private final int tileID;
    private int x;
    private int y;
    private boolean hasEnemy;
    private boolean hasPlayer;

    public Tile(int x, int y, boolean hasEnemy, boolean hasPlayer, int tileID) {
        this.x = x;
        this.y = y;
        this.hasEnemy = hasEnemy;
        this.hasPlayer = hasPlayer;
        this.tileID = tileID;
    }

    public int getTileID() {
        return tileID;
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
