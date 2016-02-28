package models.skills.SmasherSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class OneHandedWeaponSkill extends PassiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.ONE_HANDED_WEAPON;

    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
