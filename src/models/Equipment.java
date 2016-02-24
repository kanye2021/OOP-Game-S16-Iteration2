package models;

import models.items.takeable.TakeableItem;

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

    //Represents equipped items
    private TakeableItem helmet;
    private TakeableItem chestplate;
    private TakeableItem greave;
    private TakeableItem boots;
    private TakeableItem gloves;
    private TakeableItem shield;
    private TakeableItem oneHandedWeapon;
    private TakeableItem twoHandedWeapon;

    //Getter methods
    public TakeableItem getHelmet() {
        return helmet;
    }

    public TakeableItem getChestplate() {
        return chestplate;
    }

    public TakeableItem getGreave() {
        return greave;
    }

    public TakeableItem getBoots() {
        return boots;
    }
    public TakeableItem getGloves() {
        return gloves;
    }

    public TakeableItem getShield() {
        return shield;
    }

    public TakeableItem getOneHandedWeapon() {
        return oneHandedWeapon;
    }

    public TakeableItem getTwoHandedWeapon() {
        return twoHandedWeapon;
    }

    //Setter Methods
    public void setHelmet(TakeableItem helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(TakeableItem chestplate) {
        this.chestplate = chestplate;
    }

    public void setGreave(TakeableItem greave) {
        this.greave = greave;
    }

    public void setBoots(TakeableItem boots) {
        this.boots = boots;
    }

    public void setGloves(TakeableItem gloves) {
        this.gloves = gloves;
    }

    public void setShield(TakeableItem shield) {
        this.shield = shield;
    }

    public void setOneHandedWeapon(TakeableItem oneHandedWeapon) {
        this.oneHandedWeapon = oneHandedWeapon;
    }

    public void setTwoHandedWeapon(TakeableItem twoHandedWeapon) {
        this.twoHandedWeapon = twoHandedWeapon;
    }


}
