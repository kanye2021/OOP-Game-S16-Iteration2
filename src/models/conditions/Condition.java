/**
 * Created by denzel
 * on 2/6/16.
 */

package models.conditions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * All Condition objects will have a condition Enum
 * extended onto each
 */

public abstract class Condition {

    private ArrayList<Variable> runtimeArguments = new ArrayList<>();
    private HashMap<Integer, Object> parameters = new HashMap<>();

    private void getRuntimeParameters(Object... args) {

        int currentPlace = 0;

        for (Variable variable : runtimeArguments) {

            parameters.put(variable.ordinal(), args[currentPlace]);
            currentPlace++;

        }

    }

    protected final void setParameter(int integer, Object object) {

        if (object == null) {

            runtimeArguments.add(Variable.values()[integer]);

        }

        parameters.put(integer, object);

    }

    protected final Object getParameter(int integer) {

        return parameters.get(integer);

    }

    public final boolean checkCondition(Object... args) {

        getRuntimeParameters(args);
        return checkConditionInternal();

    }

    protected abstract boolean checkConditionInternal();

    //Check the conditions

    //The Item Comparison Enum
    public enum Comparison {
        AT_LEAST() {
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount >= requiredCount;
            }
        },
        EXACTLY() {
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount == requiredCount;
            }
        },
        AT_MOST() {
            protected boolean isValid(int inventoryCount, int requiredCount) {
                return inventoryCount <= requiredCount;
            }
        };

        //Function to compare
        protected abstract boolean isValid(int inventoryCount, int count);

        //Returns the ordinal for the Enum
        public int getID() {
            return ordinal();
        }
    }

    private enum Variable {
        PASS0,
        PASS1,
        PASS2,
        PASS3,
        PASS4,
        PASS5,
        PASS6,
        PASS7,
        PASS8
    }

}

