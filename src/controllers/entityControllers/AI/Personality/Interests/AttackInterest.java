package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.Memory;
import controllers.entityControllers.AI.Memory.Relationship;
import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.Item;
import models.items.takeable.TakeableItem;

/**
 * Created by aseber on 3/11/16.
 */
public class AttackInterest extends Interest {

    private Item itemOfInterest;

    public AttackInterest(double interestLevel) {

        super(interestLevel);

    }

    private AttackInterest(Interest interest, Object objectOfInterest) {

        super(interest.getInterestLevel());
        addInterestAttachment(objectOfInterest);

    }

    public Interest createRuntimeInterest(Object objectOfInterest) {

        return new AttackInterest(this, objectOfInterest);

    }

    private void addInterestAttachment(Object object) {

        if (object instanceof Item) {

            this.itemOfInterest = (Item) object;

        }

    }

    public void Update(Memory memory) {

        // Do nothing at moment.

    }

    public double getInterestWeight(Object objectofInterest, ThoughtInterface memory) {

        double weight = 0.0;

        if (objectofInterest instanceof TakeableItem) {

//            TakeableItem item = (TakeableItem) objectofInterest;
//            weight = item.getMonetaryValue() * 100 * getInterestLevel();

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenItems().containsKey(itemOfInterest);

    }

    public Type getInterestType() { return Type.ITEM; }

}
