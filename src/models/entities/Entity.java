package models.entities;

import models.*;
import controllers.entityControllers.EntityController;
import models.map.Map;
import models.map.Terrain;
import models.occupation.Occupation;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.skills.SkillList;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DirectionalSprite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity {

    protected Point location;
    protected Map.Direction orientation;
    protected Stats stats;
    protected SkillList skills;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Occupation occupation;
    protected EntityController controller;
    protected Map map;
    protected ArrayList<Terrain> passableTerrain;

    // Plans for sprite: Entity will have a getImage() method to return the image
    // to render on the AreaViewport. It will call sprite.getCurrentImage(orientation)
    // which will return the appropriate image.
    protected DirectionalSprite sprite;

    public Entity(Point location, Map map) {
        this.location = location;
        this.orientation = Map.Direction.NORTH;
        this.stats = new Stats();
        this.occupation = initOccupation();
        this.skills = occupation.getSkills();
        this.inventory = new Inventory(30);
        this.equipment = new Equipment();
        this.controller = initController();
        passableTerrain = new ArrayList<>();
        //occupation.initStats(this.stats); // This will setup the stats and skills particular to this occupation.
        //occupation.initSkills(this.skills);

        this.sprite = new DirectionalSprite(initSprites());

        this.map = map;

        initInitialStats().applyStats(stats);
        skills.addAll(occupation.getSkills());
        occupation.getStats().applyStats(stats);

    }
    public boolean canTraverseTerrain(Terrain terrain){
        return passableTerrain.contains(terrain);
    }
    // Location getter/setter
    public final Point getLocation() {
        return location;
    }

    public final void move(Map.Direction direction){
        this.location = map.moveEntity(this, direction);
        this.orientation = direction;
    }

    // Wrapper functions for Stats
    public final void applyStatMod(StatModificationList statMods){

        if (statMods != null) {

            stats.applyStatMod(statMods);

        }

    }

    public final void removeStatMod(StatModificationList statMods){

        if (statMods != null) {

            stats.removeStatMod(statMods);

        }

    }

    // Wrappers for skills
    // TODO: Implement skill stuff.

    // Wrapper functions for inventory interaction
    public final Inventory getInventory(){
        return inventory;
    }

    public final void addItemToInventory(TakeableItem item){
        //inventory.addItem(item);
    }

    public final void equipItem(EquippableItem item){
        //inventory.removeItem(item);
        //equippedItems.addItem(item);

        //applyStatMod(item.getStatModification());
    }

    public final void dropItem(TakeableItem item){
        //inventory.removeItem(item);
        //map.addItem(location, item);
    }

    // Wrapper functions for equpped items interaction
    // TODO: Might need more.
    public final Equipment getEquipment(){
        return equipment;
    }

    public final void unequipItem(EquippableItem item){
        //equipment.removeItem(item);
        //inventory.addItem(item);

        // this should not happen here. It should happen inside item?
        //removeStatMod(item.getStatModification());
    }

    // Wrappers for occupation
    // TODO: Implement occupation stuff. i.e. potentially


    // Used to go to a new map
    public final void setmap(Map map){
        this.map = map;
    }

    protected abstract StatModificationList initInitialStats();
    protected abstract Occupation initOccupation();
    protected abstract HashMap<Map.Direction, String> initSprites();
    protected abstract EntityController initController();

    public final ImageIcon getImage(){

        return sprite.getImage(orientation);
    }

}
