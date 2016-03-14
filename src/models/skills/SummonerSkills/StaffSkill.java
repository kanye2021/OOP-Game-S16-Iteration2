package models.skills.SummonerSkills;

import models.attack.AngularAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.Skill;
import views.sprites.Sprite;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class StaffSkill extends ActiveSkill {
    public StaffSkill() {
        cooldown = false;
        cooldownTime = 1 * SECONDS;
    }

    @Override
    public Skill.SkillDictionary initID() {

        return Skill.SkillDictionary.STAFF;

    }

    @Override
    public String getName() {
        return "Staff Mastery";
    }

    @Override
    public void onActivate(Entity entity) {

        if (isCooldown()) {
            return;
        }
        cooldown = true;
        doTheCoolDown();
        int damage = damageSent(entity);
        Projectile projectile = new Projectile(damage, 1, StatusEffects.StatusEffect.NONE);
        new AngularAttack(entity, projectile);

    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "summoner-staffSkill.png");
    }


    @Override
    public KeyEvent[] initActivatorKeys() {
        return null;
    }
}
