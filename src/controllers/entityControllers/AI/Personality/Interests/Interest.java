package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;

import java.awt.*;
import java.lang.reflect.Type;

/**
 * Created by aseber on 3/9/16.
 */
public abstract class Interest {

    public enum Type {

        ENTITY(EntityInterest.class),
        ITEM(ItemInterest.class),
        POINT(PointInterest.class);

        java.lang.reflect.Type type;

        Type(java.lang.reflect.Type type) {

            this.type = type;

        }

        public java.lang.reflect.Type getClassType() {

            return type;

        }

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

    public abstract double getInterestWeight(Object objectofInterest, ThoughtInterface memory);

    public abstract boolean isApplicable(ThoughtInterface memory);

    public double getInterestLevel() {

        return interestLevel;

    }

    public abstract Interest.Type getInterestType();

}
