package models.entities.npc;

import models.entities.npc.actions.Action;

/**
 * Created by denzel on 3/3/16.
 */
public class Mount extends Action {

    public Mount(NPC npc){
        super(npc);
    }


    @Override
    public String getName() {
        return "Mount as in I will mount you";
    }


    @Override
    public void activate() {
        //Figure out how to implement the mount

        System.out.println("I will one day mount you");
    }


}

