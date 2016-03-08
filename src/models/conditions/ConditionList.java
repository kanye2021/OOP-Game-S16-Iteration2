package models.conditions;

import java.util.ArrayList;

/**
 * Created by denzel on 2/6/16.
 */
public class ConditionList {

    private ArrayList<Condition> conditions = new ArrayList<>();

    //Constructor that puts all the conditions for each item
    public ConditionList(Condition... conditionsList) {
        for (Condition condition : conditionsList) {
            conditions.add(condition);
        }
    }

    public boolean checkCondition(Object... args) {
        for (Condition condition : conditions) {
            if (!condition.checkCondition(args)) {
                return false;
            }
        }
        return true;
    }

}

