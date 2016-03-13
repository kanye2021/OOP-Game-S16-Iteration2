package controllers.entityControllers.AI.Personality.Interests;

import models.entities.Entity;

import java.awt.*;

/**
 * Created by aseber on 3/13/16.
 */
public abstract class EntityInterest extends Interest {

    protected Entity entityOfInterest;

    public EntityInterest(double interestLevel) {

        super(interestLevel);

    }

    public EntityInterest(double interestLevel, Entity entityOfInterest) {

        super(interestLevel);
        this.entityOfInterest = entityOfInterest;

    }

    public final Type getInterestType() { return Type.ENTITY; }
    public abstract Interest createRuntimeInterest(Entity entityOfInterest, Point pointOfInterest);

}
