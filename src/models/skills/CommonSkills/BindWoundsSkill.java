package models.skills.CommonSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.StatModification;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class BindWoundsSkill extends ActiveSkill {

    private Stats stats;
    private final int cost = -5;//This is the mana cost it takes to activate this skill
    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }

    @Override
    public void onActivate(Entity entity) {
    //This is used to heal.

        Stats stats = entity.getStats();//gets the instance of the stats

        stats.modifyMana(cost);
        //need an if statement here conditions Austin Saber
        //int healAmount;
       // stats.modifyHealth(healAmount);

    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
