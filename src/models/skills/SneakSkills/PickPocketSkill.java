package models.skills.SneakSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class PickPocketSkill extends ActiveSkill {

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.PICK_POCKET;

    }

    @Override
    public void onActivate(Entity entity) {

        System.out.println("I am pick pocket skill");

    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

}
