package models.occupation;

import models.entities.Entity;
import models.skills.SkillList;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/22/16.
 */
public class Summoner extends Occupation {

    @Override
    public SkillList initSkills() {

        return new SkillList();

    }

    @Override
    public StatModificationList initStats() {

        StatModificationList modifications = new StatModificationList(
                /*new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)*/
        );

        return modifications;

    }

}
