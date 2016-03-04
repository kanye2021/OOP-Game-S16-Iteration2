package models.conditions;

import models.entities.Entity;

/**
 * Created by aseber
 * on 2/9/16.
 */
public abstract class MapCondition extends Condition {

    protected MapCondition(Variable... runtimeParameters) {

        super(runtimeParameters);

    }

    public enum Location {

        LOCATED_AT() {public boolean checkLocation(Object e1, Object e2) {return e1.equals(e2);}},
        NOT_LOCATED_AT {public boolean checkLocation(Object e1, Object e2) {return e1.equals(e2);}};

        public abstract boolean checkLocation(Object e1, Object e2);

    }

}
