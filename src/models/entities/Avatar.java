package models.entities;

import models.EntityController;
import models.Map;
import models.NavigationMediator;
import models.occupation.Occupation;
import models.occupation.Smasher;
import models.stats.StatModificationList;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Avatar extends Entity {

    public Avatar(Point location, Map map) {

        super(location, map);

    }

    @Override
    protected final EntityController initController() {

        return null; // Keyboard controller!

    }

}
