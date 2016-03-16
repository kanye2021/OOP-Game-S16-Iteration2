package AI.Memory;

import AI.Personality.Personality;
import models.entities.Entity;
import models.entities.Entity_Action_Interface;
import models.items.Item;
import utilities.RelationshipList;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/10/16.
 */
public class Memory implements VisualInterface, ThoughtInterface, MotorInterface {

    // The NPC
    Entity_Action_Interface npc;
    //map of entity relationships
    private RelationshipList<Entity> relationships = new RelationshipList<>();
//    private HashMap<Entity, Relationship> relationships = new HashMap<>();
    // The visual (short term) memory
    private VisualInformation visualMemory = new VisualInformation();
    // The decision
    private Decision decision;
    // The entities personality
    private Personality personality;

    public Memory(Entity_Action_Interface npc, Personality personality) {

        this.npc = npc;
        this.personality = personality;

    }

    public void modifyEntityRelationship(Entity entity, double relationship) {

        relationships.modifyRelationship(entity, relationship);

    }

    public RelationshipList.Relationship getEntityRelationship(Entity entity) {

        return relationships.getRelationship(entity);

    }

    public boolean relationshipExists(Entity entity) {

        return relationships.relationshipExists(entity);

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

        visualMemory.forget();

    }

    public void addVisualInput(Object object, Point point) {
        visualMemory.addVisualInput(object, point);
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision newDecision) {

        this.decision = newDecision;

    }

    public Entity_Action_Interface getNPC() {

        return npc;

    }

}
