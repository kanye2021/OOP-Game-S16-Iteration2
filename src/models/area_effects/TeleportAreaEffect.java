package models.area_effects;

import models.entities.Entity;
import models.map.Decal;

import java.awt.*;

/**
 * Created by sergiopuleri on 3/6/16.
 */
public class TeleportAreaEffect extends AreaEffect {

    // Can be used to send to new point on current map..
    // or to go to "new map"
    private final Point targetPoint;


    public TeleportAreaEffect(Point point) {
        this.targetPoint = point;
        init();
    }
    public void init() {
        decal = new Decal(Decal.Types.TELEPORT);
    }

    @Override
    public void onTouch(Entity entity) {
        entity.teleport(targetPoint);
    }

    @Override
    public String getType() {
        return "teleport";
    }

    public int getValue(){
        return 0;
    }
    public Point getPoint(){
        return targetPoint;
    }

}
