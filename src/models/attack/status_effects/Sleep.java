package models.attack.status_effects;

import models.attack.StatusEffects;
import models.entities.Entity;

/**
 * Created by ben on 3/11/16.
 */
public class Sleep extends StatusEffects{
 //protected Entity entity;

    /*public Sleep(){
        applySleep();
    }*/
    public Sleep(Entity entity){
        applySleep(entity);
    }
    public void sleepEffects(Entity entity){
        entity.setCanMove(false);
        //How do I disable attacks?
    }
    public void sleepUneffects(Entity entity){
        entity.setCanMove(true);
    }
    public void applySleep(Entity target){
        if(!target.getCanMove()){//If entity cannot move
            return;
        }
        //Find out how movement works
        //entity.setCanMove(false);
        sleepEffects(target);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(target.getStatusEffect().toString());
                        sleepUneffects(target);

                    }
                },
                5000
        );
    }
    /*public void applySleep(){
        if(!entity.getCanMove()){//If entity cannot move
            System.out.println("The baby is already asleep");
            return;
        }
        //Find out how movement works
        entity.setCanMove(false);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("I am awake!");
                        entity.setCanMove(true);
                    }
                },
                2000
        );
    }*/
}
