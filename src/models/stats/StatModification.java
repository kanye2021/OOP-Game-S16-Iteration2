package models.stats;

/**
 * Created by Bradley on 2/19/16.
 */
public class StatModification {

    private Stats.StatType statToModify;
    private int modifyAmount;


    public StatModification(Stats.StatType statType, int modifyAmount){
        this.statToModify = statType;
        this.modifyAmount = modifyAmount;
    }


    public void apply(Stats stats){

        switch(statToModify) {
            case LIVES:
                stats.modifyLives(modifyAmount);
                break;
            case STRENGTH:
                //stats.modifyStrength(modifyAmount);
                break;
            case AGILITY:
                stats.modifyLives(modifyAmount);
                break;
            case INTELLECT:
                break;
            case HARDINESS:
                break;
            case EXPERIENCE:
                break;
            case MOVEMENT:
                break;
            case HEALTH:
                break;
            case MANA:
                break;
            case WEAPON_MODIFIER:
                break;
            case ARMOR_MODIFIER:
                break;
        }

    }

    public void remove(Stats stats){

    }
}
