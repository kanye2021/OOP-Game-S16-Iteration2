package models;

import models.conditions.MapCondition;
import models.entities.Entity;
import models.entities.Entity;
import models.map.Map;
import models.skills.Skill;
import models.skills.SkillList;
import models.stats.Stats;

import java.awt.*;

/**
 * Created by ben on 3/10/16.
 */
public abstract class Attackion {
    protected Entity entity;
    protected Map map;
    protected int damage;//Total damage not including target defense
    protected int range;
    protected Map.Direction orientation;
    public abstract void calculateDamage();
    protected Point origin;

}