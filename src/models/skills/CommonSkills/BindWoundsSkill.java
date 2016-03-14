package models.skills.CommonSkills;

import models.conditions.ConditionList;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */

public class BindWoundsSkill extends ActiveSkill {
    private final int constant = 5;
    private Stats stats;
    //cost = -5;//This is the mana cost it takes to activate this skill


    public BindWoundsSkill() {

        conditionsToActivate = new ConditionList(
//            new StatCondition(Avatar, 3, Stats.Type.LIVES, Condition.Comparison.EXACTLY);
        );
        level = 1;
        cost = 5;
        cooldownTime = 5 * SECONDS;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }

    @Override
    public String getName() {
        return "Bind Wounds";
    }

    @Override
    public void onActivate(Entity entity) {
        //This is used to heal.
        if (isCooldown()) {
            return;
        }
        if (!payMana(entity, cost)) {
            return;
        }
        doTheCoolDown();
        int healAmt = constant * level;
        Stats stats = entity.getStats();//gets the instance of the stats
        stats.modifyStat(Stats.Type.HEALTH, healAmt);


    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "common-bindWounds.png");
    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        // This initializes the key to activate this skill! It should not be null if we intend to actually
        // use the skill
        return null;

    }

}
