package models.stats;

import utilities.MathUtilities;
import utilities.Toast;
import views.Display;

import java.util.EnumMap;
import java.util.TimerTask;

/**
 * Created by Bradley on 2/19/16.
 */
public class Stats {

    public enum Type {
        // Descriptor is just used to show the name of the stat on the view
        LEVEL("Level"),
        LIVES("Lives"),
        STRENGTH("Strength"),
        AGILITY("Agility"),
        INTELLECT("Intellect"),
        HARDINESS("Hardiness"),
        EXPERIENCE("EXP"),
        MOVEMENT("Movement"),
        HEALTH("Health"),
        MAX_HEALTH("Max Health"),
        MANA("Mana"),
        MAX_MANA("Max Mana"),
        WEAPON_MODIFIER("Weapon Damage"),
        ARMOR_MODIFIER("Armor Modifier"),
        RADIUS_OF_VISIBILITY("Radius of Vis."),
        EXP_TO_LEVEL("EXP To Level"),
        LAST_EXP_TO_LEVEL("Last EXP To Level"),
        OFFSENSIVE_RATING("Offense"),
        DEFENSIVE_RATING("Defense"),
        ARMOR_RATING("Armor"),
        SKILL_POINTS("Skill Points"),
        TOTAL_WEIGHT("Weight");

        private String descriptor;

        Type(String descriptor){this.descriptor = descriptor;}

        public String getDescriptor() { return descriptor; }
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
    private int skillPointsAvailabile;
    private int health;
    private int mana;
    private int expReqLvUp;
    private int lastLvlExpReq;
    private int weaponModifier;
    private int armorModifier;
    private int radiusOfVisiblility;
    private int totalWeight;

    private TimerTask currentTask;
    private String lastTaskType;

    private EnumMap<Type, StatsGetTask> statGetMap = new EnumMap<>(Type.class);
    private EnumMap<Type, StatsSetTask> statSetMap = new EnumMap<>(Type.class);

    public Stats() {
        this.level = 0;
        this.skillPointsAvailabile = 0;

        statGetMap.put(Type.LEVEL, () -> getLevel());
        statGetMap.put(Type.SKILL_POINTS, () -> getSkillPointsAvailabile());
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
        statGetMap.put(Type.RADIUS_OF_VISIBILITY, () -> getRadiusOfVisiblility());
        statGetMap.put(Type.EXP_TO_LEVEL, () -> getExpReqLvUp());
        statGetMap.put(Type.LAST_EXP_TO_LEVEL, () -> getLastLvlExpReq());
        statGetMap.put(Type.OFFSENSIVE_RATING, () -> getOffensiveRating());
        statGetMap.put(Type.DEFENSIVE_RATING, () -> getDefensiveRating());
        statGetMap.put(Type.ARMOR_RATING, () -> getArmorRating());
        statGetMap.put(Type.TOTAL_WEIGHT, () -> getTotalWeight());

        statSetMap.put(Type.LIVES, (delta) -> modifyLives(delta));
        statSetMap.put(Type.SKILL_POINTS, (delta) -> modifySkillPoints(delta));
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
        statSetMap.put(Type.RADIUS_OF_VISIBILITY, (delta) -> modifyRadiusOfVisibility(delta));
        statSetMap.put(Type.TOTAL_WEIGHT, (delta) -> modifyTotalWeight(delta));
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

    private void modifySkillPoints(int delta) {
        this.skillPointsAvailabile = MathUtilities.putInRange(0, this.skillPointsAvailabile + delta, Integer.MAX_VALUE);
    }

    // Wrapper for modify level
    public void levelUp() {
        modifyExperience(expReqLvUp);
    }

    // Wrapper to decrement skill points
    public void decrementSkillPoints() {
        modifySkillPoints(-1);
    }

    // Wrapper to animate loosing a life (health bar slides down)
    public void loseALife() {
        modifyHealth(-maxHealth);
    }

    private void modifyExperience(int delta) {
        this.experience = MathUtilities.putInRange(0, this.experience + delta, Integer.MAX_VALUE);

        if (this.experience >= this.expReqLvUp) {

            this.level++;
            this.experience -= expReqLvUp;
            this.skillPointsAvailabile++;

            updateDerivedStats();
            fillHealth();
            fillMana();

        }


    }

    private void fillHealth(){ health = maxHealth; }
    private void fillMana(){ mana = maxMana; }

    private void modifyMovement(int delta) {
        this.movement = MathUtilities.putInRange(0, this.movement + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyHealth(int delta) {
        this.health = MathUtilities.putInRange(0, this.health + delta, this.maxHealth);

        if (this.health == 0) {

            this.lives--;
            this.health += maxHealth;
            System.out.println("Teleport me to spawn!");
            Toast.createToast("OUCH I AM DED");

        }

    }

    private void modifyMana(int delta) {
        this.mana = MathUtilities.putInRange(0, this.mana + delta, this.maxMana);
    }

    private void modifyWeaponModifier(int delta) {
        this.weaponModifier = MathUtilities.putInRange(0, this.weaponModifier + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyArmorModifier(int delta) {
        this.armorModifier = MathUtilities.putInRange(0, this.armorModifier + delta, Integer.MAX_VALUE);
        updateDerivedStats();
    }

    private void modifyRadiusOfVisibility(int delta){
        this.radiusOfVisiblility = MathUtilities.putInRange(0, this.radiusOfVisiblility + delta, Integer.MAX_VALUE);
    }

    private void modifyTotalWeight(int delta){
        this.totalWeight = MathUtilities.putInRange(0, this.totalWeight + delta, Integer.MAX_VALUE);
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
    private int getSkillPointsAvailabile() {return this.skillPointsAvailabile;}

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

    private int getRadiusOfVisiblility() { return radiusOfVisiblility; }

    private int getTotalWeight(){return totalWeight;}

}
