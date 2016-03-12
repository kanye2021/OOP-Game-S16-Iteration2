package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.Item;

/**
 * Created by aseber on 3/9/16.
 */
public class EmptyInterest extends Interest {

    public EmptyInterest(double interestLevel) {

        super(interestLevel);

    }

    public void addInterestAttachment(Object object) {}
    public Interest createRuntimeInterest(Object objectOfInterest) { return new EmptyInterest(1.0); }
    public void Update() {}
    public double getInterestWeight(Object objectOfInterest, ThoughtInterface memory) { return 0; }
    public boolean isApplicable(ThoughtInterface memory) { return false; }

    public Type getInterestType() { return Type.NONE; }

}
