package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class EnchantmentSkill extends ActiveSkill {

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.ENCHANTMENT;

    }

    @Override
    public void onActivate(Entity entity) {
        System.out.println("Enchantment skill is used");
    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return new KeyEvent[0];
    }
}
