package models.attack;

import models.entities.Entity;
import models.map.Map;
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
    @Override
    public boolean hitEntity() {

        Point starting = new Point(entity.getLocation());


        while(range != 0) {
            starting.translate(0,1);
            if(map.getTileAt(starting).getEntity() != null){
                entity.startInteraction(map.getTileAt(starting).getNPC());
                map.getTileAt(starting).getEntity().getStats().modifyStat(Stats.Type.HEALTH,-1);
                return true;
            }
            starting.translate(0,1);
            range--;
        }
        return false;

    }

    @Override
    protected void initSprites() {

    }
}


