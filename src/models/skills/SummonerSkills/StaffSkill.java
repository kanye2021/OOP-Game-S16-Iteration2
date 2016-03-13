package models.skills.SummonerSkills;

import models.entities.Entity;
import models.skills.ActiveSkill;
import models.skills.PassiveSkill;
import models.skills.Skill;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
public class StaffSkill extends PassiveSkill {
    public StaffSkill(){
        cooldown = false;
        cooldownTime=MIDTIME;
    }
    @Override
    public Skill.SkillDictionary initID() {

        return Skill.SkillDictionary.STAFF;

    }
    @Override
    public String getName() {
        return "Staff-Mastery";
    }

    @Override
    public void onUpdate(Entity entity) {



    }

}
