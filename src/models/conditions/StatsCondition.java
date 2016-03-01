package models.conditions;
import models.entities.Entity;
import models.stats.Stats;

/**
 * Created by ben on 2/28/16.
 */

//TODO: Polish/Test the condition checker
public class StatsCondition{
    private Entity entity;
    private Condition.Comparison comparison;
    private Stats stats;
    private Stats.Type statType;
    private int entityAmt;//This is to hold if the entity has enough of the stat

    private int requiredAmount;
    public StatsCondition(Entity entity, int requiredAmt, Stats.Type statType, Condition.Comparison comparison){
        this.entity = entity;
        this.stats = entity.getStats();
        this.statType = statType;
        this.comparison = comparison;
        this.requiredAmount = requiredAmt;
    }
    //getStatType is used to find the stats of a certain entity so we can compare it in checkCondition
    public void getStatType(){

    //First things first find it!
    if(statType==Stats.Type.LIVES){

        entityAmt = stats.getLives();

    }else if(statType==Stats.Type.STRENGTH){

        entityAmt = stats.getStrength();

    }else if(statType==Stats.Type.AGILITY){

        entityAmt = stats.getAgility();

    }else if(statType==Stats.Type.INTELLECT){

        entityAmt = stats.getIntellect();

    }else if(statType==Stats.Type.HARDINESS){

        entityAmt = stats.getHardiness();

    }else if(statType==Stats.Type.EXPERIENCE){

        entityAmt = stats.getExperience();

    }else if(statType==Stats.Type.MOVEMENT){

        entityAmt = stats.getMovement();

    }else if(statType==Stats.Type.HEALTH){

        entityAmt = stats.getHealth();

    }else if(statType==Stats.Type.MANA){

        entityAmt = stats.getMana();

    }else if(statType==Stats.Type.WEAPON_MODIFIER){

        entityAmt = stats.getWeaponModifier();

    }else if(statType==Stats.Type.ARMOR_MODIFIER){

        entityAmt = stats.getArmorModifier();

    }else{

        System.out.println("WTF how did you get here?");
    }



    }

    public boolean checkCondition(){
        getStatType();
        return (comparison.isValid(entityAmt,requiredAmount));
    }
}
