package models.occupation;

import models.entities.Entity;
import models.skills.SkillList;
import models.stats.StatModificationList;

/**
 * Created by aseber on 2/22/16.
 */
public interface Occupation {

    StatModificationList initStats(Entity entiy);
    SkillList initSkills(Entity entity);

}
