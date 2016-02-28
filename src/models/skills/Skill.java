package models.skills;

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

    protected abstract SkillDictionary initID();

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
