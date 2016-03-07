package models.area_effects;

import models.entities.Entity;
import models.map.Decal;

/**
 * Created by sergiopuleri on 3/6/16.
 */
public class TrapAreaEffect extends AreaEffect {

    private int AMOUNT_OF_DAMAGE;

    public TrapAreaEffect(int damage) {
        this.AMOUNT_OF_DAMAGE = damage;
        init();
    }
    public void init() {
        decal = new Decal(Decal.Types.TRAP);
        // Initially not visible
        decal.setVisible(false);
    }
    @Override
    public void onTouch(Entity entity) {
        // When activiated, becomes visibile
        entity.takeDamage(-AMOUNT_OF_DAMAGE);
        decal.setVisible(true);
    }

    @Override
    public String getType() {
        return "trap";
    }

}

