package models.entities.npc;

import models.entities.npc.actions.Attack;
import models.entities.npc.actions.Buy;
import models.entities.npc.actions.Talk;
import models.items.Item;
import models.items.takeable.TakeableItem;
import models.map.Map;
import models.occupation.Occupation;
import models.occupation.Smasher;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by dyeung on 2/28/16.
 */
public class ShopKeeper extends NPC{

    public ShopKeeper(Point location, Map map) {

        super(location, map);
        passableTerrain.add("grass");
        initActions();
        initInventory(); //Adds items to the inventory
    }
    //---------NPC Stuff------------
    public void initActions(){
        actionList.add(new Talk(this));
        actionList.add (new Attack(this));
        actionList.add(new Buy(this));
        dialogue = "Welcome to my store where I can sell you stuff!";
    }
    public void initInventory(){
        int id = 1000; //starts at 1000 (HELMS)
        for (int i = 0; i < 2; i++) { //default level
            Item newItem = Item.ItemDictionary.itemFromID(id + i);
            ((TakeableItem)newItem).setMonetaryValue(500 + (i * 100));
            inventory.addItem((TakeableItem)newItem);
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
        imagePaths.put(Map.Direction.SOUTH_WEST, imageBasePath + "W.png");
        imagePaths.put(Map.Direction.NORTH_WEST, imageBasePath + "NW.png");

        return imagePaths;
    }

    public void talk(){

    }
    public void buy(){

    }
    public void sell(TakeableItem item){
        inventory.removeItem(item);
    }


}
