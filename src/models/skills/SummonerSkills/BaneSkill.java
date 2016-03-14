package models.skills.SummonerSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class BaneSkill extends ActiveSkill {
    private int damage;
    private int range;

    public BaneSkill() {
        damage = 5;
        range = 3;
        cooldownTime = 1 * SECONDS;
        cost = 10;
        level = 1;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.BANE;

    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "summoner-baneSkill.png");
    }


    @Override
    public String getName() {
        return "Bane";
    }


    @Override
    public void onActivate(Entity entity) {
        if (isCooldown()) {
            return;
        }
        if (!payMana(entity, cost)) {
            return;
        }

        doTheCoolDown();
        System.out.println("Can you take this? Bane!");
        Projectile projectile = new Projectile(damage, range, StatusEffects.StatusEffect.NONE, "summoner-baneSkill.png");

        cooldown = true;
        new LinearAttack(entity, projectile);//This is the attack


    }


    @Override
    public KeyEvent[] initActivatorKeys() {

        // This initializes the key to activate this skill! It should not be null if we intend to actually
        // use the skill
        return null;

    }
}
