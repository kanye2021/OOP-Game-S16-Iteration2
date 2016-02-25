package models.occupation;

import models.entities.Entity;
import models.skills.*;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Occupation {

    private static final SkillList defaultSkills = new SkillList(
        new Bargain(),
        new BindWounds(),
        new Observation()
    );

    protected abstract StatModificationList initStats();
    protected abstract SkillList initSkills();

    public final StatModificationList getStats() {

        return initStats();

    }

    public final SkillList getSkills() {

        SkillList skills = new SkillList();

        for (Skill skill : defaultSkills.getSkills()) {

            skills.add(skill);

        }

        for (Skill skill : initSkills().getSkills()) {

            skills.add(skill);

        }

        return skills;

    }

}
