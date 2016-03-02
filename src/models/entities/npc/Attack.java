package models.entities.npc;

import models.entities.Avatar;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

/**
 * Created by dyeung on 2/28/16.
 */
public class Attack extends Action {
    public Attack(NPC npc){
        super(npc);
    }

    @Override
    public void activate(){
        //be able to attack the npc
        doDamage();
    }

    public void doDamage(){
        Stats npcStats = npc.getStats();
        npcStats.modifyHealth(-1);
        System.out.println("NPC Current HP: " + npcStats.getHealth());
    }


    @Override
    public String getName() {
        return "Attack";
    }

}
