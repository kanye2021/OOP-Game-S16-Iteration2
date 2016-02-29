package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class DetectRemoveTrapSkill extends ActiveSkill {

    @Override
    protected SkillDictionary initID() {

        return SkillDictionary.DETECT_REMOVE_TRAP;

    }

    @Override
    public void onActivate(Entity entity) {

        // This needs to take a Item!

    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
