package models.area_effects;


import models.entities.Entity;
import models.map.Decal;

/**
 * Created by Bradley on 2/4/16.
 */
public class TakeDamageAreaEffect extends AreaEffect {
    private int AMOUNT_OF_DAMAGE;

    public TakeDamageAreaEffect(int damage) {
        this.AMOUNT_OF_DAMAGE = damage;
        init();
    }

    public void init() {
        decal = new Decal(Decal.Types.SKULL_CROSSBONES);
    }


    @Override
    public void onTouch(Entity entity) {
        entity.takeDamage(-AMOUNT_OF_DAMAGE);
    }

    @Override
    public String getType() {
        return "take-damage";
    }

    public int getValue() {
        return AMOUNT_OF_DAMAGE;
    }

}
