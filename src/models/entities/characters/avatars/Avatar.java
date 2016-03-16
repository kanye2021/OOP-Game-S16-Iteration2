package models.entities.characters.avatars;

import models.entities.Entity;
import models.factions.Faction;
import models.factions.FactionAssociation;
import models.items.takeable.TakeableItem;
import models.map.Map;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Avatar extends Entity {

    public Avatar(Point location, Map map) {
        super(location, map);
    }

    @Override
    protected final StatModificationList initInitialStats() {

        return new StatModificationList(
            new StatModification(Stats.Type.LIVES, 3),
            new StatModification(Stats.Type.LEVEL, 1),
            new StatModification(Stats.Type.AGILITY, 10),
            new StatModification(Stats.Type.STRENGTH, 10),
            new StatModification(Stats.Type.INTELLECT, 10),
            new StatModification(Stats.Type.HARDINESS, 10)
        );

    }

    public void update() {}

    protected final FactionAssociation initFaction() {

        return new FactionAssociation(1.0, Faction.BLUE);

    }

    public int getAmountofMoney() {

        return money;
    }

    public void buyItem(TakeableItem item) {
        int price = item.getMonetaryValue();
        if (money >= price) { //Trade the item (two checks are being done
            inventory.addItem(item);
            money -= item.getMonetaryValue();
        }
    }

    public void sellItem(TakeableItem item, int factor) {
        int price = item.getMonetaryValue() / factor;
        inventory.removeItem(item);
        money += price;
    }

    @Override
    public String getType() {
        //Why are we doing it this way?
        //return "Avatar" + "-" + super.getType();
        return "Avatar";
    }


}
