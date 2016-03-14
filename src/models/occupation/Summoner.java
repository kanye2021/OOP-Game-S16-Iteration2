package models.occupation;

import models.skills.SkillList;
import models.skills.SummonerSkills.*;
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
                new StaffSkill(),
                new FireBallSkill(),
                new GroundDasherSkill(),
                new IndignationSkill()
        );

    }

    @Override
    public String getOccupation() {
        return "Summoner";
    }

    @Override
    public StatModificationList initStats() {

        StatModificationList modifications = new StatModificationList(
                new StatModification(Stats.Type.LIVES, 3),
                new StatModification(Stats.Type.STRENGTH, 5),
                new StatModification(Stats.Type.AGILITY, 10),
                new StatModification(Stats.Type.INTELLECT, 100),
                new StatModification(Stats.Type.MOVEMENT, 5),
                new StatModification(Stats.Type.HARDINESS, 500),
                new StatModification(Stats.Type.RADIUS_OF_VISIBILITY, 8),
                new StatModification(Stats.Type.HEALTH, Integer.MAX_VALUE),
                new StatModification(Stats.Type.MANA, Integer.MAX_VALUE)
        );

        return modifications;

    }

}
