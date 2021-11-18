package entity;

import util.Util;
import world.Tile;
import world.World;

public class Enemy extends Entity {

    private final int enemyID;

    public Enemy(World world, Tile currentTile, int enemyID) {
        super(world);
        this.enemyID = enemyID;
        this.currentTile = currentTile;
        this.maxHealth = Util.randomIntBetweenRange(40, 60);
        health = maxHealth;
        this.damage = Util.randomIntBetweenRange(10, 25);
        this.armor = Util.randomIntBetweenRange(1, 5);
    }

    public int getEnemyID() {
        return enemyID;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void die() {
        isAlive = false;
        currentTile.setEnemy(null);
        currentTile.setPassable(true);
    }
}
