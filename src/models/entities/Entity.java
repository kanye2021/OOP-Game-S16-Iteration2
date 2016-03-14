package models.entities;

import models.Equipment;
import models.Inventory;
import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.npc.Mount;
import models.entities.npc.NPC;
import models.factions.FactionAssociation;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.map.Terrain;
import models.occupation.Occupation;
import models.skills.Skill;
import models.skills.SkillList;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import utilities.Animator;
import utilities.TileDetection;
import utilities.Toast;
import views.sprites.DirectionalSprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bradley on 2/18/16.
 */
public abstract class Entity{

    protected Point location;
    protected Point startLocation;
    protected Map.Direction orientation;
    protected Stats stats;
    protected SkillList skills;
    protected StatusEffects.StatusEffect statusEffect;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Occupation occupation;
    protected Map map;
    protected String type;

    protected int level;
    protected int money;

    // All entities have a faction
    protected FactionAssociation faction;

    // All entities should be able to have a pet.
    protected Pet pet;

    //Entity may have mount
    protected Mount mount;

    protected ArrayList<String> passableTerrain;


    // Stuff for movement
    private Timer movementTimer;
    private boolean canMove;

    //Every Entity will an Animator object that does the animation
    public Animator animator;

    // Plans for sprite: Entity will have a getImage() method to return the image
    // to render on the AreaViewport. It will call sprite.getCurrentImage(orientation)
    // which will return the appropriate image.
    protected DirectionalSprite sprite;

    public Entity(Point location, Map map) {
        this.money = 500;
        this.location = new Point(location);
        this.startLocation = new Point(location);
        this.orientation = Map.Direction.SOUTH;
        this.stats = new Stats();
        this.occupation = initOccupation();
        this.skills = occupation.getSkills();
        this.faction = initFaction();
        this.inventory = new Inventory(30, money);
        this.equipment = new Equipment(this);
        passableTerrain = new ArrayList<>();

        this.statusEffect = StatusEffects.StatusEffect.NONE;
        this.sprite = new DirectionalSprite(initSprites());
        this.animator = new Animator(getAnimatorImages());
        animator.setSpeed(200);
        this.map = map;


        initInitialStats().applyStats(stats);
        occupation.getStats().applyStats(stats);

        // Setup the movement timer.
        movementTimer = new Timer();
        canMove = true;
    }

    private int getMovementDelay(){
        int movementTimerDelay = 300 - (stats.getStat(Stats.Type.MOVEMENT) / 5);

        if(movementTimerDelay < 50){
            movementTimerDelay = 50;
        }
        return movementTimerDelay;
    }

    protected abstract FactionAssociation initFaction();

    public FactionAssociation getFaction() {

        return faction;

    }

    public boolean canTraverseTerrain(Terrain terrain){
        return passableTerrain.contains(terrain.getType());
    }

    public boolean canTraverseTerrain(Point point) {

        if (map.isTileValid(point)) {

            return passableTerrain.contains(map.getTileAt(point).getTerrain().getType());

        }

        return false;
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
    public int getLevel(){
        return level;
    }
    public Map getMap(){return map;}
    //Returns specific skill by name
    public Skill getSpecificSkill(Skill.SkillDictionary skill){
        Skill found = null;
        for(int i =0; i < this.getSkills().size(); i++){

            if(skill.equals(this.getSkills().get(i).initID())){
                found = this.getSkills().get(i);
            }
        }
        if(found != null) {
            return found;
        }else{

            return null;
        }
    }

    public void basicAttack(Entity entity){

        int cooldowntime = 0;
        int damage = entity.getStats().getStat(Stats.Type.STRENGTH);
        String occupation = entity.getOccupation();

        if(occupation.contains("Smasher")){
            damage *= 1;
            cooldowntime = 2000;
        }else if(occupation.contains("Summoner")){
            damage *= 2;
            cooldowntime=3000;
        }else if(occupation.contains("Sneak")){
            cooldowntime=1000;
            damage /= 2;
        }else{
            System.err.println("Entity: BasicAttack: How did you get here");
        }


        Projectile projectile = new Projectile(damage,1, StatusEffects.StatusEffect.NONE);
        new LinearAttack(this,projectile);
    }

    public final TileDetection move(Map.Direction direction){

        setOrientation(direction);

        if(canMove){
            TileDetection td = map.moveEntity(Entity.this, direction);
            location = td.getLocation();
            canMove = false;
            movementTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    canMove = true;
                }
            }, getMovementDelay());
            map.updateTile(location);

