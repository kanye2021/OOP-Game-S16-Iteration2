package models.conditions;
import models.entities.Entity;
import models.items.takeable.equippable.EquippableItem;
import models.stats.Stats;
import utilities.Toast;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by ben on 2/28/16.
 */

//TODO: Polish/Test the condition checker
public class StatCondition extends Condition {
    // Param 0 = Entity
    // Param 1 = int
    // Param 2 = Stats.Type
    // Param 3 = Condition.Comparison

    public StatCondition(Entity entity, int requiredAmount, Stats.Type statType, Condition.Comparison comparison) {
        setParameter(0, entity);
        setParameter(1, requiredAmount);
        setParameter(2, statType);
        setParameter(3, comparison);
    }

    protected boolean checkConditionInternal() {

        Entity entity = (Entity) getParameter(0);
        int requiredAmount = (int) getParameter(1);
        Stats.Type statType = (Stats.Type) getParameter(2);
        Condition.Comparison comparison = (Comparison) getParameter(3);

        Stats stats = entity.getStats();

        int currentAmount = stats.getStat(statType);
        if (!comparison.isValid(currentAmount, requiredAmount)){
            Toast.createToastWithTimer("Your stats are too low!", 1000);
        }
        return (comparison.isValid(currentAmount, requiredAmount));

    }
}
