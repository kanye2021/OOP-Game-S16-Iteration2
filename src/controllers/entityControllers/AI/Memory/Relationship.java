package controllers.entityControllers.AI.Memory;

import models.entities.Entity;
import utilities.MathUtilities;

/**
 * Created by aseber on 3/10/16.
 */
public class Relationship {

    private double relationship; //Value spans [-1, 1]

    public Relationship(double relationship) {

        this.relationship = relationship;

    }

    public void setRelationship(double setValue) {
        System.out.println("I'm setting the relationship");
        this.relationship = setValue;
        MathUtilities.putInRange(-1.0, this.relationship, 1.0);

    }

    public void modifyRelationship(double delta) {

        double newValue = this.relationship + delta;
        MathUtilities.putInRange(-1.0, newValue, 1.0);
        this.relationship = newValue;

    }

    public double getRelationshipValue() {

        return relationship;

    }

    public boolean isFriend() {

        return relationship > 0;

    }

    public boolean isNeutral() {

        return relationship == 0;

    }

    public boolean isEnemy() {

        return relationship < 0;

    }

}
