package models;

import utilities.InputManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity{

//    protected Point location;
//    protected String orientation;
//    protected Stats stats;
//    protected SkillList skills;
//    protected Inventory inventory;
//    protected EquippedItems equippedItems;
//    protected Occupation occupation;
//    protected Map map;
//
//    // Plans for sprite: Entity will have a getImage() method to return the image
//    // to render on the AreaViewport. It will call sprite.getCurrentImage(orientation)
//    // which will return the appropriate image.
//    protected Sprite sprite;
//
//    public Entity(Point location, Occupation occupation, Map map) {
//        this.location = location;
//        this.orientation = "N";
//        this.stats = new Stats();
//        this.skills = new SkillList();
//        this.inventory = new Inventory();
//        this.equippedItems = new EquipedItems();
//        this.occupation = occupation;
//        occupation.initStats(this.stats); // This will setup the stats and skills particular to this occupation.
//        occupation.initSkills(this.skills);
//        this.sprite = new Sprite(occupation);
//        this.map = map;
//    }
//
//    // Location getter/setter
//    public Point getLocation() {
//        return location;
//    }
//
//    public void move(Point location, String direction){
//        this.location = location;
//        updateOrientation(direction);
//    }
//
//    // Update orientation
//    public void updateOrientation(String orientation){
//        this.orientation = orientation;
//    }
//
//    // Wrapper functions for Stats
//    public void applyStatMod(StatModificationList statMods){
//        stats.applyStatMod(statMods);
//    }
//
//    public void removeStatMod(StatModificationList statMods){
//        stats.removeStatMod(statMods);
//    }
//
//    // Wrappers for skills
//    // TODO: Implement skill stuff.
//
//    // Wrapper functions for inventory interaction
//    public Inventory getInventory(){
//        return inventory;
//    }
//
//    public void addItemToInventory(Item item){
//        inventory.addItem(item);
//    }
//
//    public void equipItem(Item item){
//        inventory.removeItem(item);
//        equippedItems.addItem(item);
//
//        applyStatMod(item.getStatModification());
//    }
//
//    public void dropItem(Item item){
//        inventory.removeItem(item);
//        map.addItem(location, item);
//    }
//
//    // Wrapper functions for equpped items interaction
//    // TODO: Might need more.
//    public EquippedItems getEquippedItems(){
//        return equippedItems;
//    }
//
//    public void unequipItem(Item item){
//        equippedItems.removeItem(item);
//        inventory.addItem(item);
//
//        removeStatMod(item.getStatModification());
//    }
//
//    // Wrappers for occupation
//    // TODO: Implement occupation stuff. i.e. potentially
//
//
//    // Used to go to a new map
//    public void setmap(Map map){
//        this.map = map;
//    }
//
//    public Image getImage(){
//        return sprite.getImage();
//    }




}
