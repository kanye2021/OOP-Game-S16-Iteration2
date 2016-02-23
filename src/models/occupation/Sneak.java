package models.occupation;

import models.entities.Entity;
import models.skills.SkillList;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/22/16.
 */
public class Sneak extends Occupation {

    @Override
    public SkillList initSkills(Entity entity) {

        return null;

    }

    @Override
    public StatModificationList initStats(Entity entiy) {

        StatModificationList modifications = new StatModificationList(
                /*new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT),
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)*/
        );

        return modifications;

    }

}
