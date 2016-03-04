package models.stats;

import java.util.ArrayList;

/**
 * Created by Bradley on 2/19/16.
 */
public class StatModificationList {

    ArrayList<StatModification> modifications = new ArrayList<>();

    public StatModificationList(StatModification... modifications){

        for(StatModification modification : modifications){

            this.modifications.add(modification);

        }

    }

    public void applyStats(Stats stats){

        for (StatModification statMod : modifications){

            statMod.apply(stats);
        }

    }

    public void removeStats(Stats stats){

        for (StatModification statMod : modifications){

            statMod.remove(stats);
        }

    }

    public ArrayList<StatModification> getModifications() {
        return modifications;
    }
}
