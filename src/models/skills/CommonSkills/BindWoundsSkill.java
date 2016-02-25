package models.skills.CommonSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class BindWoundsSkill extends ActiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.BIND_WOUNDS;

    }

    @Override
    public void onActivate(Entity entity) {



    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
