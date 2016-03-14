/**
 * Created by Bradley
 * on 2/1/16.
 */

package models.area_effects;


import models.entities.Entity;
import models.map.Decal;

import java.awt.*;

public abstract class AreaEffect {

    protected String type; //Can't have an abstract final type in abstract class

    protected Decal decal;

    public abstract void onTouch(Entity entity);

    public abstract String getType();

    public Image getImage() {
        return decal.getImage();
    }

    public final Decal getDecal() {
        return decal;
    }

    public final void setDecal(Decal decal) {
        this.decal = decal;
    }

    public abstract int getValue();

}
