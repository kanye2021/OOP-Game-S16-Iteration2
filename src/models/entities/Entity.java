package models.entities;

import controllers.entityControllers.EntityController;
import models.Equipment;
import models.Inventory;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.map.Terrain;
import models.occupation.Occupation;
import models.skills.SkillList;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.DirectionalSprite;

import java.awt.*;
import java.util.*;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity extends Observable {

    protected Point location;
    protected Map.Direction orientation;
    protected Stats stats;
    protected SkillList skills;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Occupation occupation;
    protected EntityController controller;
    protected Map map;
    protected ArrayList<String> passableTerrain;
    protected boolean canMove;
    private Timer movementTimer;
    private TimerTask currentMovement;

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
        this.equipment = new Equipment();
        this.controller = initController();
        passableTerrain = new ArrayList<>();
        //occupation.initStats(this.stats); // This will setup the stats and skills particular to this occupation.
        //occupation.initSkills(this.skills);

        this.sprite = new DirectionalSprite(initSprites());
        this.map = map;
        movementTimer = new Timer();


        initInitialStats().applyStats(stats);
        skills.addAll(occupation.getSkills());
        occupation.getStats().applyStats(stats);

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
    public final void move(Map.Direction direction){
        if(currentMovement != null){
            System.out.println("CANCELING MOVEMENT");
            currentMovement.cancel();
        }

        // Set a time to determine when moevment will be alowed.
        int delay = 300 - (stats.getMovement() / 5);
        // If the delay is less than 0, the avatar defaults to the fastest movement of 5ms.
        delay = delay > 10 ? delay : 10;

        currentMovement = new TimerTask(){
            @Override
            public void run() {
                location = map.moveEntity(Entity.this, direction);
                orientation = direction;
                setChanged();
                notifyObservers(); //To clairfy the observers are the controllers (Game view controller)
                System.out.println("MOVING: " + direction);
            }
        };

        movementTimer.schedule(currentMovement, 0, delay);

//        movementTimer.scheduleAtFixedRate(currentMovement, 0, 300);
    }

    public final void stopMoving(){
        if(currentMovement!=null){
            currentMovement.cancel();
            setChanged();
            notifyObservers();
        }
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
    public abstract void startInteraction();
    public final Image getImage(){

        return sprite.getImage(orientation);
    }

}
