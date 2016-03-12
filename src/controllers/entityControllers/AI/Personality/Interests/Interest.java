package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;

/**
 * Created by aseber on 3/9/16.
 */
public abstract class Interest {

    public enum Type {

        ENTITY,
        ITEM,
        POINT,
        NONE

    }

    private double interestLevel;

    public Interest(double interestLevel) {

        this.interestLevel = interestLevel;

    }

    public abstract void Update();

    public abstract Interest createRuntimeInterest(Object objectOfInterest);

    public abstract double getInterestWeight(Object objectofInterest, ThoughtInterface memory);

    public abstract boolean isApplicable(ThoughtInterface memory);

    public double getInterestLevel() {

        return interestLevel;

    }

    public abstract Interest.Type getInterestType();

}
