package AI.Personality.Interests.PointInterest;

import AI.Personality.Interests.Interest;
import models.entities.npc.NPC;

/**
 * Created by aseber on 3/13/16.
 */
public abstract class PointInterest extends Interest {

    public PointInterest(double interestLevel) {

        super(interestLevel);

    }

    public final Type getInterestType() {
        return Type.POINT;
    }

    public abstract Interest createRuntimeInterest(NPC npc);

    public abstract double getInterestWeight();

}
