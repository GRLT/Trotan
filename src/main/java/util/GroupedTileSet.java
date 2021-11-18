package util;

public class GroupedTileSet {
    private static final int[] treeTiles = new int[]{33, 34, 35, 36, 37, 38};
    private static final int[] enemyTiles = new int[]{24, 26, 27, 28, 29, 30, 31};
    private static final int[] emptyGroundTiles = new int[]{0, 1, 2, 3, 4};


    public static int getRandomEnemySprite() {
        return enemyTiles[Util.randomIntBetweenRange(0, enemyTiles.length - 1)];
    }

    public static int getRandomGroundTile() {
        return emptyGroundTiles[Util.randomIntBetweenRange(0, emptyGroundTiles.length - 1)];
    }

}
