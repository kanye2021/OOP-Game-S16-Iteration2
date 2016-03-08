package controllers.entityControllers.AI.Vision;

import models.entities.Entity;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bradley on 3/5/16.
 */
public class VisualInformation {

    private ArrayList<Entity> entities;
    private ArrayList<Point> itemLocations;

    public VisualInformation(){
        entities = new ArrayList<>();
        itemLocations = new ArrayList<>();
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public void addItem(Point p){
        itemLocations.add(p);
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public ArrayList<Point> getitemLocations(){
        return itemLocations;
    }

    public boolean foundEntities(){
        return !entities.isEmpty();
    }

    public boolean foundItems(){
        return !itemLocations.isEmpty();
    }
}
