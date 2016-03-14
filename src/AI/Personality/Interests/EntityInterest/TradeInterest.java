package AI.Personality.Interests.EntityInterest;

import AI.Memory.Memory;
import AI.Memory.MotorInterface;
import AI.Memory.Relationship;
import AI.Memory.ThoughtInterface;
import models.Equipment;
import models.entities.Entity;
import models.entities.npc.NPC;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class TradeInterest extends EntityInterest {

    public TradeInterest(double interestLevel) {

        super(interestLevel);

    }

    private TradeInterest(TradeInterest interest, Entity entityOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel(), entityOfInterest);
        setPointOfInterest(pointOfInterest);

    }

    public TradeInterest createRuntimeInterest(Entity entityOfInterest, Point pointOfInterest) {

        return new TradeInterest(this, entityOfInterest, pointOfInterest);

    }

    public void update(Memory memory) {
        NPC npc = memory.getNPC();
        npc.basicAttack(entityOfInterest);
        setPointOfInterest(memory.getNPC().getLocation());
    }

    public double getInterestWeight(Entity entityOfInterest, ThoughtInterface memory) {

        Relationship relationship = memory.getEntityRelationship(entityOfInterest);
        double relationshipValue = relationship.getRelationshipValue();
        double weight = 0.0;

        if (relationship.isFriend()) {

            weight = relationshipValue * 1000.0 * getInterestLevel();

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

    @Override
    public String getSpecificInterest() {
        return "TradeInterest";
    }
}
