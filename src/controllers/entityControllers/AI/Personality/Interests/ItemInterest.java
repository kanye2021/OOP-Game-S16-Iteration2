package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;
import models.entities.Entity;
import models.items.Item;

import java.awt.*;

/**
 * Created by aseber on 3/13/16.
 */
public abstract class ItemInterest extends Interest {

    protected Item itemOfInterest;

    public ItemInterest(double interestLevel) {

        super(interestLevel);

    }

    public ItemInterest(double interestLevel, Item itemOfInterest) {

        super(interestLevel);
        this.itemOfInterest = itemOfInterest;

    }

    public final Type getInterestType() { return Type.ITEM; }
    public abstract Interest createRuntimeInterest(Item itemOfInterest, Point pointOfInterest);
    public abstract double getInterestWeight(Item itemOfInterest, ThoughtInterface memory);

}
