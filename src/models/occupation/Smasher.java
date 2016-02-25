package models.occupation;

import models.entities.Entity;
import models.skills.SkillList;
import models.skills.SmasherSkills.*;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

/**
 * Created by aseber on 2/22/16.
 */
public class Smasher extends Occupation {

    @Override
    public SkillList initSkills() {

        return new SkillList(
            new OneHandedWeaponSkill(),
            new TwoHandedWeaponSkill(),
            new BrawlingSkill()
        );

    }

    @Override
    public StatModificationList initStats() {

        StatModificationList modifications = new StatModificationList(
                new StatModification(Stats.Type.STRENGTH, 10),
                new StatModification(Stats.Type.HARDINESS, 5)
        );

        return modifications;

    }
}
