package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class CreepSkill extends ActiveSkill {

    @Override
    protected SkillDictionary initID() {
        return SkillDictionary.CREEP;
    }

    @Override
    public void onActivate(Entity entity) {



    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
