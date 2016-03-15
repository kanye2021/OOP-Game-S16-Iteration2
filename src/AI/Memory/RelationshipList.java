package AI.Memory;

import utilities.MathUtilities;

import java.util.HashMap;

/**
 * Created by aseber on 3/14/16.
 */
public class RelationshipList<T> {

    private final Relationship DEFAULT_RELATIONSHIP = new Relationship(0.0);

    private HashMap<T, Relationship> relationships = new HashMap<>();

    public void addRelationship(T key, Relationship relationship) {

        relationships.put(key, relationship);

    }

    public Relationship getRelationship(T key) {

        return relationships.getOrDefault(key, DEFAULT_RELATIONSHIP);

    }

    public class Relationship {

        private double relationship; //Value spans [-1, 1]

        public Relationship(double relationship) {

            MathUtilities.putInRange(-1.0, relationship, 1.0);
            this.relationship = relationship;

        }

        protected void setRelationship(double setValue) {

            MathUtilities.putInRange(-1.0, setValue, 1.0);
            this.relationship = setValue;

        }

        protected void modifyRelationship(double delta) {

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

        public String toString() {

            String output = "Relationship: ";

            if (isFriend()) {
                output += "Friend";
            } else if (isNeutral()) {
                output += "Neutral";
            } else {
                output += "Enemy";
            }

            return output;

        }

    }

}
