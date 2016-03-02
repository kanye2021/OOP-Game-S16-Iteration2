package models.conditions;
import models.entities.Entity;
import models.stats.Stats;

/**
 * Created by ben on 2/28/16.
 */

//TODO: Polish/Test the condition checker
public class StatsCondition{
    //Purpose is the go between for skills and condition
    private Condition.Comparison comparison;

    public boolean checkConditionAtLeast(int currentlyHave,int constraint){
        return(comparison.AT_LEAST.isValid(currentlyHave,constraint));
    }

    public boolean checkConditionExactly(int currentlyHave,int constraint){
        return(comparison.EXACTLY.isValid(currentlyHave,constraint));
    }

    public boolean checkCondtionAtMost(int currentlyHave,int constraint){
        return(comparison.AT_MOST.isValid(currentlyHave,constraint));
    }
}
