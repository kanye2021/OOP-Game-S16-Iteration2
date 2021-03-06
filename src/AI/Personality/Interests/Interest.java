package AI.Personality.Interests;

import AI.Memory.Memory;
import AI.Memory.ThoughtInterface;

import java.awt.*;

/**
 * Created by aseber on 3/9/16.
 */
public abstract class Interest {

    private Point pointOfInterest;
    private double interestLevel;
    public Interest(double interestLevel) {

        this.interestLevel = interestLevel;

    }

    public Point getPointOfInterest() {

        return pointOfInterest;

    }

    protected void setPointOfInterest(Point pointOfInterest) {

        this.pointOfInterest = pointOfInterest;

    }

    public abstract void update(Memory memory);

    public abstract boolean isApplicable(ThoughtInterface memory);

    public double getInterestLevel() {

        return interestLevel;

    }

    public abstract Interest.Type getInterestType();

    public abstract String getSpecificInterest();

    public enum Type {

        ENTITY,
        ITEM,
        POINT

    }

}
