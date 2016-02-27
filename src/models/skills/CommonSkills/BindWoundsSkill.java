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

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }

    @Override
    public void onActivate(Entity entity) {
    //This is used to heal.  Level,
//Look at skill class may need skill lv there
        //ask austie

        //check if I have the skill level

        //how does it modify the stat?
        //int level = getLevel();//from the abstract class obtained level
        //entity.


    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
