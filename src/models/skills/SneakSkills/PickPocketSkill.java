package models.skills.SneakSkills;

import models.Inventory;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import models.items.takeable.TakeableItem;
import models.map.Map;
import models.map.Tile;
import models.skills.ActiveSkill;
import utilities.Toast;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aseber on 2/25/16.
 */

//TODO:Get the inventory viewport to show that Item got stolen and that you have it
    //TODO:SERGIO REED DIS.  SO I THINK IT DOESNT WORK B/C INVENTORY VIEW LOOKS AT ITEMS
    //TODO:SO SINCE I ADDED IT TO THE ITEM NODES ARRAYLIST BUT NOT THE ITEMS IT DOESNT APPEAR
    //TODO:IDK CURRENTLY HOW TO FIX
public class PickPocketSkill extends ActiveSkill {

    public PickPocketSkill(){
        // Shud call call super in ea. subclass of a skill
        super();
        cooldown = false;
        cooldownTime = 5*SECONDS;
        currentCooldownRemaining = 0;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.PICK_POCKET;

    }
    @Override
    public String getName() {
        return "Pickpocket";
    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            Toast.createToastWithTimer("Cooldown is not over yet!", 1200);
            return;
        }
        Entity target = findEntity(entity);
        if(target == null){
            System.out.println("Target is not there");
            cooldown=false;
            return;
        }
        Inventory entityInventory = entity.getInventory();
        Inventory targetInventory = target.getInventory();
        if(targetInventory.isEmpty()){
            System.out.println("Target did not have any items!!! Nothing 2 steal :(");
            Toast.createToastWithTimer("Nothing to steal over here...", 1200);
            cooldown=false;
            return;
        } else {
            // Pick that pocket
            TakeableItem stolenItem = stealItem(targetInventory);

            // Pocket the item
            entityInventory.addItem(stolenItem);

            //do toast
            Toast.createToastWithTimer("You stole an item!", 1200);

            // Cool-down that skill
            doTheCoolDown();
        }

    }

    public Entity findEntity(Entity entity){
        Point currentLocation = entity.getLocation();
        Point offset = new Point();
        Map.Direction entityOrientation = entity.getOrientation();
        //How to find target based off of location.
        Point desiredLocation = new Point();
//TODO:Refractor else if cascade into a function in Skills Class
        if(entityOrientation== Map.Direction.NORTH){
            offset.x=0;
            offset.y=-1;
        }
        else if(entityOrientation == Map.Direction.NORTH_EAST){
            offset.x=1;
            offset.y=-1;
        }
        else if(entityOrientation == Map.Direction.SOUTH_EAST){
            offset.x=1;
            offset.y=0;
        }
        else if(entityOrientation == Map.Direction.SOUTH){
            offset.x=0;
            offset.y=1;
        }
        else if(entityOrientation == Map.Direction.SOUTH_WEST){
            offset.x=-1;
            offset.y=1;
        }
        else if(entityOrientation == Map.Direction.NORTH_WEST){
            offset.x=-1;
            offset.y=0;
        }
        else{
            offset.x=0;
            offset.y=0;
            System.out.println("Really? You put in that much work to break the program?");
        }
        desiredLocation.x = currentLocation.x+offset.x;
        desiredLocation.y = currentLocation.y+offset.y;

        Map map = entity.getMap();
        Tile desiredTile = map.getTileAt(desiredLocation);

        if(desiredTile == null){
            return null;
        }
        if(desiredTile.hasEntity()){
            return desiredTile.getEntity();
        }

        return null;

    }

    public TakeableItem stealItem(Inventory targetInventory) {
        // Function only called if target has at least 1 item in inventory.

        // Get a random item
        TakeableItem stolenItem = targetInventory.getRandomItem();

        // Remove that item from target's inventory.
        targetInventory.removeItem(stolenItem);//removes the item

        // Print shit and return
        System.out.println("Its Mr.Steal yo girl");
        cost = 10;
        return stolenItem;
    }
/*
    public void stealsItem(Entity entity, Entity target){
        ArrayList<Inventory.ItemNode> inventory = target.getInventory().getItemNodeArrayList();
        Inventory targetInventory = target.getInventory();
        if(inventory.isEmpty()){//considers for the case the target does not have an item
            return;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(inventory.size());//I dont think it will throw out of bounds exception

        Inventory.ItemNode stolenItem = inventory.get(randomIndex);

        targetInventory.removesItem(stolenItem.getItem());
        Inventory entityInventory = entity.getInventory();
        entityInventory.addsItem(stolenItem.getItem());
        //inventory.remove(stolenItem);//removes the item
        System.out.println("Its Mr.Steal yo girl");
        //return StolenItem;
    }*/
    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
