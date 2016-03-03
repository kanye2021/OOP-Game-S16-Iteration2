package models.conditions;
import models.entities.Entity;
import models.stats.Stats;

/**
 * Created by ben on 2/28/16.
 */

//TODO: Polish/Test the condition checker
public class StatCondition {
    private Entity entity;
    private Condition.Comparison comparison;
    private Stats stats;
    private Stats.Type statType;
    private int entityAmt;//This is to hold if the entity has enough of the stat

    private int requiredAmount;
    public StatCondition(Entity entity, int requiredAmt, Stats.Type statType, Condition.Comparison comparison){
        this.entity = entity;
        this.stats = entity.getStats();
        this.statType = statType;
        this.comparison = comparison;
        this.requiredAmount = requiredAmt;
    }
    //getStatType is used to find the stats of a certain entity so we can compare it in checkCondition
    private void getStatType(){

        //First things first find it!

        entityAmt = stats.getStat(statType);

    }

    public boolean checkCondition(){
        getStatType();
        return (comparison.isValid(entityAmt, requiredAmount));
    }
}
