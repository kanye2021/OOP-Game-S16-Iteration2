package models.stats;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/19/16.
 */
public class StatModificationList extends ArrayList<StatModification> {

    public StatModificationList(StatModification... modifications){

        for(StatModification mod : modifications){
            add(mod);
        }
    }

    public void applyStats(Stats stats){

        for (StatModification statMod : this){

            statMod.apply(stats);
        }
    }

    public void removeStats(Stats stats){

        for (StatModification statMod : this){

            statMod.remove(stats);
        }
    }
}
