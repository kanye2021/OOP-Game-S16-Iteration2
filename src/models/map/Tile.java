package models.map;

import javafx.util.Pair;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;

/**
 * Created by Bradley on 2/25/2016.
 */

// TODO: Finish this implementation to include AreaEffects
public class Tile {
    private Terrain terrain;
//    private AreaEffect areaEffect;
    private Decal decal;
    private Item item;
    private Entity entity;

//    public Tile(Terrain terrain, AreaEffect areaEffect, Decal decal, Item item, Entity entity) {
//        this.terrain = terrain;
//        this.areaEffect = areaEffect;
//        this.decal = decal;
//        this.item = item;
//        this.entity = entity;
//    }

    public Tile(Terrain terrain, Decal decal, Item item, Entity entity) {
        this.terrain = terrain;
        this.decal = decal;
        this.item = item;
        this.entity = entity;
    }

    // For now if there is already an entity on the tile. adding an entity will replace that
    // entity with this one. This may or may not be the desired affect so care should be taken
    // in the logic that consumes this function (and it has been).
    public boolean insertEntity(Entity entity) {

        // Check to see if this entity can pass here.

        // Check if the entity can pass through this terrain.
        if(!entity.canTraverseTerrain(this.terrain)){
            return false;
        }

        // Check if there is another entity on this tile.
        if(this.entity != null){
            // TODO: Implment entity/ entity interaction.
            System.out.println("In tile");
            //entity.notifyObservers();
            //the NPC will contain all of the interactions
            this.entity.startInteraction();
            return false;
        }

        // Check to see if there is an obstacle.
        if(this.item!= null && this.item.getType().equals("obstacle")){
            return false;
        }

        // Activate areaEffects on the tile
//        if(this.areaEffect != null){
//            this.areaEffect.onTouch(entity);
//        }

        // Active item on the tile
        if(this.item != null){
            this.item.onTouch(entity);
        }

        // Add the entity to this location
        this.entity = entity;

        // Indicate that the move was successfull.
        return true;
    }

    public Terrain getTerrain() {
        return terrain;
    }

//    public AreaEffect getAreaEffect() {
//        return areaEffect;
//    }

    public Decal getDecal() {

        return decal;
    }

    public Item getItem() {

        return item;
    }

    public Entity getEntity() {
        return entity;
    }

    public void removeItem() {
        item = null;
    }

//    public void removeAreaEffect() {
//        areaEffect = null;
//    }

    public void removeDecal() {
        decal = null;
    }

    public void removeEntity() {
        entity = null;
    }

    // For now putting an item on this tile simply replaces one that was already there.
    // Perhaps in a later iteration multiple items could be on a single tile?
    public void addItem(Item item) {
        this.item = item;
    }

    //Checks if the entity is actually a NPC
    public boolean hasNPC(){
        if (entity != null){ //assume all entities are npcs now
            return true;
        }else {
            return false;
        }
    }
}
