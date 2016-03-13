package AI.Personality.Interests.ItemInterest;

import AI.Memory.ThoughtInterface;
import models.items.Item;
import models.items.takeable.TakeableItem;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class ItemPickupInterest extends ItemInterest {

    public ItemPickupInterest(double interestLevel) {

        super(interestLevel);

    }

    private ItemPickupInterest(ItemInterest interest, Item itemOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel(), itemOfInterest);
        setPointOfInterest(pointOfInterest);

    }

    public ItemInterest createRuntimeInterest(Item itemOfInterest, Point pointOfInterest) {

        return new ItemPickupInterest(this, itemOfInterest, pointOfInterest);

    }

    public void update() {

        // Do nothing, the item doesn't move!

    }

    public double getInterestWeight(Item itemOfInterest, ThoughtInterface memory) {

        double weight = 0.0;

        if (itemOfInterest instanceof TakeableItem) {

            TakeableItem item = (TakeableItem) itemOfInterest;
            weight = item.getMonetaryValue() * 100 * getInterestLevel();

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenItems().containsKey(itemOfInterest);

    }

}
