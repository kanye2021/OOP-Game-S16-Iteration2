package models;

import models.items.takeable.equippable.EquippableItem;
import utilities.EquippableItemTask;

import java.util.EnumMap;

/**
 * Created by aseber on 2/21/16.
 */
public class Equipment {

    // Need to define a set of locations to put equipment,
    // and a set of component types that map to multiple Locations.
    // Get stuff by locations, and when you add an item it defines what location it takes and sets that.
    // This way a two handed weapon takes both right and left arm, but a one handed weapon only takes your
    // right arm.

    // Then set a dynamic mapping to each location so I can easily access it without passing in equipment.
    // Then do the same to stats,

    // Then fix conditions finally!

    // Then make interactive items finally!

    public enum Location {

        HEAD,
        CHEST,
        ARMS,
        LEGS

    }

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

        componentMap.put(Component.BOOTS, new EquippableItemTask() {
            public EquippableItem run() {
                return getHelmet();
            }
        });

        componentMap.put(Component.CHESTPLATE, new EquippableItemTask() {
            public EquippableItem run() {
                return getHelmet();
            }
        });

        componentMap.put(Component.GLOVES, new EquippableItemTask() {
            public EquippableItem run() {
                return getHelmet();
            }
        });

        componentMap.put(Component.GREAVES, new EquippableItemTask() {
            public EquippableItem run() {
                return getHelmet();
            }
        });

        componentMap.put(Component.ONE_HANDED_WEAPON, new EquippableItemTask() {
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
