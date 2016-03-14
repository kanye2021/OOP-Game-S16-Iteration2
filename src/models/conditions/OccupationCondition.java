package models.conditions;

import models.entities.Entity;
import utilities.Toast;

/**
 * Created by dyeung on 3/13/16.
 */
public class OccupationCondition extends Condition {

    public OccupationCondition(Entity entity, String occupation, Condition.Comparison comparison) {
        setParameter(0, entity);
        setParameter(1, occupation);
        setParameter(2, comparison);
    }

    @Override
    protected boolean checkConditionInternal() {
        Entity entity = (Entity) getParameter(0);
        String occupation = (String) getParameter(1);
        Condition.Comparison comparison = (Comparison) getParameter(2);

        String entityOccupation = entity.getOccupation();

        //return (comparison.isValid(occupation, entityOccupation));
        if (!occupation.equals(entityOccupation)) {
            Toast.createToastWithTimer("You have the wrong occupation!", 1000);
        }
        return (occupation.equals(entityOccupation));
    }
}
