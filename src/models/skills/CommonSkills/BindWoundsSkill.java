package models.skills.CommonSkills;

import models.conditions.Condition;
import models.conditions.StatsCondition;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.StatModification;
import models.stats.Stats;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class BindWoundsSkill extends ActiveSkill {
    private StatsCondition statsCondition;
    private Stats stats;
    private final int constant = 5;
    private final int cost = -5;//This is the mana cost it takes to activate this skill
    private int skillLv;
    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }
    public BindWoundsSkill(){
        //I feel like I need to put something in here idk what currently
        skillLv = getLevel();
    }
    @Override
    public void onActivate(Entity entity) {
    //This is used to heal.

        int healAmt = constant * skillLv;
        Stats stats = entity.getStats();//gets the instance of the stats
        statsCondition.checkCondition();//somehow checks condition.  Need austie to look at
        stats.modifyMana(cost);
        stats.modifyHealth(healAmt);
    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
