package models.entities.npc;

import AI.Brain;
import AI.Personality.Personality;
import models.entities.npc.actions.Attack;
import models.entities.npc.actions.Trade;
import models.entities.npc.actions.Talk;
import models.entities.npc.actions.UseItemOnNPC;
import models.items.Item;
import models.items.takeable.TakeableItem;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Smasher;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dyeung on 2/28/16.
 */
public class ShopKeeper extends NPC {

    public ShopKeeper(Point location, Map map) {

        super(location, map);
        passableTerrain.add("grass");
        initInventory(); //Adds items to the inventory
        modifyActions();
        brain = new Brain(this, Personality.FAT_LARD_SHOPKEEPER); // Agnostic is the default personailty.
    }
    //---------NPC Stuff------------
    public void modifyActions(){
        actionList.add(new Trade(this));
    }
    @Override
    public void initDialogue(){
        //dialogue.clear(); //Might be unnecessary
        actionList.add(new UseItemOnNPC(this));
        dialogue.add("Welcome to my store where I can sell you stuff!");
        dialogue.add("Don't have much though...");
    }
    public void initInventory(){
        int id = 1000; //starts at 1000 (HELMS)
        for (int i = 0; i < 2; i++) { //default level
            Item newItem = Item.ItemDictionary.itemFromID(id + i);
            buy((TakeableItem)newItem);
        }
    }

    ///------------Entity Stuff--------------
    //TODO: For now smashers are villagers
    protected Occupation initOccupation(){
        return new Smasher();
    }


    //TODO: Refactor this because NPC's shouldn't start interaction I believe.
    @Override
    public void startInteraction(NPC npc) {

    }

    protected HashMap<Map.Direction, String> initSprites(){
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-shopkeeper-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();
        imagePaths.put(Map.Direction.NORTH, imageBasePath + "N.png");
        imagePaths.put(Map.Direction.NORTH_EAST, imageBasePath + "NE.png");
        imagePaths.put(Map.Direction.SOUTH_EAST, imageBasePath + "SE.png");
        imagePaths.put(Map.Direction.SOUTH, imageBasePath + "S.png");
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "SW.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");

        return imagePaths;
    }

    public void talk(){

    }
    public void buy(TakeableItem item){
        int currentValue = item.getMonetaryValue();
        item.setMonetaryValue(currentValue + 10); //NPCS add value to the item
        inventory.addItem(item);
    }
    public void sell(TakeableItem item){
        int currentValue = item.getMonetaryValue();
        item.setMonetaryValue(currentValue - 10); //Item value returns back to original
        inventory.removeItem(item);
    }
    @Override
    public final String getType() {

       // return "ShopKeeper" + "-" + super.getType();
        return "ShopKeeper";
    }

    @Override
    protected ArrayList<Image> getAnimatorImages() {


        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-shopkeeper-");


        ArrayList<Image> imagePaths = new ArrayList<>();

        imagePaths.add(new ImageIcon(imageBasePath + "N.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "NE.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "NW.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "S.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "SE.png").getImage());
        imagePaths.add(new ImageIcon(imageBasePath + "SW.png").getImage());

        return imagePaths;
    }
}
