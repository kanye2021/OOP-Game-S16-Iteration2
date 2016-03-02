package models.stats;

/**
 * Created by Bradley on 2/19/16.
 */
public class StatModification {

    private Stats.Type statToModify;
    private int modifyAmount;


    public StatModification(Stats.Type statType, int modifyAmount) {
        this.statToModify = statType;
        this.modifyAmount = modifyAmount;
    }

    public void apply(Stats stats){

        switch(statToModify) {
            case LIVES:
                stats.modifyLives(modifyAmount);
                break;
            case STRENGTH:
                stats.modifyStrength(modifyAmount);
                break;
            case AGILITY:
                stats.modifyLives(modifyAmount);
                break;
            case INTELLECT:
                stats.modifyIntellect(modifyAmount);
                break;
            case HARDINESS:
                stats.modifyHardiness(modifyAmount);
                break;
            case EXPERIENCE:
                stats.modifyExperience(modifyAmount);
                break;
            case MOVEMENT:
                stats.modifyMovement(modifyAmount);
                break;
            case HEALTH:
                stats.modifyHealth(modifyAmount);
                break;
            case MANA:
                stats.modifyMana(modifyAmount);
                break;
            case WEAPON_MODIFIER:
                stats.modifyWeaponModifier(modifyAmount);
                break;
            case ARMOR_MODIFIER:
                stats.modifyArmorModifier(modifyAmount);
                break;
            case RADIUS_OF_VISIBILITY:
                stats.modifyRadiusOfVisibility(modifyAmount);
                break;
        }

    }

    public void remove(Stats stats){

        switch(statToModify) {
            case LIVES:
                stats.modifyLives(-modifyAmount);
                break;
            case STRENGTH:
                stats.modifyStrength(-modifyAmount);
                break;
            case AGILITY:
                stats.modifyLives(-modifyAmount);
                break;
            case INTELLECT:
                stats.modifyIntellect(-modifyAmount);
                break;
            case HARDINESS:
                stats.modifyHardiness(-modifyAmount);
                break;
            case EXPERIENCE:
                stats.modifyExperience(-modifyAmount);
                break;
            case MOVEMENT:
                stats.modifyMovement(-modifyAmount);
                break;
            case HEALTH:
                stats.modifyHealth(-modifyAmount);
                break;
            case MANA:
                stats.modifyMana(-modifyAmount);
                break;
            case WEAPON_MODIFIER:
                stats.modifyWeaponModifier(-modifyAmount);
                break;
            case ARMOR_MODIFIER:
                stats.modifyArmorModifier(-modifyAmount);
                break;
            case RADIUS_OF_VISIBILITY:
                stats.modifyRadiusOfVisibility(-modifyAmount);
                break;
        }

    }
}
