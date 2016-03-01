package models.entities.npc;

/**
 * Created by dyeung on 2/28/16.
 */
public class Talk extends Action{
    private String dialogue;
    public Talk(String text){
        dialogue = text;
    }
    public String getType() {
        return "talk";
    }
    public void activate(){

    }
}
