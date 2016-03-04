package models.attack;

import models.entities.Entity;
import models.entities.npc.Enemy;
import models.entities.npc.NPC;
import models.map.Map;
import models.map.Tile;
import models.skills.SneakSkills.TileDetection;
import models.stats.Stats;

import java.awt.*;


/**
 * Created by denzel on 3/4/16.
 */
public class RangedAttack extends Attack {

    //Constructor for RangedAttacks
    public RangedAttack(Entity entity, int range, Map map) {

        super(entity, range, map);
    }



    //Do TileDetection stuff for rangedAttacks
    public void hitEntity() {

        Point starting = new Point(entity.getLocation());


        while(range != 0) {
            starting.translate(0,1);
            if(map.getTileAt(starting).getEntity() != null){
                entity.startInteraction(map.getTileAt(starting).getNPC());
                map.getTileAt(starting).getEntity().getStats().modifyStat(Stats.Type.HEALTH,-1);
                System.out.println(map.getTileAt(starting).getEntity() + " hp is: " + map.getTileAt(starting).getEntity().getStats().getStat(Stats.Type.HEALTH));

            }
            starting.translate(0,1);
            range--;
        }

    }
}


