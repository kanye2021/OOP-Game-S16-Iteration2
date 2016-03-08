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
    public TileDetection(Entity entity, Point location){
        this.entity = entity;
        this.location = location;
    }
    public boolean npcDetected(){
        if (entity == null){
            return false;
        }else {
            return true;
        }

    }
    public Point getLocation(){
        return location;
    }
    public Entity getEntity(){
        return entity;
    }
}
