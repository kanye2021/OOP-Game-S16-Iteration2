package utilities;

import models.conditions.MapCondition;
import models.entities.Entity;
import models.entities.npc.NPC;

import java.awt.*;

/**
 * Created by dyeung on 3/1/16.
 */
public class TileDetection {
    private Entity entity;
    private Point location;
    private boolean moved;
    private boolean teleported;

    public TileDetection(Entity npc, Point location, boolean moved, boolean teleported) {
        this.entity = npc;
        this.location = location;
        this.moved = moved;
        this.teleported = teleported;
    }
    public boolean npcDetected(){
        return entity != null;
    }
    public Point getLocation(){
        return location;
    }
    public Entity getEntity(){
        return entity;
    }

    public void setNpc(NPC npc) {
        this.entity = npc;
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
