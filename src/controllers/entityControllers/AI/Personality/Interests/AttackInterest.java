package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.takeable.TakeableItem;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class AttackInterest extends EntityInterest {

    public AttackInterest(double interestLevel) {

        super(interestLevel);

    }

    private AttackInterest(Interest interest, Entity entityOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel(), entityOfInterest);
        setPointOfInterest(pointOfInterest);

    }

    public Interest createRuntimeInterest(Entity entityOfInterest, Point pointOfInterest) {

        return new AttackInterest(this, entityOfInterest, pointOfInterest);

    }

    public void update() {

        setPointOfInterest(entityOfInterest.getLocation());

    }

    public double getInterestWeight(Entity entityOfInterest, ThoughtInterface memory) {

        double weight = 0.0;

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

}
