package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.Memory;
import controllers.entityControllers.AI.Memory.Relationship;
import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.Item;
import models.items.takeable.TakeableItem;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class AttackInterest extends Interest {

    private Entity entityOfInterest;

    public AttackInterest(double interestLevel) {

        super(interestLevel);

    }

    private AttackInterest(Interest interest, Object objectOfInterest, Point pointOfInterest) {

        super(interest.getInterestLevel());
        addInterestAttachment(objectOfInterest);
        setPointOfInterest(pointOfInterest);

    }

    public Interest createRuntimeInterest(Object objectOfInterest, Point pointOfInterest) {

        return new AttackInterest(this, objectOfInterest, pointOfInterest);

    }

    private void addInterestAttachment(Object object) {

        if (object instanceof Entity) {

            this.entityOfInterest = (Entity) object;

        }

    }

    public void Update() {

        setPointOfInterest(entityOfInterest.getLocation());

    }

    public double getInterestWeight(Object objectofInterest, ThoughtInterface memory) {

        double weight = 0.0;

        if (objectofInterest instanceof TakeableItem) {

//            TakeableItem item = (TakeableItem) objectofInterest;
//            weight = item.getMonetaryValue() * 100 * getInterestLevel();

        }

        return weight;

    }

    public boolean isApplicable(ThoughtInterface memory) {

        // Return whether or not we see the item of interest on the map around us.
        return memory.getSeenEntities().containsKey(entityOfInterest);

    }

    public Type getInterestType() { return Type.ENTITY; }

}
