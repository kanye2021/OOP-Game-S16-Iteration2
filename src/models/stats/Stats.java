package models.stats;

import utilities.MathUtilities;

import java.util.TimerTask;

/**
 * Created by Bradley on 2/19/16.
 */
public class Stats {

    public enum Type {
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
        ARMOR_MODIFIER
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

    // Call this whenever a primary stat is changed. This holds the derived stats that won't be changed
    // by anything other than primary stats.
    private void updateDerivedStats(){
        maxHealth = hardiness + 10 * level;
        maxMana = intellect + 10 * level;
        offensiveRating = weaponModifier + strength + level;
        defensiveRating = agility + 10 * level;
        armorRating = armorModifier + hardiness;
        expReqLvUp = 100 + 10 * (int) Math.pow(level, 2.0);
    }

    public void applyStatMod(StatModificationList statMod){
        statMod.applyStats(this);
    }

    public void removeStatMod(StatModificationList statMod){
        statMod.removeStats(this);
    }

    public void modifyLives(int delta) {
        this.lives = MathUtilities.putInRange(0, this.lives + delta, Integer.MAX_VALUE);

        if (this.lives == 0) {

            System.out.println("KILL ME!");

        }

    }

    public void modifyStrength(int delta) {
        this.strength = MathUtilities.putInRange(0, this.strength + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public void modifyAgility(int delta) {
        this.agility = MathUtilities.putInRange(0, this.agility + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public void modifyIntellect(int delta) {
        this.intellect = MathUtilities.putInRange(0, this.intellect + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public void modifyHardiness(int delta) {
        this.hardiness = MathUtilities.putInRange(0, this.hardiness + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public void modifyExperience(int delta) {
        this.experience = MathUtilities.putInRange(0, this.experience + delta, Integer.MAX_VALUE);

        if (this.experience >= this.expReqLvUp) {

            this.level++;
            this.experience -= expReqLvUp;

        }

        updateDerivedStats();
    }

    public void modifyMovement(int delta) {
        this.movement = MathUtilities.putInRange(0, this.movement + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public void modifyHealth(int delta) {
        this.health = MathUtilities.putInRange(0, this.health + delta, Integer.MAX_VALUE);

        if (this.health == 0) {

            this.lives--;
            this.health += maxHealth;
            System.out.println("Teleport me to spawn!");

        }

    }

    public void modifyMana(int delta) {
        this.mana = MathUtilities.putInRange(0, this.mana + delta, Integer.MAX_VALUE);
    }

    public void modifyWeaponModifier(int delta) {
        this.weaponModifier = MathUtilities.putInRange(0, this.weaponModifier + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public void modifyArmorModifier(int delta) {
        this.armorModifier = MathUtilities.putInRange(0, this.armorModifier + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    public int getLives() {
        return this.lives;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getIntellect() {
        return this.intellect;
    }

    public int getHardiness() {
        return this.hardiness;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getMovement() {
        return this.movement;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMana() {
        return this.mana;
    }

    public int getWeaponModifier() {
        return this.weaponModifier;
    }

    public int getArmorModifier() {
        return this.armorModifier;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxHealth() { return maxHealth; }

    public int getMaxMana() { return maxMana; }

    public int getOffensiveRating() { return offensiveRating; }

    public int getDefensiveRating() { return defensiveRating; }

    public int getArmorRating() { return armorRating; }

    public int getExpReqLvUp() { return expReqLvUp; }

    public int getLastLvlExpReq() { return lastLvlExpReq; }

    public TimerTask getCurrentTask() { return currentTask; }

    public String getLastTaskType() { return lastTaskType; }

}
