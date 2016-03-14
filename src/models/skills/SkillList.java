package models.skills;

import java.util.ArrayList;

/**
 * Created by aseber on 2/22/16.
 */
public class SkillList extends ArrayList<Skill> {

    public SkillList(Skill... skills) {

        for (Skill skill : skills) {

            this.add(skill);

        }

    }

    public void loadSkills(String name, int value) {
        for (Skill skill : this) {
            if (skill.getName().equals(name)) {
                skill.setLevel(value);
            }
        }
    }

}
