package models.attack;

import models.map.Map;
import models.map.Terrain;
import utilities.Animator;
import utilities.ProjectileDetection;

import java.awt.*;
import java.util.*;

/**
 * Created by ben on 3/8/16.
 */
public class Projectile {
    //Things I would need:
    //Attack
    //
    protected int damage;
    protected int range;
    protected int speed;
    protected Point location;
    private boolean canMove;
    protected Map map;
    private java.util.Timer movementTimer;
    public Animator animator;

    //projectiles need passable terrains
    protected ArrayList<String> passableTerrain;

    //Put status effects here
    public Projectile(int damage,int range,int speed,Map map,ArrayList<Image> images){
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.map = map;
        passableTerrain = new ArrayList<>();
        passableTerrain.add("grass");
        canMove = true;
        movementTimer = new java.util.Timer();
        this.animator = new Animator(images);
        animator.setSpeed(speed);
    }

    public int getDamage(){
        return damage;
    }

    public int getRange(){
        return range;
    }

    public int getSpeed(){
        return speed;
    }

    public Point getLocation(){
        return location;
    }

    public boolean canTraverseTerrain(Terrain terrain){
        return passableTerrain.contains(terrain.getType());
    }

    public final ProjectileDetection projectileMove(Map.Direction direction) {
        if (canMove) {
            ProjectileDetection pd = map.moveProjectile(this, direction);
            location = pd.getLocation();
            canMove = false;
            movementTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    canMove = true;
                }
            }, speed);
            map.updateTile(location);
        }
        return null;
    }

    public Image getImage(){
        System.out.println("hey");
        return animator.update(System.currentTimeMillis());
    }
}
