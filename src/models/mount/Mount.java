package models.mount;

import models.entities.Entity;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.map.Terrain;
import models.occupation.Occupation;
import models.stats.StatModificationList;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by denzel on 2/29/16.
 */
public abstract class Mount extends EquippableItem {

    //variables that all mounts have
    protected int movement;
    protected Entity entity;
    protected String terrain;

    //constructor
    public Mount(Entity entity){
        this.entity = entity;
    }

    //what happens when an entity touches the mount (oo la la la)
    public abstract void setTerrain(Entity entity);

}
