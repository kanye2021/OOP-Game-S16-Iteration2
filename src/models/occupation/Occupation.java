package models.occupation;

import models.skills.*;
import models.skills.CommonSkills.BargainSkill;
import models.skills.CommonSkills.BindWoundsSkill;
import models.skills.CommonSkills.ObservationSkill;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Occupation {
    public Occupation(){

    }

    private static final SkillList defaultSkills = new SkillList(
        new BargainSkill(),
        new BindWoundsSkill(),
        new ObservationSkill()
    );

    public abstract String getOccupation();
    protected abstract StatModificationList initStats();
    protected abstract SkillList initSkills();

    public final StatModificationList getStats() {

        return initStats();

    }

    public final SkillList getSkills() {

        SkillList skills = new SkillList();

        skills.clear();
        skills.addAll(defaultSkills);
        skills.addAll(initSkills());

        return skills;

    }


}
