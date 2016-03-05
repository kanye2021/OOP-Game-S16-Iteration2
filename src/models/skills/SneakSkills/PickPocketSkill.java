package models.skills.SneakSkills;

import models.Inventory;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import models.map.Map;
import models.map.Tile;
import models.skills.ActiveSkill;

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
        cooldown = false;
        cooldownTime = 3*SECONDS;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.PICK_POCKET;

    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("Cooldown is not over yet!");
            return;
        }
        Entity target = findEntity(entity);
        if(target == null){
            System.out.println("Target is not there");
            cooldown=false;
            return;
        }
        Inventory entityInvent = entity.getInventory();
        ArrayList<Inventory.ItemNode> entityInventory = entity.getInventory().getItemNodeArrayList();
        if(stealItem(target)==null){
            System.out.println("Target did not have an item");
            cooldown=false;
            return;
        }
        entityInventory.add(stealItem(target));
        //entityInvent.addItem(stealItem(target).getItem());
        //stealsItem(entity,target);
        cooldown = true;

        System.out.println("I am pick pocket skill");
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        cooldown = false;
                    }
                },
                cooldownTime
        );

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
        if(desiredTile.hasNPC()){//TODO:Changed hasNPC() to hasEntity() since we may need it for an NPC pickpocketing you
            return desiredTile.getEntity();
        }

        return null;

    }

    public Inventory.ItemNode stealItem(Entity target){
        //Inventory targetInventory = target.getInventory();
        ArrayList<Inventory.ItemNode> inventory = target.getInventory().getItemNodeArrayList();
        if(inventory.isEmpty()){//considers for the case the target does not have an item
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(inventory.size());//I dont think it will throw out of bounds exception
        Inventory.ItemNode stolenItem = inventory.get(randomIndex);

        inventory.remove(stolenItem);//removes the item
        System.out.println("Its Mr.Steal yo girl");
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
