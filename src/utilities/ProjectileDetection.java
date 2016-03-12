package utilities;

import models.entities.Entity;

import java.awt.*;

/**
 * Created by denzel on 3/11/16.
 */
public class ProjectileDetection {
    private Entity entity;
    private Point location;
    private boolean moved;
    private boolean teleported;



    public boolean npcDetected(){
        return entity != null;
    }
    public Point getLocation(){
        return location;
    }
    public Entity getEntity(){
        return entity;
    }


    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isTeleported() {
        return teleported;
    }

    public void setTeleported(boolean teleported) {
        this.teleported = teleported;
    }
}
