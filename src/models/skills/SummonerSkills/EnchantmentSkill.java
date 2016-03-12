package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by aseber on 2/24/16.
 */
public class EnchantmentSkill extends ActiveSkill {
    public EnchantmentSkill(){
        cooldown = false;
        cooldownTime = 4*SECONDS;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.ENCHANTMENT;

    }

    @Override
    public String getName() {
        return "Enchantment";
    }


    @Override
    public void onActivate(Entity entity) {
        System.out.println("Enchantment skill is used");
    }

    @Override
    public KeyEvent[] initActivatorKeys() {
        return new KeyEvent[0];
    }

    @Override
    public ArrayList<Image> initSprite() {
        return null;
    }
}
