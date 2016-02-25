package models.entities;

import models.entityControllers.EntityController;
import models.Map;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class NPC extends Entity {

    public NPC(Point location, Map map) {

        super(location, map);

    }

    @Override
    protected final EntityController initController() {

        return null; // AIController!

    }

}
