package controllers.entityControllers.AI.Memory;

import controllers.entityControllers.AI.Personality.Personality;
import models.entities.Entity;
import models.items.Item;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/11/16.
 */
public interface ThoughtInterface extends MotorInterface {

    void addEntityRelationship(Entity entity, double relationship);
    void setEntityRelationship(Entity entity, double relationship);
    void modifyEntityRelationship(Entity entity, double relationship);
    Relationship getEntityRelationship(Entity entity);
    boolean relationshipExists(Entity entity);
    Personality getPersonality();
    HashMap<Entity, Point> getSeenEntities();
    boolean entitiesSeen();
    HashMap<Item, Point> getSeenItems();
    boolean itemsSeen();
    void setDecision(Decision newDecision);

}
