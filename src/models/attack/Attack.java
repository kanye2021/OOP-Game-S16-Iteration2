package models.attack;

import models.entities.Entity;
import models.map.Map;
import models.map.Tile;

import java.awt.*;

/**
 * Created by denzel on 3/4/16.
 */
public abstract class Attack{

    //all attacks have range and the entity who fired the attack and the tile as reference
    protected Map map;
    protected Entity entity;
    protected int range;
    protected Point location;

    public Attack(Entity entity, int range, Map map){
        this.entity = entity;
        this.range = range;
        this.map = map;
    }



    //any other methods that all attacks should have
    protected abstract void initSprites();
    protected abstract boolean hitEntity();

}
