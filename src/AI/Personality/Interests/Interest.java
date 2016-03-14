package AI.Personality.Interests;

import AI.Memory.ThoughtInterface;

import java.awt.*;

/**
 * Created by aseber on 3/9/16.
 */
public abstract class Interest {

    public enum Type {

        ENTITY,
        ITEM,
        POINT

    }

    private Point pointOfInterest;
    private double interestLevel;

    public Interest(double interestLevel) {

        this.interestLevel = interestLevel;

    }

    protected void setPointOfInterest(Point pointOfInterest) {

        this.pointOfInterest = pointOfInterest;

    }

    public Point getPointOfInterest() {

        return pointOfInterest;

    }

    public abstract void update();

    public abstract boolean isApplicable(ThoughtInterface memory);

    public double getInterestLevel() {

        return interestLevel;

    }

    public abstract Interest.Type getInterestType();

}