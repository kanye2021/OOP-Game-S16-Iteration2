package models.map;

//import javafx.util.Pair;
import models.area_effects.AreaEffect;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import models.skills.SneakSkills.TileDetection;

import java.awt.*;

/**
 * Created by Bradley on 2/25/2016.
 */

// TODO: Finish this implementation to include AreaEffects
public class Tile {
    private Terrain terrain;
    private AreaEffect areaEffect;
    private Decal decal;
    private Item item;
    private Entity entity;
    private boolean seen;

    public Tile(Terrain terrain, Decal decal, Item item, Entity entity, AreaEffect areaEffect) {
        this.terrain = terrain;
        this.decal = decal;
        this.item = item;
        this.entity = entity;
        this.areaEffect = areaEffect;
    }

    // Creates a new tile with the same instance vars as the old tile
    public Tile(Tile tile){
        this.terrain = tile.getTerrain();
        this.decal = tile.getDecal();
        this.item = tile.getItem();
        this.entity = tile.getEntity();
    }

    // For now if there is already an entity on the tile. adding an entity will replace that
    // entity with this one. This may or may not be the desired affect so care should be taken
    // in the logic that consumes this function (and it has been).
    public TileDetection insertEntity(Entity entity) {
        TileDetection result = new TileDetection(null, null, false, false);
        // Check to see if this entity can pass here.

        // Check if the entity can pass through this terrain.
        if(!entity.canTraverseTerrain(this.terrain)){
            return result;
        }

        // Check if there is another entity on this tile.
        if(this.entity != null){
            // TODO: Implment entity/ entity interaction.
            System.out.println("In tile");
            //entity.notifyObservers();
            //the NPC will contain all of the interactions
//            this.entity.startInteraction();
            return result;
        }

        // Check to see if there is an obstacle.
        if(this.item!= null && this.item.getType().equals("obstacle")){
            return result;

        }


        // Active item on the tile
        if(this.item != null){
            boolean pickedUp = this.item.onTouch(entity);
            // Remove it from this tile if it was sucessfully picked up
            if (pickedUp) {
                this.item = null;
            }
        }

        // Activate AoE
        if (this.areaEffect != null) {
            Point oldLoc = entity.getLocation();
            this.areaEffect.onTouch(entity);
            Point newLoc = entity.getLocation();
            // If entity changed locations (eg teleport)
            // return out of func here. Dont want to add entity to this location
            if (newLoc != oldLoc) {
                result.setTeleported(true);
                return result;
            }
        }

        // Add the entity to this location
        this.entity = entity;

        // Indicate that the move was successfull.
        result.setMoved(true);
        return result;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

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

    public void removeAreaEffect() {
        areaEffect = null;
    }

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

    public void setSeen(){ seen = true; }
    public boolean getSeen(){ return seen; }

    //Checks if the entity is actually a NPC
    public boolean hasNPC(){
        return entity != null;
    }
}
