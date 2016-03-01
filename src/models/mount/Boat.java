package models.mount;

import models.Equipment;
import models.entities.Entity;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

public class Boat extends Mount {

    public Boat(){
        ID = ItemDictionary.BOAT;
        name = "Horse";
        description = "Tis a horse";
        component = Equipment.Component.MOUNT;
        sprite = new Sprite("pathname");
        onEquipModifications = new StatModificationList(
                new StatModification(Stats.Type.MOVEMENT,30)
        );
    }


    @Override
    public void setTerrain(Entity entity) {
        entity.setPassableTerrain("sea");
    }


}