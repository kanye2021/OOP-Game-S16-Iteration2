package models.entities;

import controllers.entityControllers.EntityController;
import models.Equipment;
import models.Inventory;
import models.entities.npc.Mount;
import models.entities.npc.NPC;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.map.Terrain;
import models.occupation.Occupation;
import models.skills.Skill;
import models.skills.SkillList;
import models.skills.SneakSkills.TileDetection;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DirectionalSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity extends Observable implements ActionListener{

    protected Point location;
    protected Map.Direction orientation;
    protected Stats stats;
    protected SkillList skills;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Occupation occupation;
    protected EntityController controller;
    protected Map map;

    // All entities should be able to have a pet.
    protected Pet pet;

    //Entity may have mount
    protected Mount mount;


    protected ArrayList<String> passableTerrain;
    protected boolean canMove;
    private javax.swing.Timer movementTimer;
    private int movementTimerDelay;
    private Map.Direction currentMovement;
//    private TimerTask currentMovement;

    // Plans for sprite: Entity will have a getImage() method to return the image
    // to render on the AreaViewport. It will call sprite.getCurrentImage(orientation)
    // which will return the appropriate image.
    protected DirectionalSprite sprite;
    protected DirectionalSprite cansprite;

    public Entity(Point location, Map map) {
        this.location = location;
        this.orientation = Map.Direction.SOUTH;
        this.stats = new Stats();
        this.occupation = initOccupation();
        this.skills = occupation.getSkills();
        this.inventory = new Inventory(30);
        this.equipment = new Equipment(this);
        this.controller = initController();
        passableTerrain = new ArrayList<>();
        //occupation.initStats(this.stats); // This will setup the stats and skills particular to this occupation.
        //occupation.initSkills(this.skills);

        this.sprite = new DirectionalSprite(initSprites());
        this.map = map;


        initInitialStats().applyStats(stats);
        skills.addAll(occupation.getSkills());
        occupation.getStats().applyStats(stats);

        // Setup the movement timer.
        movementTimer = new Timer(300, this);
        updateMovementTimerDelay();
        currentMovement = null;
    }

    private void updateMovementTimerDelay(){
        movementTimerDelay = 300 - (stats.getStat(Stats.Type.MOVEMENT) / 5);
        if(movementTimerDelay < 50){
            movementTimerDelay = 50;
        }
        movementTimer.setDelay(movementTimerDelay);
    }
    public boolean canTraverseTerrain(Terrain terrain){
        return passableTerrain.contains(terrain.getType());
    }
    // Location getter/setter
    public final Point getLocation() {
        return location;
    }
    public Stats getStats(){return stats;}
    public SkillList getSkills(){return skills;}
    public String getOccupation(){
        return occupation.getOccupation();
    }
    public Map.Direction getOrientation(){return orientation;}
    public Map getMap(){return map;}
    //Returns specific skill by name
    public Skill getSpecificSkill(Skill.SkillDictionary skill){
        Skill found = null;
        System.out.println(this.getSkills());
        for(int i =0; i < this.getSkills().size(); i++){

            if(skill.equals(this.getSkills().get(i).initID())){
                found = this.getSkills().get(i);
            }
        }
        if(found != null) {
            return found;
        }else{
            System.out.println("hahahah couldn't find it bitch");
            return null;
        }
    }

    public final TileDetection move(Map.Direction direction){
        updateMovementTimerDelay();
        orientation = direction;
        currentMovement = direction;

        TileDetection td = map.moveEntity(Entity.this, currentMovement);
        location = td.getLocation();
        // Call action performed so there is no lag when you press a button and start the timer.
        actionPerformed(null);
        movementTimer.start();

        return td;
    }

    public final void stopMoving(){
        movementTimer.stop();
        currentMovement = null;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setChanged();
        notifyObservers();
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

    public int getRadiusOfVisiblility(){
        return stats.getStat(Stats.Type.RADIUS_OF_VISIBILITY);
    }

    // Wrappers for skills
    // TODO: Implement skill stuff.

    // Wrapper functions for inventory interaction
    public final Inventory getInventory(){
        return inventory;
    }

    public final boolean addItemToInventory(TakeableItem item){
        boolean successfullyAdded = inventory.addItem(item);
        if (!successfullyAdded) {
            System.out.println("Cant pick up item. Inventory is Full. Please drop an item");
            //TODO: Make this a toast message^ with a timer.
            return false;
        } else return true;
    }

    public final void equipItem(EquippableItem item){
        inventory.removeItem(item);
        equipment.setEquipmentComponent(item.getComponent(), item);
        applyStatMod(item.getOnEquipModifications());
    }

    public final void dropItem(TakeableItem item){
        inventory.removeItem(item);
        map.insertItemAtPoint(item, location);
    }

    // Wrapper functions for equpped items interaction
    // TODO: Might need more.
    public final Equipment getEquipment(){
        return equipment;
    }

    public final void unequipItem(EquippableItem item){
        // Unequip (Which will remove stat mods)
        equipment.unEquipItem(item);
        // Add to inventory
        inventory.addItem(item);
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
    public abstract void startInteraction(NPC npc);
    public final Image getImage(){

        return sprite.getImage(orientation);
    }
    // TODO: Pet methods may not belong here? just getting stuff 2 work.
    // They could belong here tho.
    public final Pet getPet() {
        return this.pet;
    }
    public final void setPet(Pet pet) {
        this.pet = pet;
    }
    public final void setMount(Mount mount){this.mount = mount;}


}
