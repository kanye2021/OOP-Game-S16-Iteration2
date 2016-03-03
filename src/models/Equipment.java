package models;

import models.entities.Entity;
import models.items.takeable.equippable.EquippableItem;

import java.util.*;

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
        LEGS,
        FEET,
        HANDS,
        LEFT_ARM,
        RIGHT_ARM,

    }

    public enum Component {

        HELMET(Location.HEAD),                                        // Maps to HEAD
        CHESTPLATE(Location.CHEST),                                   // Maps to CHEST
        GREAVES(Location.LEGS),                                       // Maps to LEGS
        BOOTS(Location.FEET),                                         // Maps to FEET
        GLOVES(Location.HANDS),                                       // Maps to HANDS
        SHIELD(Location.LEFT_ARM),                                    // Maps to LEFT_ARM
        DAGGER(Location.RIGHT_ARM),                                   // Maps to RIGHT_ARM
        RANGED_WEAPON(Location.RIGHT_ARM),                            // Maps to RIGHT_ARM
        ONE_HANDED_WEAPON(Location.RIGHT_ARM),                        // Maps to RIGHT_ARM
        TWO_HANDED_WEAPON(Location.LEFT_ARM, Location.RIGHT_ARM);     // Maps to RIGHT_ARM and LEFT_ARM

        private Set<Location> affectedLocations;

        Component(Location... affectedLocations) {

            this.affectedLocations = new HashSet<>(Arrays.asList(affectedLocations));

        }

        protected boolean ComponentsIntersect(Component otherComponent) {

            Set<Location> intersection = new HashSet<>(this.affectedLocations);
            intersection.retainAll(otherComponent.affectedLocations);
            return !intersection.isEmpty();

        }

    }

    // The entity this Equipment belongs to
    private Entity entity;

    //Equipped Items
    private EquipmentSlot head;
    private EquipmentSlot chest;
    private EquipmentSlot legs;
    private EquipmentSlot feet;
    private EquipmentSlot hands;
    private EquipmentSlot leftArm;
    private EquipmentSlot rightArm;
    private EnumMap<Location, EquippableItemLocationTask> locationMap = new EnumMap<>(Location.class);

    // do getters and setters through this enummap stuff. only need to do
    // getEquipment().getComponent(Component component) and it maps dynamically
    // to each internal call.

    public Equipment(Entity entity) {

        this.entity = entity;

        locationMap.put(Location.HEAD, () -> head);
        locationMap.put(Location.CHEST, () -> chest);
        locationMap.put(Location.LEGS, () -> legs);
        locationMap.put(Location.FEET, () -> feet);
        locationMap.put(Location.HANDS, () -> hands);
        locationMap.put(Location.LEFT_ARM, () -> leftArm);
        locationMap.put(Location.RIGHT_ARM, () -> rightArm);

    }

    public EquippableItem getEquipmentLocation(Location location) {

        return locationMap.get(location).get().getSlotContents();

    }

    public EquippableItem[] getEquipmentLocation(Component component) {

        HashSet<EquippableItem> items = new HashSet<>();

        for (Location location : component.affectedLocations) {

            items.add(getEquipmentLocation(location));

        }

        return (EquippableItem[]) items.toArray();

    }

    public void removeEquipmentFromLocation(Location location) {

        locationMap.get(location).get().removeSlotContents();

    }

    public void equipItem(EquippableItem item) {

        HashSet<EquippableItem> equipmentToRemove = new HashSet<>();
        Set<Location> union = new HashSet<>(item.getComponent().affectedLocations);
        EquippableItem slotContents;

        // First find all affected locations
        for (Location location : item.getComponent().affectedLocations) {

            slotContents = locationMap.get(location).get().getSlotContents();

            if (slotContents != null) {

                equipmentToRemove.add(slotContents);
                union.addAll(slotContents.getComponent().affectedLocations);

            }

        }

        // Remove all equipment from affected locations
        for (Location location : union) {

            removeEquipmentFromLocation(location);

        }

        // Remove all stat modifications those items did
        for (EquippableItem itemToRemove : equipmentToRemove) {

            itemToRemove.onUnequip(entity);

        }

        // Set all locations to the new item
        for (Location location : item.getComponent().affectedLocations) {

            locationMap.get(location).get().setSlotContents(item);

        }

    }

    private interface EquippableItemLocationTask {

        EquipmentSlot get();

    }

    private class EquipmentSlot {

        EquippableItem item;

        public void setSlotContents(EquippableItem item) {

            this.item = item;

        }

        public EquippableItem getSlotContents() {

            return this.item;

        }

        public void removeSlotContents() {

            this.item = null;

        }

    }

}
