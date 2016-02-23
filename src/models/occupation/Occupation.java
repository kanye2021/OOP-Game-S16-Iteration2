package models.occupation;

import models.entities.Entity;
import models.skills.SkillList;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/22/16.
 */
public abstract class Occupation {

    public abstract StatModificationList initStats(Entity entiy);
    public abstract SkillList initSkills(Entity entity);

}
