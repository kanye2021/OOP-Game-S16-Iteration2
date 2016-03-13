package controllers.entityControllers.AI.Memory;

import controllers.entityControllers.AI.Personality.Interests.FollowInterest;
import controllers.entityControllers.AI.Personality.Personality;
import models.entities.Entity;
import models.items.Item;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/10/16.
 */
public class Memory implements VisualInterface, ThoughtInterface, MotorInterface {

    //map of entity relationships
    private HashMap<Entity, Relationship> relationships = new HashMap<>();
    private VisualInformation visualMemory = new VisualInformation();
    private Decision decision;
    private Personality personality;

    public Memory(Personality personality) {

        this.personality = personality;

    }

    public void addEntityRelationship(Entity entity, double relationship) {

        relationships.put(entity, new Relationship(relationship));

    }

    public void setEntityRelationship(Entity entity, double relationship) {

        relationships.get(entity).setRelationship(relationship);

    }

    public void modifyEntityRelationship(Entity entity, double relationship) {

        relationships.get(entity).modifyRelationship(relationship);

    }

    public Relationship getEntityRelationship(Entity entity) {

        return relationships.get(entity);

    }

    public boolean relationshipExists(Entity entity) {

        return relationships.containsKey(entity);

    }

    public Personality getPersonality() {

        return personality;

    }

    public HashMap<Entity, Point> getSeenEntities() {

        return visualMemory.getEntities();

    }

    public boolean entitiesSeen() {

        return !visualMemory.getEntities().isEmpty();

    }

    public boolean itemsSeen() {

        return !visualMemory.getItems().isEmpty();

    }

    public HashMap<Item, Point> getSeenItems() {

        return visualMemory.getItems();

    }

    public void process() { // Forget the visuals we saw from the last tick.

//        visualMemory.forget();

    }

    public void addVisualInput(Object object, Point point) {visualMemory.addVisualInput(object, point); }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision newDecision) {

        this.decision = newDecision;

    }

    public void setNewDecision(Decision newDecision){
        this.decision = newDecision;
    }

}
