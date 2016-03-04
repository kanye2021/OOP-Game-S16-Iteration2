package models.stats;

import models.items.interactive.InteractiveItem;

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

        stats.modifyStat(statToModify, modifyAmount);

    }

    public void remove(Stats stats){

        stats.modifyStat(statToModify, -modifyAmount);

    }

    public String toString() {
        String sign = modifyAmount > 0 ? "+" : "-";
        return sign + Integer.toString(modifyAmount) + " " + statToModify.getDescriptor();
    }
}
