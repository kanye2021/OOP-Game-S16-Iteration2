package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.Memory;
import controllers.entityControllers.AI.Memory.Relationship;
import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.Item;
import models.items.takeable.TakeableItem;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class ItemPickupInterest extends Interest {

    private Item itemOfInterest;

    public ItemPickupInterest(double interestLevel) {

        super(interestLevel);

    }

    private ItemPickupInterest(Interest interest, Object objectOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel());
        addInterestAttachment(objectOfInterest);
        setPointOfInterest(pointOfInterest);

    }

    public Interest createRuntimeInterest(Object objectOfInterest, Point pointOfInterest) {

        return new ItemPickupInterest(this, objectOfInterest, pointOfInterest);

    }

    private void addInterestAttachment(Object object) {

        if (object instanceof Item) {

            this.itemOfInterest = (Item) object;

        }

    }

    public void Update() {

        // Do nothing, the item doesn't move!

    }

    public double getInterestWeight(Object objectofInterest, ThoughtInterface memory) {

        double weight = 0.0;

        if (objectofInterest instanceof TakeableItem) {

            TakeableItem item = (TakeableItem) objectofInterest;
            weight = item.getMonetaryValue() * 100 * getInterestLevel();

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenItems().containsKey(itemOfInterest);

    }

    public Type getInterestType() { return Type.ITEM; }

}
