package models.skills;

import models.conditions.ConditionList;
import utilities.MathUtilities;
import views.sprites.Sprite;

import java.util.Timer;
import java.util.TimerTask;

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
        FIREBALL,
        GROUND_DASHER,
        INDIGNATION,
        PICK_POCKET,  // Skills Sneaks have
        DETECT_REMOVE_TRAP,
        CREEP,
        RANGED_ATTACK

    }

    public enum AttackType{
        LINEAR,
        RADIAL,
        AREA
    }

    private SkillDictionary ID;
    protected ConditionList conditionsToActivate = new ConditionList();
    protected int level;
    protected String SKILL_ROOT_FILE_PATH = "./src/res/skills/";

    protected boolean cooldown;
    protected int cooldownTime;
    protected double currentCooldownRemaining;
    protected final int SECONDS = 1000;
    private Sprite sprite;


    public Skill() {
        this.currentCooldownRemaining = 0;
        this.level = 1;
        this.ID = initID();
        this.sprite = initSprite();
    }

    public void modifyLevel(int delta) {

        MathUtilities.putInRange(1, this.level + delta, Integer.MAX_VALUE);

    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getLevel() {
        return this.level;
    }

    public void incrementLevel() {
        level++;
    }

    public boolean isCooldown() {
        return cooldown;
    }

    public final void doTheCoolDown() {
        // Set "cooldown" to true to indicate the skill is cooling down
        cooldown = true;

        // Set a task to execute after "X" seconds have passed by.
        // "X" == cooldownTime of current skill
        // After the time is up "cooldown" is set to false;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        cooldown = false;

                    }
                },
                cooldownTime
        );

        // Start tracking the remaining time available
        countDownAndUpdateRemainingTime();
    }


    /***
     * This function will update the "currentCooldownRemaining" property of the skill
     * it'll update every MS. This is to get an accurate status of how long of the CD is left.
     */
    public void countDownAndUpdateRemainingTime() {
        Timer timer = new Timer();

        currentCooldownRemaining = cooldownTime;
        double startTime = System.currentTimeMillis();

        timer.schedule(new TimerTask() {
            double elapsedTime = 0;
            @Override
            public void run() {
                if (cooldown) {
                    //Get elapsed time in milli seconds
                    elapsedTime = (System.currentTimeMillis() - startTime);

                    // Current time remaining is the elapsed time minus total cooldown time
                    // divide by 1000 to get seconds
                    currentCooldownRemaining = (cooldownTime - elapsedTime) / SECONDS;
                }
                else {
                    timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                    timer.purge();   // Removes all cancelled tasks from this timer's task queue.
                }
            };
        }, 0, 1);
    }

    public int getCooldownTime() {
        return cooldownTime;
    }

    public double getCooldownTimeRemaining() {
        return currentCooldownRemaining;
    }


    public abstract SkillDictionary initID();
    public abstract Sprite initSprite();
    public abstract String getName();

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

    public void setLevel(int value){
        level = value;
    }

}
