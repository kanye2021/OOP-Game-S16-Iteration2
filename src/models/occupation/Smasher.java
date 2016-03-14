package models.occupation;

import models.skills.SkillList;
import models.skills.SmasherSkills.BrawlingSkill;
import models.skills.SmasherSkills.OneHandedWeaponSkill;
import models.skills.SmasherSkills.TwoHandedWeaponSkill;
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
    public String getOccupation() {
        return "Smasher";
    }

    @Override
    public StatModificationList initStats() {

        StatModificationList modifications = new StatModificationList(
                new StatModification(Stats.Type.STRENGTH, 10),
                new StatModification(Stats.Type.INTELLECT, 10),
                new StatModification(Stats.Type.HARDINESS, 1000),
                new StatModification(Stats.Type.LIVES, 3),
                new StatModification(Stats.Type.MOVEMENT, 5),
                new StatModification(Stats.Type.RADIUS_OF_VISIBILITY, 8),
                new StatModification(Stats.Type.HEALTH, Integer.MAX_VALUE),
                new StatModification(Stats.Type.MANA, Integer.MAX_VALUE)
        );

        return modifications;

    }
}
