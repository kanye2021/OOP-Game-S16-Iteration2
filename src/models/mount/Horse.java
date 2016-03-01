package models.mount;

import models.Equipment;
import models.entities.Entity;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by denzel on 2/29/16.
 */
public class Horse extends Mount {

    public Horse(){
        ID = ItemDictionary.HORSE;
        name = "Horse";
        description = "Tis a horse";
        component = Equipment.Component.MOUNT;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.MOVEMENT,20)
        );
    }


    @Override
    public void setTerrain(Entity entity) {
        entity.setPassableTerrain("grass");
    }
}
