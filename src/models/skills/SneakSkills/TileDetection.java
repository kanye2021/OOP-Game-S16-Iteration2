package models.skills.SneakSkills;

import models.conditions.MapCondition;
import models.entities.npc.NPC;
import models.map.Map;

import java.awt.*;

/**
 * Created by dyeung on 3/1/16.
 */
public class TileDetection {
    private NPC npc;
    private Point location;
    private Map map;

    public TileDetection(NPC npc, Point location){

        this.location = location;
        this.npc = npc;

    }
    public boolean npcDetected(){
        if (npc == null){
            return false;
        }else {
            return true;
        }
    }

    //TODO: For now it checks verticallly in front of the Avatar
    //ranged utility function for NPC detection
    public boolean npcDetectedRanged(int range, Point starting){
        while(range != 0) {
            starting.translate(0,1);
            System.out.println(map.getTileAt(starting).getEntity());
            range--;
        }
        return true;
    }


    public Point getLocation(){
        return location;
    }
    public NPC getNpc(){
        return npc;
    }
}
