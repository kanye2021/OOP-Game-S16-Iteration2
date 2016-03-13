package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.Memory;
import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.items.Item;

import java.awt.*;

/**
 * Created by aseber on 3/9/16.
 */
public abstract class Interest {

    public enum Type {

        ENTITY(0),
        ITEM(1),
        POINT(2),
        NONE(3);

        private int ID;

        Type(int ID){
            this.ID = ID;
        }
        public int getID() {

            return ID;

        }
    }

    protected Type ID;

    public Type getID(){
        return ID;
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

    public abstract void Update();

    public abstract Interest createRuntimeInterest(Object objectOfInterest, Point pointOfInterest);

    public abstract double getInterestWeight(Object objectofInterest, ThoughtInterface memory);

    public abstract boolean isApplicable(ThoughtInterface memory);

    public double getInterestLevel() {

        return interestLevel;

    }

    public abstract Interest.Type getInterestType();


    public  boolean equalWTF(Object o) {
        System.out.println("wtf");
        System.out.println(o.toString());
        if (o instanceof Interest) {

            Interest otherItem = (Interest) o;

            return this.getID()== otherItem.getID();

        }

        return false;

    }

}
