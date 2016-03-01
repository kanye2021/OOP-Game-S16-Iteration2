package models;

import models.items.takeable.equippable.EquippableItem;
import utilities.EquippableItemTask;

import java.util.EnumMap;

/**
 * Created by aseber on 2/21/16.
 */
public class Equipment {

    public enum Component {

        HELMET,
        CHESTPLATE,
        GREAVES,
        BOOTS,
        GLOVES,
        SHIELD,
        ONE_HANDED_WEAPON,
        TWO_HANDED_WEAPON;

    }

    //Equipped Items
    private EquippableItem helmet;
    private EquippableItem chestplate;
    private EquippableItem greaves;
    private EquippableItem boots;
    private EquippableItem gloves;
    private EquippableItem shield;
    private EquippableItem oneHandedWeapon;
    private EquippableItem twoHandedWeapon;
    private EnumMap<Component, EquippableItemTask> componentMap = new EnumMap<Component, EquippableItemTask>(Component.class);

    // do getters and setters through this enummap stuff. only need to do
    // getEquipment().getComponent(Component component) and it maps dynamically
    // to each internal call.

    public Equipment() {

        componentMap.put(Component.HELMET, new EquippableItemTask() {
            public EquippableItem run() {
                return getHelmet();
            }
        });

    }

    //Setters for equipment
    public void setHelmet(EquippableItem helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(EquippableItem chestplate) {
        this.chestplate = chestplate;
    }

    public void setGreaves(EquippableItem greaves) {
        this.greaves = greaves;
    }

    public void setBoots(EquippableItem boots) {
        this.boots = boots;
    }

    public void setGloves(EquippableItem gloves) {
        this.gloves = gloves;
    }

    public void setShield(EquippableItem shield) {
        this.shield = shield;
    }

    public void setOneHandedWeapon(EquippableItem oneHandedWeapon) {
        this.oneHandedWeapon = oneHandedWeapon;
    }

    public void setTwoHandedWeapon(EquippableItem twoHandedWeapon) {
        this.twoHandedWeapon = twoHandedWeapon;
    }

    //Getters for equipment
    public EquippableItem getHelmet() {
        return helmet;
    }

    public EquippableItem getChestplate() {
        return chestplate;
    }

    public EquippableItem getGreaves() {
        return greaves;
    }

    public EquippableItem getBoots() {
        return boots;
    }

    public EquippableItem getGloves() {
        return gloves;
    }

    public EquippableItem getShield() {
        return shield;
    }

    public EquippableItem getOneHandedWeapon() {
        return oneHandedWeapon;
    }

    public EquippableItem getTwoHandedWeapon() {
        return twoHandedWeapon;
    }
}
