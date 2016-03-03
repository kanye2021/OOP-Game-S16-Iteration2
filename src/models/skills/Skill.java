package models.skills;

import models.conditions.ConditionList;
import utilities.MathUtilities;

/**
 * Created by aseber on 2/24/16.
 */
public abstract class Skill {

    public enum SkillDictionary {

        BIND_WOUNDS,  // Skills all Occupations have.
        BARGAIN,
        OBSERVATION,
        ONE_HANDED_WEAPON,  // Skills Smashers have
        TWO_HANDED_WEAPON,
        BRAWLING,
        ENCHANTMENT,  // Skills Summoners have
        BOON,
        BANE,
        STAFF,
        PICK_POCKET,  // Skills Sneaks have
        DETECT_REMOVE_TRAP,
        CREEP,
        RANGED_ATTACK

    }

    private SkillDictionary ID;
    protected ConditionList conditionsToActivate = new ConditionList();
    private int level;

    public Skill() {

        this.level = 1;
        this.ID = initID();

    }

    public void modifyLevel(int delta) {

        MathUtilities.putInRange(1, this.level + delta, Integer.MAX_VALUE);

    }


    public int getLevel() {

        return this.level;

    }


    public abstract SkillDictionary initID();

    //check to see if the skill is active or not
    public abstract boolean isActive();

    @Override
    public boolean equals(Object o) {

        if (o instanceof Skill) {

            Skill otherSkill = (Skill) o;

            if (this.ID == otherSkill.ID) {

                return true;

            }

        }

        return false;

    }

}
