/**
 * Created by denzel
 * on 2/6/16.
 */

package models.conditions;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * All Condition objects will have a condition Enum
 * extended onto each
 */

public abstract class Condition {

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

    public enum Variable {
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

    protected Condition(Variable... runtimeArguments) {

        this.runtimeArguments = new ArrayList(Arrays.asList(runtimeArguments));

    }

    private ArrayList<Variable> runtimeArguments = new ArrayList<>();
    private HashMap<Integer, Object> parameters = new HashMap<>();

    protected final void getRuntimeParameters(Object... args) {

        int currentPlace = 0;

        for (Variable variable : runtimeArguments) {

            parameters.put(variable.ordinal(), args[currentPlace]);
            currentPlace++;

        }

    }

    protected final void setParameter(int integer, Object object) {

        parameters.put(integer, object);

    }

    protected final Object getParameter(int integer) {

        return parameters.get(integer);

    }

    //Check the conditions
    public abstract boolean checkCondition(Object... args);

}

