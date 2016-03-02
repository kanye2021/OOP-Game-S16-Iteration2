package models.occupation;

import models.skills.SkillList;
import models.skills.SneakSkills.*;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

/**
 * Created by aseber on 2/22/16.
 */
public class Sneak extends Occupation {

    @Override
    public SkillList initSkills() {

        return new SkillList(
            new PickPocketSkill(),
            new DetectRemoveTrapSkill(),
            new CreepSkill(),
            new RangedAttackSkill()
        );

    }

    @Override
    public String getOccupation() {
        return "Sneak";
    }

    @Override
    public StatModificationList initStats() {

        StatModificationList modifications = new StatModificationList(
                new StatModification(Stats.Type.STRENGTH, 5),
                new StatModification(Stats.Type.AGILITY, 10),
                new StatModification(Stats.Type.HARDINESS, -5),
                new StatModification(Stats.Type.RADIUS_OF_VISIBILITY, 4)
        );

        return modifications;

    }

}
