package models.entities;

import controllers.entityControllers.AvatarController;
import models.entities.npc.NPC;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.stats.StatModificationList;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Avatar extends Entity {
    private int radiusOfVisiblility;
    private int money;
    public Avatar(Point location, Map map) {
        super(location, map);
        this.radiusOfVisiblility = 4;
        money = 100;
    }

    @Override
    protected final StatModificationList initInitialStats() {

        StatModificationList initialStats = new StatModificationList(
            /*new StatModification(Stats.Type.LIVES, 3, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.LEVEL, 1, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.AGILITY, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.INTELLECT, 10, StatModification.NumberType.POINT),
            new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)*/
        );



        return initialStats;

    }

    //overiding function for skills mapping

    protected final AvatarController initController() {

        return null; // Keyboard controller!

    }
    @Override
    public void startInteraction(NPC npc){


        return;
    }

    public int getAmountofMoney(){
        return money;
    }
    public void buyItem(TakeableItem item){
        int price = item.getMonetaryValue();
        if (money >= price) { //Trade the item (two checks are being done
            inventory.addItem(item);
            money -= item.getMonetaryValue();
        }
    }
    public void sellItem(TakeableItem item, int factor){
        int price = item.getMonetaryValue()/factor;
        inventory.removeItem(item);
        money += price;
    }

    public String getType() {

        return "Avatar" + "-" + super.getType();

    }

}
