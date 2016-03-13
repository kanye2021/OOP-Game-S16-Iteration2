package models.map;

//import javafx.util.Pair;
import models.area_effects.AreaEffect;
import models.attack.Projectile;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import utilities.ProjectileDetection;
import utilities.TileDetection;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private Projectile projectile;
    private Trap trap;
    private TileImage tileImage;

    public Tile(Terrain terrain, Decal decal, Item item, Entity entity, AreaEffect areaEffect,Projectile projectile) {
        this.terrain = terrain;
        this.decal = decal;
        this.item = item;
        this.entity = entity;
        this.areaEffect = areaEffect;
        this.projectile = projectile;
        this.tileImage = new TileImage(75, 75, BufferedImage.TYPE_INT_RGB); // Size is arbitrary as it will be scaled later anyway.
        tileImage.generate(this); // Generate the image for the tile.
    }

    // Creates a new tile with the same instance vars as the old tile
    public Tile(Tile tile){
        this.terrain = new Terrain(tile.getTerrain());
        this.decal = (tile.getDecal()!=null) ?  new Decal(tile.getDecal()) : null;
        this.item = (tile.getItem()!=null) ? Item.ItemDictionary.itemFromID(tile.getItem().getItemId()) : null;
        this.entity = tile.getEntity(); // This will store the same reference.... this is bad.
        this.projectile = tile.getProjectile();
        this.areaEffect = tile.getAreaEffect(); // THis will also store the same reference.
        this.tileImage = TileImage.copyImage(tile.getTileImage()); // The image will stay the same tho...at least.
    }

    public ProjectileDetection insertProjectile(Projectile projectile){
        ProjectileDetection result = new ProjectileDetection(null,null,false);

        if(!projectile.canTraverseTerrain(this.terrain)){
            System.out.println("projectile can't travel here");
            return result;
        }

        //check to see if theres an obstacle
        if(this.item != null){
            if(this.item.getType().equals("obstacle")){
                System.out.println("obstacle");
            }
        }

        return result;
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
            System.out.println("In tile");
            //the NPC will contain all of the interactions
//            this.entity.startInteraction();
            return result;
        }

        // Check to see if there is an obstacle.
        if(this.item!= null){
            if(this.item.getType().equals("obstacle")){
                return result;
            }
            if(this.item.getType().equals("interactive")){
                if(!item.onTouch(entity)){
                    return result;
                }else{
                    this.item = null;
                }
            }

        }

        // The move was not


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
    public Image getTerrainImage(){ return terrain.getImage(); }

    public AreaEffect getAreaEffect() {
        return areaEffect;
    }

    public Decal getDecal() {return decal;}
    public Image getDecalImage(){ return (decal!=null) ? decal.getImage() : null;}

    public Item getItem() {return item;}
    public Image getItemImage() {return (item!=null) ? item.getImage() : null;}

    public Entity getEntity() {
        return entity;
    }

    public Projectile getProjectile(){
        return projectile;
    }
    public Image getEntityImage() {return (entity!=null) ? entity.getImage() : null;}

    public Image getProjectileImage(){
        if(projectile!=null){
            System.out.println("yo");
        }
        return null;
    }

    public Trap getTrap(){return trap;}

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

    public void removeProjectile(){
        projectile = null;
    }

    // For now putting an item on this tile simply replaces one that was already there.
    // Perhaps in a later iteration multiple items could be on a single tile?
    public void addItem(Item item) {
        this.item = item;
    }

    //Checks if the tile has an Entity
    public boolean hasEntity(){
        return entity != null;
    }
    public boolean hasTrap(){
        if(trap!= null){
            return true;
        }
        else{
            return false;
        }
    }

    public TileImage getTileImage(){
        return this.tileImage;
    }

    public void refreshImage(){
        tileImage.generate(this);
    }
}
