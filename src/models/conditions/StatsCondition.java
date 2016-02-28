package models.conditions;
import models.entities.Entity;
import models.stats.Stats;

/**
 * Created by ben on 2/28/16.
 */
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
    if(statType.equals("LIVES")){

        entityAmt = stats.getLives();

    }else if(statType.equals("STRENGTH")){

        entityAmt = stats.getStrength();

    }else if(statType.equals("AGILITY")){

        entityAmt = stats.getAgility();

    }else if(statType.equals("INTELLECT")){

        entityAmt = stats.getIntellect();

    }else if(statType.equals("HARDINESS")){

        entityAmt = stats.getHardiness();

    }else if(statType.equals("EXPERIENCE")){

        entityAmt = stats.getExperience();

    }else if(statType.equals("MOVEMENT")){

        entityAmt = stats.getMovement();

    }else if(statType.equals("HEALTH")){

        entityAmt = stats.getHealth();

    }else if(statType.equals("MANA")){

        entityAmt = stats.getMana();

    }else if(statType.equals("WEAPON_MODIFIER")){

        entityAmt = stats.getWeaponModifier();

    }else if(statType.equals("ARMOR_MODIFIER")){

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
