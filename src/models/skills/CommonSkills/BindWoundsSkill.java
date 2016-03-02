package models.skills.CommonSkills;

import models.conditions.ConditionList;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
//TODO: Tie in skill level with skill class.  Polish/Test the condition is called correctly
public class BindWoundsSkill extends ActiveSkill {
    private Stats stats;
    private final int constant = 5;
    private final int cost = -5;//This is the mana cost it takes to activate this skill

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }

    public BindWoundsSkill() {

        conditionsToActivate = new ConditionList(
//            new StatCondition(Avatar, 3, Stats.Type.LIVES, Condition.Comparison.EXACTLY);
        );

    }

    @Override
    public void onActivate(Entity entity) {
    //This is used to heal.

        if (conditionsToActivate.checkCondition()) {

            if (stats.getStat(Stats.Type.MANA) >= cost) {

                int healAmt = constant * getLevel();
                Stats stats = entity.getStats();//gets the instance of the stats
                stats.modifyStat(Stats.Type.MANA, cost);
                stats.modifyStat(Stats.Type.HEALTH, healAmt);

            }

        }

    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        // This initializes the key to activate this skill! It should not be null if we intend to actually
        // use the skill
        return null;

    }

}
