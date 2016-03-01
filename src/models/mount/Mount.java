package models.mount;

import models.entities.Entity;
import models.entities.NPC;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.map.Terrain;

import java.awt.*;

/**
 * Created by denzel on 2/29/16.
 */
public abstract class Mount extends EquippableItem {
    //variables that all mounts have
    protected int movement;
    protected Entity entity;

    //constructor
    public Mount(){

    }

    //methods that all Mounts should have

    //what happens when an entity touches the mount (oo la la la)
    public abstract void setTerrain(Entity entity);

}
