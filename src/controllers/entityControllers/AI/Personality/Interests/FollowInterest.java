package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.Decision;
import controllers.entityControllers.AI.Memory.Relationship;
import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.Item;

/**
 * Created by aseber on 3/9/16.
 */
public class FollowInterest extends Interest {

    Entity entityOfInterest;

    public FollowInterest(double interestLevel) {

        super(interestLevel);

    }

    public FollowInterest(Interest interest, Object objectOfInterest) {

        super(interest.getInterestLevel());
        addInterestAttachment(objectOfInterest);

    }

    private void addInterestAttachment(Object object) {

        if (object instanceof Entity) {

            this.entityOfInterest = (Entity) object;
            //System.out.println("I'm following: " + entityOfInterest);

        }

    }

    public Interest createRuntimeInterest(Object objectOfInterest) {

        return new FollowInterest(this, objectOfInterest);

    }

    public void Update() {

        // Do nothing at moment.

    }

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

        System.out.println("IsApplicable: " + memory.getSeenEntities().containsKey(entityOfInterest));
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

    public Type getInterestType() { return Type.ENTITY; }

}
