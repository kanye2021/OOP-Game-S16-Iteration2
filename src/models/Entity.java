package models;

import utilities.InputManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity implements Occupation{

    protected Point location;
    protected String orientation;
    protected Stats stats;
    protected Inventory inventory;
    protected EquippedItems equippedItems;

    // Plans for sprite: Entity will have a getImage() method to return the image
    // to render on the AreaViewport. It will call sprite.getCurrentImage(orientation)
    // which will return the appropriate image.
    protected Sprite sprite;

    public Entity(Point location) {
        this.location = location;
        this.orientation = "N";
        this.inventory = new Inventory();
        this.equippedItems = new EquipedItems();
        this.stats = new Stats();
    }

    public Point getLocation() {
        return location;
    }

    public Image getImage(){
        return sprite.getImage();
    }

    public void move(Point location, String direction){
        this.location = location;
        updateOrientation(direction);
    }

    public void updateOrientation(String orientation){
        this.orientation = orientation;
    }

    public Inventory getInventory(){
        return inventory;
    }

    // Used to add items to inventory on touch.
    public void addItemToInventory(Item item){
        inventory.addItem(item);
    }

    public EquippedItems getEquippedItems(){
        return equippedItems;
    }


    // Potentially create more wrappers for inventory and equipped items stuff.
//    public Item removeItemFromInventory(Item item){
//        return inventory.dropItem(item);
//    }

}
