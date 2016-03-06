package models.skills.SneakSkills;

import models.conditions.MapCondition;
import models.entities.npc.NPC;

import java.awt.*;

/**
 * Created by dyeung on 3/1/16.
 */
public class TileDetection {
    private NPC npc;
    private Point location;
    private boolean moved;
    private boolean teleported;
    public TileDetection(NPC npc, Point location, boolean moved, boolean teleported) {
        this.npc = npc;
        this.location = location;
        this.moved = moved;
        this.teleported = teleported;
    }
    public boolean npcDetected(){
        return npc != null;
    }
    public Point getLocation(){
        return location;
    }
    public NPC getNpc(){
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
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
