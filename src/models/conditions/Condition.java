/**
 * Created by denzel
 * on 2/6/16.
 */

package models.conditions;

import models.Map;

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

    //Check the conditions
    public abstract boolean checkCondition();

}

