package AI.Memory;

import utilities.MathUtilities;

/**
 * Created by aseber on 3/10/16.
 */
public class Relationship {

    private double relationship; //Value spans [-1, 1]

    public Relationship(double relationship) {

        MathUtilities.putInRange(-1.0, relationship, 1.0);
        this.relationship = relationship;

    }

    public void setRelationship(double setValue) {
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
