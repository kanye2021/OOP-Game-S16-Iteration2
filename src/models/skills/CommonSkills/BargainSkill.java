package models.skills.CommonSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class BargainSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BARGAIN;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
