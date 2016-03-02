package models.stats;

import utilities.MathUtilities;

import java.util.EnumMap;
import java.util.TimerTask;

/**
 * Created by Bradley on 2/19/16.
 */
public class Stats {

    public enum Type {
        LEVEL,
        LIVES,
        STRENGTH,
        AGILITY,
        INTELLECT,
        HARDINESS,
        EXPERIENCE,
        MOVEMENT,
        HEALTH,
        MAX_HEALTH,
        MANA,
        MAX_MANA,
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

    private EnumMap<Type, StatsGetTask> statGetMap = new EnumMap<>(Type.class);
    private EnumMap<Type, StatsSetTask> statSetMap = new EnumMap<>(Type.class);

    public Stats() {

        statGetMap.put(Type.LEVEL, () -> getLevel());
        statGetMap.put(Type.LIVES, () -> getLives());
        statGetMap.put(Type.STRENGTH, () -> getStrength());
        statGetMap.put(Type.AGILITY, () -> getAgility());
        statGetMap.put(Type.INTELLECT, () -> getIntellect());
        statGetMap.put(Type.HARDINESS, () -> getHardiness());
        statGetMap.put(Type.EXPERIENCE, () -> getExperience());
        statGetMap.put(Type.MOVEMENT, () -> getMovement());
        statGetMap.put(Type.HEALTH, () -> getHealth());
        statGetMap.put(Type.MAX_HEALTH, () -> getMaxHealth());
        statGetMap.put(Type.MANA, () -> getMana());
        statGetMap.put(Type.MAX_MANA, () -> getMaxMana());
        statGetMap.put(Type.WEAPON_MODIFIER, () -> getWeaponModifier());
        statGetMap.put(Type.ARMOR_MODIFIER, () -> getArmorModifier());

        statSetMap.put(Type.LIVES, (delta) -> modifyLives(delta));
        statSetMap.put(Type.STRENGTH, (delta) -> modifyStrength(delta));
        statSetMap.put(Type.AGILITY, (delta) -> modifyAgility(delta));
        statSetMap.put(Type.INTELLECT, (delta) -> modifyIntellect(delta));
        statSetMap.put(Type.HARDINESS, (delta) -> modifyHardiness(delta));
        statSetMap.put(Type.EXPERIENCE, (delta) -> modifyExperience(delta));
        statSetMap.put(Type.MOVEMENT, (delta) -> modifyMovement(delta));
        statSetMap.put(Type.HEALTH, (delta) -> modifyHealth(delta));
        statSetMap.put(Type.MANA, (delta) -> modifyMana(delta));
        statSetMap.put(Type.WEAPON_MODIFIER, (delta) -> modifyWeaponModifier(delta));
        statSetMap.put(Type.ARMOR_MODIFIER, (delta) -> modifyArmorModifier(delta));

    }

    public void applyStatMod(StatModificationList statMod){
        statMod.applyStats(this);
    }

    public void removeStatMod(StatModificationList statMod){
        statMod.removeStats(this);
    }

    public int getStat(Type type) {

        return statGetMap.get(type).get();

    }

    public void modifyStat(Type type, int delta) {

        statSetMap.get(type).set(delta);

    }

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

    private void modifyLives(int delta) {
        this.lives = MathUtilities.putInRange(0, this.lives + delta, Integer.MAX_VALUE);

        if (this.lives == 0) {

            System.out.println("KILL ME!");

        }

    }

    private void modifyStrength(int delta) {
        this.strength = MathUtilities.putInRange(0, this.strength + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyAgility(int delta) {
        this.agility = MathUtilities.putInRange(0, this.agility + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyIntellect(int delta) {
        this.intellect = MathUtilities.putInRange(0, this.intellect + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyHardiness(int delta) {
        this.hardiness = MathUtilities.putInRange(0, this.hardiness + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyExperience(int delta) {
        this.experience = MathUtilities.putInRange(0, this.experience + delta, Integer.MAX_VALUE);

        if (this.experience >= this.expReqLvUp) {

            this.level++;
            this.experience -= expReqLvUp;

        }

        updateDerivedStats();
    }

    private void modifyMovement(int delta) {
        this.movement = MathUtilities.putInRange(0, this.movement + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyHealth(int delta) {
        this.health = MathUtilities.putInRange(0, this.health + delta, Integer.MAX_VALUE);

        if (this.health == 0) {

            this.lives--;
            this.health += maxHealth;
            System.out.println("Teleport me to spawn!");

        }

    }

    private void modifyMana(int delta) {
        this.mana = MathUtilities.putInRange(0, this.mana + delta, Integer.MAX_VALUE);
    }

    private void modifyWeaponModifier(int delta) {
        this.weaponModifier = MathUtilities.putInRange(0, this.weaponModifier + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyArmorModifier(int delta) {
        this.armorModifier = MathUtilities.putInRange(0, this.armorModifier + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private int getLives() {
        return this.lives;
    }

    private int getStrength() {
        return this.strength;
    }

    private int getAgility() {
        return this.agility;
    }

    private int getIntellect() {
        return this.intellect;
    }

    private int getHardiness() {
        return this.hardiness;
    }

    private int getExperience() {
        return this.experience;
    }

    private int getMovement() {
        return this.movement;
    }

    private int getHealth() {
        return this.health;
    }

    private int getMana() {
        return this.mana;
    }

    private int getWeaponModifier() {
        return this.weaponModifier;
    }

    private int getArmorModifier() {
        return this.armorModifier;
    }

    private int getLevel() {
        return this.level;
    }

    private int getMaxHealth() { return maxHealth; }

    private int getMaxMana() { return maxMana; }

    private int getOffensiveRating() { return offensiveRating; }

    private int getDefensiveRating() { return defensiveRating; }

    private int getArmorRating() { return armorRating; }

    private int getExpReqLvUp() { return expReqLvUp; }

    private int getLastLvlExpReq() { return lastLvlExpReq; }

    private TimerTask getCurrentTask() { return currentTask; }

    private String getLastTaskType() { return lastTaskType; }

    private interface StatsGetTask {

        int get();

    }

    private interface StatsSetTask {

        void set(int delta);

    }

}
