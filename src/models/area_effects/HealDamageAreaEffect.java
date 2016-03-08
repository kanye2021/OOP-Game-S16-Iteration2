package models.area_effects;


import models.Equipment;
import models.entities.Entity;
import models.items.Item;
import models.map.Decal;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;
import views.sprites.Sprite;

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

}