            // Include this so that the entity will be removed from the map.
//            if(isDead()){
//                map.removeEntityAt(location);
//            }
            return td;
        }

        map.updateTile(location);
        return null;
    }

    public final TileDetection teleport(Point point) {
        TileDetection td =  map.moveEntity(Entity.this, point);
        location = td.getLocation();
        return td;
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
    public StatusEffects.StatusEffect getStatusEffect(){
        return statusEffect;
    }
//Dont think I need setter?
    public void setStatusEffect(StatusEffects.StatusEffect newStatusEffect){
        this.statusEffect = newStatusEffect;
    }
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

        item.onUse(this);

    }

    public final void dropItem(TakeableItem item){
        inventory.dropItem(item, map, location);
    }

    // Wrapper functions for equpped items interaction
    // TODO: Might need more.
    public final Equipment getEquipment(){
        return equipment;
    }

    public final void unequipItem(EquippableItem item){
        // Unequip (Which will remove stat mods)
        equipment.unEquipItem(item, inventory);
    }

    // Wrappers for occupation
    // TODO: Implement occupation stuff. i.e. potentially


    public String getType() {

        return "Entity";

    }
    public boolean getCanMove(){
        return canMove;
    }
    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    // Used to go to a new map
    public final void setMap(Map map){
        this.map = map;
        updateBrain();
    }
    //Function needs to be overrided by NPC
    //TODO: Problem is caused because if you switch maps, the brain still thinks its on the old map
    public void updateBrain(){}
    protected abstract StatModificationList initInitialStats();
    protected abstract Occupation initOccupation();
    protected abstract HashMap<Map.Direction, String> initSprites();
    protected abstract ArrayList<Image> getAnimatorImages();

    public abstract void startInteraction(NPC npc);

    public final Image getImage(){
//        sprite.setDirection(orientation);
//        return sprite.getImage();
        return animator.update(System.currentTimeMillis());
    }

    public int getLives(){
        return stats.getLives();
    }

    public boolean isDead(){
        return stats.getLives() < 1;
    }


    // TODO: Pet methods may not belong here? just getting stuff 2 work.
    // They could belong here tho.
    public final Pet getPet() {
        return this.pet;
    }
    public final void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setOrientation(Map.Direction orientation){
        this.orientation = orientation;
        if (mount != null){
            mount.setOrientation(orientation);
        }

    }

    // Wrapper to levelup an entity
    public void levelUp() {
        // Upon level-up, notifies skillviewport to allow for level-ing up a skill
        level++;
        this.stats.levelUp();
    }

    public void levelUpToast(){
        level++;
        this.stats.levelUp();
        Toast.createToastWithTimer("You've leveled up! Click a skill to increase", 1500);
    }

    // Wrapper to die (lose a life)
    public void loseALife() {
        this.stats.loseALife();

        handleDeath();
    }

    private void handleDeath(){

        // Drop all items
        equipment.unEquipAll(inventory);
        inventory.dropAll(map, location);

        // Check to see if this was the last life.
        if(isDead()){
            System.out.println("AN ENTITY HAS LOST ALL ITS LIVES");
            map.removeEntityAt(location);
        }else{
            System.out.println("GOTO START");
            teleport(startLocation);
        }
    }

    // Wrapper to heal life
    public void heal(int amount) {
        this.stats.modifyStat(Stats.Type.HEALTH, amount);
    }

    // Wrapper to take damage
    public void takeDamage(int amount) {

        int livesBefore = getLives();
        this.stats.modifyStat(Stats.Type.HEALTH, amount);
        if(getLives()!=livesBefore){
            handleDeath();
        }
    }

    //Weird hacky thing (All entities do not have amount unless otherwise specificed. Avatar will
    //override and return based on
    public Mount getMount(){
        return mount;
    }

    public void setMount(Mount mount){
        this.mount = mount;
        for(String s : mount.getTerrain()){
            passableTerrain.add(s);
        }
        StatModification moveSpeed = new StatModification(Stats.Type.MOVEMENT, 10);
        StatModificationList list = new StatModificationList(moveSpeed);
        applyStatMod(list);
        Point p = mount.getLocation();
        map.removeEntityAt(p);
    }
    public void removeMount(){
        if (mount != null) {
            mount.location = getOrientation().neighbor(getLocation());
            //Problem is that you are removing while the entity is already on the tile
            //map.moveEntity(mount, getOrientation()); //Tmp solution is to drop off mount in direction you are facing
            for(String s : mount.getTerrain()){
                passableTerrain.remove(s);
            }
            map.insertEntity(mount);
            this.mount = null;

        }
    }


}
