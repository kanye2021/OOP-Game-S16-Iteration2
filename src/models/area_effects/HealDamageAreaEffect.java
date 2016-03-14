package models.area_effects;


import models.entities.Entity;
import models.map.Decal;

/**
 * Created by Bradley on 2/4/16.
 */
public class HealDamageAreaEffect extends AreaEffect {
    private int AMOUNT_OF_DAMAGE;

    public HealDamageAreaEffect(int damage) {
        this.AMOUNT_OF_DAMAGE = damage;
        init();
    }

    public void init() {
        decal = new Decal(Decal.Types.RED_CROSS);
    }

    @Override
    public void onTouch(Entity entity) {
        entity.heal(AMOUNT_OF_DAMAGE);
    }

    @Override
    public String getType() {
        return "heal-damage";
    }

    public int getValue() {
        return AMOUNT_OF_DAMAGE;
    }

}
