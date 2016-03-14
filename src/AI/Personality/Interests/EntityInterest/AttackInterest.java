package AI.Personality.Interests.EntityInterest;

import AI.Memory.Memory;
import AI.Memory.Relationship;
import AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.entities.npc.NPC;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class AttackInterest extends EntityInterest {

    public AttackInterest(double interestLevel) {

        super(interestLevel);

    }

    private AttackInterest(AttackInterest interest, Entity entityOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel(), entityOfInterest);
        setPointOfInterest(pointOfInterest);

    }

    public AttackInterest createRuntimeInterest(Entity entityOfInterest, Point pointOfInterest) {

        return new AttackInterest(this, entityOfInterest, pointOfInterest);

    }

    public void update(Memory memory) {
        NPC npc = memory.getNPC();
        npc.basicAttack(entityOfInterest);
        //setPointOfInterest(entityOfInterest.getLocation());
    }

    public double getInterestWeight(Entity entityOfInterest, ThoughtInterface memory) {

        Relationship relationship = memory.getEntityRelationship(entityOfInterest);
        double relationshipValue = -relationship.getRelationshipValue();
        double aggresiveness = memory.getPersonality().getAggressiveness();
        double weight = 0.0;

        if (relationship.isEnemy()) {

            weight = relationshipValue * 1000.0 * getInterestLevel() * aggresiveness;

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

    @Override
    public String getSpecificInterest() {
        return "AttackInterest";
    }
}
