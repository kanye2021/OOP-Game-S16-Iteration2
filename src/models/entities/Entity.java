package models.entities;

import models.Equipment;
import models.Inventory;
import models.Map;
import models.NavigationMediator;
import models.occupation.Occupation;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.skills.SkillList;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DirectionalSprite;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity{

    protected Point location;
    protected NavigationMediator.Direction orientation;
    protected Stats stats;
    protected SkillList skills;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Occupation occupation;
    protected Map map;

    // Plans for sprite: Entity will have a getImage() method to return the image
    // to render on the AreaViewport. It will call sprite.getCurrentImage(orientation)
    // which will return the appropriate image.
    protected DirectionalSprite sprite;

    public Entity(Point location, Occupation occupation, Map map) {
        this.location = location;
        this.orientation = NavigationMediator.Direction.NORTH;
        this.stats = new Stats();
        this.skills = new SkillList();
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.occupation = occupation;
        //occupation.initStats(this.stats); // This will setup the stats and skills particular to this occupation.
        //occupation.initSkills(this.skills);

        this.sprite = new DirectionalSprite();
        this.sprite.addImage(NavigationMediator.Direction.NORTH, "");
        this.sprite.addImage(NavigationMediator.Direction.NORTH_EAST, "");
        this.sprite.addImage(NavigationMediator.Direction.SOUTH_EAST, "");
        this.sprite.addImage(NavigationMediator.Direction.SOUTH, "");
        this.sprite.addImage(NavigationMediator.Direction.SOUTH_WEST, "");
        this.sprite.addImage(NavigationMediator.Direction.NORTH_WEST, "");

        this.map = map;
    }

    // Location getter/setter
    public Point getLocation() {
        return location;
    }

    public void move(Point location, NavigationMediator.Direction direction){
        this.location = location;
        updateOrientation(direction);
    }

    // Update orientation
    public void updateOrientation(NavigationMediator.Direction orientation){
        this.orientation = orientation;
    }

    // Wrapper functions for Stats
    public void applyStatMod(StatModificationList statMods){

        if (statMods != null) {

            stats.applyStatMod(statMods);

        }

    }

    public void removeStatMod(StatModificationList statMods){

        if (statMods != null) {

            stats.removeStatMod(statMods);

        }

    }

    // Wrappers for skills
    // TODO: Implement skill stuff.

    // Wrapper functions for inventory interaction
    public Inventory getInventory(){
        return inventory;
    }

    public void addItemToInventory(TakeableItem item){
        //inventory.addItem(item);
    }

    public void equipItem(EquippableItem item){
        //inventory.removeItem(item);
        //equippedItems.addItem(item);

        //applyStatMod(item.getStatModification());
    }

    public void dropItem(TakeableItem item){
        //inventory.removeItem(item);
        //map.addItem(location, item);
    }

    // Wrapper functions for equpped items interaction
    // TODO: Might need more.
    public Equipment getEquipment(){
        return equipment;
    }

    public void unequipItem(EquippableItem item){
        //equipment.removeItem(item);
        //inventory.addItem(item);

        // this should not happen here. It should happen inside item?
        //removeStatMod(item.getStatModification());
    }

    // Wrappers for occupation
    // TODO: Implement occupation stuff. i.e. potentially


    // Used to go to a new map
    public void setmap(Map map){
        this.map = map;
    }

    public ImageIcon getImage(){

        return sprite.getImage(orientation);

    }

}
