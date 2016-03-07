/**
 * Created by aseber
 * on 2/8/16.
 */

package models.conditions;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ORCondition extends Condition {

    ArrayList<Condition> conditions = new ArrayList<Condition>();

    public ORCondition(Condition... conditionsToAdd) {

        for (Condition condition : conditionsToAdd) {

            conditions.add(condition);

        }

    }

    public boolean checkCondition(Object... args) {

        for (Condition condition : conditions) {

            if (condition.checkCondition(args)) {

                return true;

            }

        }

        return false;

    }

}
