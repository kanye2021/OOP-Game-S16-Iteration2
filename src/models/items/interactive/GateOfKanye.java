package models.items.interactive;

import models.conditions.Condition;
import models.conditions.ConditionList;
import models.conditions.InventoryCondition;
import models.conditions.StatCondition;
import models.items.Item;
import models.stats.Stats;
import views.sprites.Sprite;

/**
 * Created by aseber on 3/1/16.
 */
public class GateOfKanye extends InteractiveItem {

    public GateOfKanye() {

        ID = ItemDictionary.GATE_OF_KANYE;
        sprite = new Sprite("./src/res/items/interactive/KanyeGate.png");

    }

    @Override
    protected ConditionList initConditions() {

        ConditionList conditions = new ConditionList(
            //new StatCondition(null, 5, Stats.Type.LEVEL, Condition.Comparison.AT_LEAST, Condition.Variable.PASS0),
            new InventoryCondition(null, Condition.Comparison.AT_LEAST, 1, ItemDictionary.KEY_OF_KANYE, Condition.Variable.PASS0)
        );

        return conditions;

    }

}
