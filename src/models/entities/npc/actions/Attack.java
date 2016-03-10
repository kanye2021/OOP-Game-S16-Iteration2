package models.entities.npc.actions;

import controllers.GameViewController;
import controllers.ViewController;
import models.entities.Avatar;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

import models.entities.npc.NPC;
import models.entities.npc.actions.Action;
import views.GameView;
import views.View;

/**
 * Created by dyeung on 2/28/16.
 */
public class Attack extends Action {
    public Attack(NPC npc){
        super(npc);
    }

    @Override
    public void activate(View view, ViewController viewController){
        //Since Attack activate doesn't create a new view, the view/viewcontrollers will be
        //be able to attack the npc
        doDamage();
    }

    public void doDamage(){
        Stats npcStats = npc.getStats();
        npcStats.modifyStat(Stats.Type.HEALTH, -1);
        System.out.println("NPC Current HP: " + npcStats.getStat(Stats.Type.HEALTH));
    }


    @Override
    public String getName() {
        return "Attack";
    }


}
