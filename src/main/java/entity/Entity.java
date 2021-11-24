package entity;

import world.Tile;
import world.World;

public abstract class Entity {

    protected final World world;
    public Tile currentTile;
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected int armor;

    public boolean isAlive() {
        return isAlive;
    }

    protected boolean isAlive = true;

    public Entity(World world) {
        this.world = world;
    }

    public abstract void move(int x, int y);

    public void takeDamage(int damageToTake) {
        health = Math.max(health - Math.max(damageToTake - armor, 1), 0);
        if (health == 0)
            die();

    }

    public abstract void die();

    public int getHealthPercent() {
        return (int) (((double) health / maxHealth) * 100);
    }
}
