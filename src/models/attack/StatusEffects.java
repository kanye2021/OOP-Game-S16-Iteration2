package models.attack;

/**
 * Created by ben on 3/11/16.
 */
public class StatusEffects {
    //protected Entity entity;
    private StatusEffect status;

    /*public StatusEffects(Entity entity){
        this.entity = entity;
        status = StatusEffect.NONE;
    }*/

   /* public StatusEffects() {
    }*/

    //This is used to make something take an effect
    //You want to apply the statusEffect immediately
   /* public void setStatusEffect(StatusEffect newStatus){
        status = newStatus;
        applyStatusEffect();
    }*/
    /*public void applyStatusEffect(){
        if(status == StatusEffect.SLEEP){
            //Do sleep shit here
            new Sleep();
        }else if(status == StatusEffect.NONE){
            //Nothing should happen here
        }
        else{
            System.out.println("How did you get here?");
        }
        //Don't do anything if you can find status
    }*/
    public String getStatusEffect() {
        return status.toString();
    }

    public void clearStatus() {
        status = StatusEffect.NONE;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public enum StatusEffect {
        SLEEP,
        INVISIBLE,
        NONE
    }
}
