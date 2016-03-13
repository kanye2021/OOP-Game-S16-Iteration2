package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.Relationship;
import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;

import java.awt.*;

/**
 * Created by aseber on 3/9/16.
 */
public class FollowInterest extends EntityInterest {

    public FollowInterest(double interestLevel) {
        super(interestLevel);
    }

    public FollowInterest(Interest interest, Entity entityOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel(), entityOfInterest);
        setPointOfInterest(pointOfInterest);


    }

    public Interest createRuntimeInterest(Entity entityOfInterest, Point pointOfInterest) {

        return new FollowInterest(this, entityOfInterest, pointOfInterest);

    }

    public void update() {

        setPointOfInterest(entityOfInterest.getLocation());

    }

    @Override
    public double getInterestWeight(Object objectofInterest, ThoughtInterface memory) {

        double weight = 0.0;

        if (objectofInterest instanceof Entity) {

            Entity entity = (Entity) objectofInterest;
            Relationship relationship = memory.getEntityRelationship(entity);
            weight = relationship.getRelationshipValue() * 1000 * getInterestLevel();


        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

}
