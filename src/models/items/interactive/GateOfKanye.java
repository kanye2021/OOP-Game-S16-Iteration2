package models.items.interactive;

import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.StatCondition;
import models.stats.Stats;

/**
 * Created by aseber on 3/1/16.
 */
public class GateOfKanye extends InteractiveItem {

    public GateOfKanye() {

        ID = ItemDictionary.GATE_OF_KANYE;

    }

    @Override
    protected ConditionList initConditions() {

        ConditionList conditions = new ConditionList(
            new StatCondition(null, 5, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0)
        );

        return conditions;

    }

}
