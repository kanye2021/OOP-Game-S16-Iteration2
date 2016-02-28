package models.stats;

import java.util.TimerTask;

/**
 * Created by Bradley on 2/19/16.
 */
public class Stats {

    public enum StatType {
        LIVES,
        STRENGTH,
        AGILITY,
        INTELLECT,
        HARDINESS,
        EXPERIENCE,
        MOVEMENT,
        HEALTH,
        MANA,
        WEAPON_MODIFIER,
        ARMOMR_MODIFIER;
    }


    // Primary stats
    private int lives;
    private int strength;
    private int agility;
    private int intellect;
    private int hardiness;
    private int experience;
    private int movement;

    // Derived stats
    private int maxHealth;
    private int maxMana;
    private int offensiveRating;
    private int defensiveRating;
    private int armorRating;

    // Other useful parameters
    private int level;
    private int health;
    private int mana;
    private int expReqLvUp;
    private int lastLvlExpReq;
    private int weaponModifier;
    private int armorModifier;

    private TimerTask currentTask;
    private String lastTaskType;

    public Stats() {/*

        // Init primary stats.
        lives = 3;
        strength = 10;
        agility = 10;
        intellect = 10;
        hardiness = 10;
        experience = 0;
        movement = 10;

        // Init derived stats.
        weaponModifier = 0;
        armorModifier = 0;

        updateDerivedStats();
        level = 0;
        health = maxHealth;
        mana = maxMana;
        lastLvlExpReq = 0; // Remember to update this on level up.
    */}

    // Call this whenever a primary stat is changed. This holds the derived stats that won't be changed
    // by anything other than primary stats.
    private void updateDerivedStats(){
        maxHealth = hardiness + 10 * level;
        maxMana = intellect + 10 * level;
        offensiveRating = weaponModifier + strength + level;
        defensiveRating = agility + 10 * level;
        armorRating = armorModifier + hardiness;
        expReqLvUp = 100 + 10 * level;
    }

    public void applyStatMod(StatModificationList statMod){
        statMod.applyStats(this);
    }

    public void removeStatMod(StatModificationList statMod){
        statMod.removeStats(this);
    }

    public void modifyLives(int delta) {
        this.lives += delta;
    }
    public void modifyStrength(int delta){

    }




}
