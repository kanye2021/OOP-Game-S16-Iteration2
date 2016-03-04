package models.entities.npc;

/**
 * Created by denzel on 3/3/16.
 */
public class Unmount extends Action{

    public Unmount(NPC npc){
        super(npc);
    }

    @Override
    public String getName() {
        return "Unmount";
    }

    @Override
    public void activate() {
        unmount();
    }


    public void unmount(){
        System.out.println("I am trying to unmount");
    }
}
