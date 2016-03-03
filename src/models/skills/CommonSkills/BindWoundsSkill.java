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
//TODO: Tie in skill level with skill class.  Polish/Test the condition is called correctly
public class BindWoundsSkill extends ActiveSkill {
    //private Stats stats;
    private final int constant = 5;
    private final int cost = -1;//This is the mana cost it takes to activate this skill
    private int skillLv;
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }
    public BindWoundsSkill(){
        System.out.println("BindWoundSkills");
        //I feel like I need to put something in here idk what currently
        //skillLv = getLevel();
    }
    public void test(){
        System.out.println("Why");
    }
    @Override
    public void onActivate(Entity entity) {
    //This is used to heal.
        int mana = entity.getStats().getMana();
        if(mana >= cost) {
            skillLv = getLevel();
            int healAmt = constant * skillLv;
            Stats stats = entity.getStats();//gets the instance of the stats
            //statsCondition.checkCondition();//somehow checks condition.  Need austie to look at
            stats.modifyMana(cost);
            stats.modifyHealth(healAmt);
            System.out.println("Can you take this? FIRST AID!");
        }
    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
