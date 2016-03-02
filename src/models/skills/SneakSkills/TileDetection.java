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
    public TileDetection(NPC npc, Point location){
        this.npc = npc;
        this.location = location;
    }
    public boolean npcDetected(){
        if (npc == null){
            return false;
        }else {
            return true;
        }

    }
    public Point getLocation(){
        return location;
    }
    public NPC getNpc(){
        return npc;
    }
}
