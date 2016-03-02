package models.occupation;

import models.skills.SkillList;
import models.skills.SummonerSkills.BaneSkill;
import models.skills.SummonerSkills.BoonSkill;
import models.skills.SummonerSkills.EnchantmentSkill;
import models.skills.SummonerSkills.StaffSkill;
import models.stats.StatModification;
import models.stats.StatModificationList;
import models.stats.Stats;

/**
 * Created by aseber on 2/22/16.
 */
public class Summoner extends Occupation {

    @Override
    public SkillList initSkills() {

        return new SkillList(
            new EnchantmentSkill(),
            new BoonSkill(),
            new BaneSkill(),
            new StaffSkill()
        );

    }

    @Override
    public String getOccupation() {
        return "Summoner";
    }

    @Override
    public StatModificationList initStats() {

        StatModificationList modifications = new StatModificationList(
                new StatModification(Stats.Type.AGILITY, -5),
                new StatModification(Stats.Type.INTELLECT, 10),
                new StatModification(Stats.Type.HARDINESS, 5),
                new StatModification(Stats.Type.RADIUS_OF_VISIBILITY, 4)
        );

        return modifications;

    }

}
