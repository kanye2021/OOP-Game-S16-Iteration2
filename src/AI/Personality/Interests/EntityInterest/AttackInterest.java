package AI.Personality.Interests.EntityInterest;

import AI.Memory.Memory;
import AI.Memory.MotorInterface;
import AI.Memory.ThoughtInterface;
import models.Equipment;
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
        NPC npc = memory.getNPCforAttack();
        npc.basicAttack(entityOfInterest);
    }

    public double getInterestWeight(Entity entityOfInterest, ThoughtInterface memory) {

        double weight = 100.0;

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

    @Override
    public String getSpecificInterest() {
        String test = "AttackInterest";
        System.out.println(test);
        return test;
    }
}
