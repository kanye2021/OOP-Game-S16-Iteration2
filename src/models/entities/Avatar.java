package models.entities;

import controllers.GameViewController;
import controllers.entityControllers.AvatarController;
import controllers.entityControllers.EntityController;
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
        money = 1000;
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

    @Override
    protected final EntityController initController() {

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
        int price = item.getPrice();
        if (money >= price) { //Buy the item
            inventory.addItem(item);
            money -= item.getPrice();
        }
    }
    public void sellItem(TakeableItem item){
        int price = item.getPrice()/2;
        inventory.removeItem(item);
        money += item.getPrice();
    }
}
