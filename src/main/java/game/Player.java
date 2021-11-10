package game;

public class Player {
    private final World world;
    private int posX;
    private int posY;
    private int health;
    private int damage;
    private int armor;

    public Player(World world) {
        this.world = world;
    }

    public void move(int x, int y) {
        Tile[][] neighborTiles = world.getNeighborTiles(posX, posY);
        if (neighborTiles[x+2][y+2].isHasEnemy()) {
            //Fight
        } else if (neighborTiles[x+2][y+2].isPassable()) {
            //Move to tile
            world.getWorldTiles()[posX][posY].setHasPlayer(false);
            world.getWorldTiles()[x+1+posX][y+1+posY].setHasPlayer(true);
            posY += x*-1;
            posX += y*-1;
        }



    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
