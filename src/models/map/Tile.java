package models.map;

//import javafx.util.Pair;
import models.area_effects.AreaEffect;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import utilities.TileDetection;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Bradley on 2/25/2016.
 */

// TODO: Finish this implementation to include AreaEffects
public class Tile {
    private Terrain terrain;
    private AreaEffect areaEffect;
    private Decal decal;
    private ArrayList<Item> items;
    private Entity entity;
    private Trap trap;
    private TileImage tileImage;

    public Tile(Terrain terrain, Decal decal, ArrayList<Item> items, Entity entity, AreaEffect areaEffect) {
        this.terrain = terrain;
        this.decal = decal;
        this.items = (ArrayList<Item>)items.clone();
        this.entity = entity;
        this.areaEffect = areaEffect;
        this.tileImage = new TileImage(75, 75, BufferedImage.TYPE_INT_RGB); // Size is arbitrary as it will be scaled later anyway.
        tileImage.generate(this); // Generate the image for the tile.
    }

    // Creates a new tile with the same instance vars as the old tile
    public Tile(Tile tile){
        this.terrain = new Terrain(tile.getTerrain());
        this.decal = (tile.getDecal()!=null) ?  new Decal(tile.getDecal()) : null;
        this.items = (ArrayList<Item>) tile.getItems().clone();
//        Item item = (tile.getItem()!=null) ? Item.ItemDictionary.itemFromID(tile.getItem().getItemId()) : null;
        this.entity = tile.getEntity(); // This will store the same reference.... this is bad.
        this.areaEffect = tile.getAreaEffect(); // THis will also store the same reference.
        this.tileImage = TileImage.copyImage(tile.getTileImage()); // The image will stay the same tho...at least.
    }

    // For now if there is already an entity on the tile. adding an entity will replace that
    // entity with this one. This may or may not be the desired affect so care should be taken
    // in the logic that consumes this function (and it has been).
    public TileDetection insertEntity(Entity entity) {
        TileDetection result = new TileDetection(null, null, false, false);
        int entityLivesBeforeInteraciton = entity.getLives();

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

        // Loop through all the items on the tile and active them.
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();){
            Item item = iterator.next();

            if(item.getType().equals("obstacle")){
                return result;
            }else if(item.getType().equals("interactive")){
                if(!item.onTouch(entity)){
                    return result;
                }else{
                    iterator.remove();
                }
            }else{
                if(item.onTouch(entity)){
                    iterator.remove();
                }
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

        // Indicate that the move was successful only if it didnt result in the entity dieing.
        if(entity.getLives() == entityLivesBeforeInteraciton){
            this.entity = entity;
            result.setMoved(true);
        }

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

    public ArrayList<Item> getItems() {return items;}
    public Image getItemImage() {
        if (items.size() == 0) {
            return null;
        } else if (items.size() == 1) {
            return items.get(0).getImage();
        } else {
            return Item.getBagImage();
        }
    }

    public Entity getEntity() {
        return entity;
    }
    public Image getEntityImage() {return (entity!=null) ? entity.getImage() : null;}

    public Trap getTrap(){return trap;}

    public void removeItems() {

        items.clear();
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

        this.items.add(item);
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

    //Methods for loadSave (just getters) Shouldn't be violating princples as its not modifying source code
    public String getTerrainType(){
        if(getTerrain() != null){
            return getTerrain().getType();
        }else{
            return "No terrrain? not possible";
        }
    }
}
