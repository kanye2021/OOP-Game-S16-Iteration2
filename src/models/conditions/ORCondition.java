/**
 * Created by aseber
 * on 2/8/16.
 */

package models.conditions;

import java.util.ArrayList;

public class ORCondition extends Condition {

    ArrayList<Condition> conditions = new ArrayList<Condition>();

    public ORCondition(Condition... conditionsToAdd) {

        for (Condition condition : conditionsToAdd) {

            conditions.add(condition);

        }

    }

    protected boolean checkConditionInternal() {

        for (Condition condition : conditions) {

            if (condition.checkConditionInternal()) {

                return true;

            }

        }

        return false;

    }

}
