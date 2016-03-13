package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.items.takeable.TakeableItem;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class ExploreInterest extends Interest {

    public ExploreInterest(double interestLevel) {

        super(interestLevel);

    }

    private ExploreInterest(Interest interest, Point pointOfInterest) {

        super(interest.getInterestLevel());
        setPointOfInterest(pointOfInterest);

    }

    public Interest createRuntimeInterest(Point pointOfInterest) {

        return new ExploreInterest(this, pointOfInterest);

    }

    public void update() {

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
        //return memory.getSeenItems().containsKey(itemOfInterest);
        return false; // BAD! need to check if npc.getLocation.equals(pointOfInterest);

    }

    public Type getInterestType() { return Type.ITEM; }

}
