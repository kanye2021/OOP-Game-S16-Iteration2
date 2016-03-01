package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class StaffSkill extends ActiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.STAFF;

    }

    @Override
    public void onActivate(Entity entity) {



    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
