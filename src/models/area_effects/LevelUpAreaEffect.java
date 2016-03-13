package models.area_effects;


import models.entities.Entity;
import models.map.Decal;

/**
 * Created by Bradley on 2/4/16.
 */
public class LevelUpAreaEffect extends AreaEffect {

    public LevelUpAreaEffect() {
        init();
    }
    public void init() {
        decal = new Decal(Decal.Types.GOLD_STAR);
    }

    @Override
    public void onTouch(Entity entity) {
        entity.levelUp();
    }

    @Override
    public String getType() {
        return "level-up";
    }

    public int getValue(){
        return 0;
    }
}
