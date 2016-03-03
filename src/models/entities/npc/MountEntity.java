package models.entities.npc;


import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Smasher;


import java.awt.*;


/**
 * Created by denzel on 3/2/16.
 */
public abstract class MountEntity extends NPC {

    //Constructor for the mount with location on a map
    public MountEntity(Point location, Map map) {

        super(location, map);
    }


    @Override
    protected Occupation initOccupation() {
        return new Smasher();
    }


    @Override
    public void startInteraction(NPC npc) {

    }


}
