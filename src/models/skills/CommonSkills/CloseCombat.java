package models.skills.CommonSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.Skill;

import java.awt.event.KeyEvent;

/**
 * Created by ben on 3/9/16.
 */

//I was thinking of using this class to generalize stuff
    //Possibly Instead of Staffskill and Brawling we have these instead
public class CloseCombat extends ActiveSkill{
    @Override
    public SkillDictionary initID() {
        return null;
    }

    @Override
    public void onActivate(Entity entity) {

    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return new KeyEvent[0];
    }
}
