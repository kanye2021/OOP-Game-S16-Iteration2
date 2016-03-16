package AI.Memory;

import AI.Personality.Personality;
import models.entities.Entity;
import models.entities.Entity_Action_Interface;
import models.items.Item;
import utilities.RelationshipList;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 3/11/16.
 */
public interface ThoughtInterface extends MotorInterface {

    void modifyEntityRelationship(Entity entity, double relationship);

    RelationshipList.Relationship getEntityRelationship(Entity entity);

    boolean relationshipExists(Entity entity);

    Personality getPersonality();

    HashMap<Entity, Point> getSeenEntities();

    boolean entitiesSeen();

    HashMap<Item, Point> getSeenItems();

    boolean itemsSeen();

    void setDecision(Decision newDecision);

    Entity_Action_Interface getNPC();

}
