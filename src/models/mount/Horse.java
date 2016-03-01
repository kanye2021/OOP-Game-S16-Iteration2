package models.mount;

import models.entities.Entity;
import models.map.Map;
import utilities.IOUtilities;
import views.sprites.Sprite;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by denzel on 2/29/16.
 */
public class Horse extends Mount {

    public Horse(Entity entity){
        super(entity);
        setTerrain(entity);
    }


    @Override
    public void setTerrain(Entity entity) {
        entity.setPassableTerrain("grass");
    }
}
