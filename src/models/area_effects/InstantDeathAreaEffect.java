package models.area_effects;


import models.entities.Entity;
import models.map.Decal;

/**
 * Created by Bradley on 2/4/16.
 */
public class InstantDeathAreaEffect extends AreaEffect {


    public InstantDeathAreaEffect() {
        init();
    }
    public void init() {
        decal = new Decal(Decal.Types.SKULL_CROSSBONES);
    }

    @Override
    public void onTouch(Entity entity) {
        entity.die();
    }

    @Override
    public String getType() {
        return "instant-death";
    }

    public int getValue(){
        return 0;
    }
}
