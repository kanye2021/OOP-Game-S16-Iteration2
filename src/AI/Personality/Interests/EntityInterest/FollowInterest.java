package AI.Personality.Interests.EntityInterest;

import AI.Memory.Relationship;
import AI.Memory.ThoughtInterface;
import models.entities.Entity;

import java.awt.*;

/**
 * Created by aseber on 3/9/16.
 */
public class FollowInterest extends EntityInterest {

    public FollowInterest(double interestLevel) {
        super(interestLevel);
    }

    public FollowInterest(FollowInterest interest, Entity entityOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel(), entityOfInterest);
        setPointOfInterest(pointOfInterest);


    }

    public FollowInterest createRuntimeInterest(Entity entityOfInterest, Point pointOfInterest) {

        return new FollowInterest(this, entityOfInterest, pointOfInterest);

    }

    public void update() {

        setPointOfInterest(entityOfInterest.getLocation());

    }

    @Override
    public double getInterestWeight(Entity entityOfInterest, ThoughtInterface memory) {

        double weight = 0.0;
        Relationship relationship = memory.getEntityRelationship(entityOfInterest);

        if (relationship.isFriend()) {

            weight = relationship.getRelationshipValue() * 1000 * getInterestLevel();

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        System.out.println(memory.entitiesSeen());
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

}
