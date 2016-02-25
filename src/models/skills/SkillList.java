package models.skills;

import models.stats.StatModification;

import java.util.ArrayList;

/**
 * Created by aseber on 2/22/16.
 */
public class SkillList extends ArrayList<Skill> {

    public SkillList(Skill... skills) {

        for(Skill skill : skills) {

            this.add(skill);

        }

    }

}