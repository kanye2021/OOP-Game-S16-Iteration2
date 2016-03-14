package utilities;

import models.attack.Projectile;

import java.awt.*;

/**
 * Created by denzel on 3/11/16.
 */
public class ProjectileDetection {
    private Projectile projectile;
    private Point location;
    private boolean moved;

    public ProjectileDetection(Projectile projectile, Point location, boolean moved) {
        this.projectile = projectile;
        this.location = location;
        this.moved = moved;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
