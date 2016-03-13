package models;

import models.entities.Entity;
import models.items.Item;
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
        // Descriptor is just used to display the location "name" on the view.
        HEAD("Head"),
        NECK("Neck"),
        CHEST("Chest"),
        BACK("Back"),
        LEGS("Legs"),
        FEET("Greaves"),
        HANDS("Gloves"),
        LEFT_ARM("Left"),
        RIGHT_ARM("Right");

        private String descriptor;

        Location(String descriptor){this.descriptor = descriptor;}

        public String getDescriptor() { return descriptor; }
    }

    public enum Component {

        HELMET(Location.HEAD),                                        // Maps to HEAD
        AMULET(Location.NECK),                                        // Maps to Neck for necklace/ammy
        CHESTPLATE(Location.CHEST),                                   // Maps to CHEST
        CAPE(Location.BACK),                                          // Maps to BACK for cape
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
    private EquipmentSlot head = new EquipmentSlot();
    private EquipmentSlot neck = new EquipmentSlot();
    private EquipmentSlot chest = new EquipmentSlot();
    private EquipmentSlot back = new EquipmentSlot();
    private EquipmentSlot legs = new EquipmentSlot();
    private EquipmentSlot feet = new EquipmentSlot();
    private EquipmentSlot hands = new EquipmentSlot();
    private EquipmentSlot leftArm = new EquipmentSlot();
    private EquipmentSlot rightArm = new EquipmentSlot();
    private EnumMap<Location, EquippableItemLocationTask> locationMap = new EnumMap<>(Location.class);

    // do getters and setters through this enummap stuff. only need to do
    // getEquipment().getComponent(Component component) and it maps dynamically
    // to each internal call.

    public Equipment(Entity entity) {

        this.entity = entity;

        locationMap.put(Location.HEAD, () -> head);
        locationMap.put(Location.NECK, () -> neck);
        locationMap.put(Location.CHEST, () -> chest);
        locationMap.put(Location.BACK , () -> back);
        locationMap.put(Location.LEGS, () -> legs);
        locationMap.put(Location.FEET, () -> feet);
        locationMap.put(Location.HANDS, () -> hands);
        locationMap.put(Location.LEFT_ARM, () -> leftArm);
        locationMap.put(Location.RIGHT_ARM, () -> rightArm);

    }

    public EquippableItem getEquipmentLocation(Location location) {

        return locationMap.get(location).get().getSlotContents();

    }
//TODO:Make onehandedweapon work
   /* public boolean isOneHandedWeapon(){
        if(rightArm = get)
    }*/

    // If we call the above function and there is no equipment at the specified location,
    // Will get a null ptr exception. Use this method to verify if there is something first before.
    public boolean isEquipmentAtLocation(Location location) {
        return locationMap.get(location).get().getSlotContents() != null;
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


    public void unEquipAll(Inventory inventory){
        for(Location loc : Location.values()){
            if(isEquipmentAtLocation(loc)){
                unEquipItem(getEquipmentLocation(loc), inventory);
            }
        }
    }

    public void unEquipItem(EquippableItem item, Inventory inventory) {
        if(item!=null){
            removeEquipmentFromAffectedLocations(item.getComponent());
            inventory.addItem(item);
        }
    }

    public void removeEquipmentFromAffectedLocations(Component component) {
        HashSet<EquippableItem> equipmentToRemove = new HashSet<>();
        Set<Location> union = new HashSet<>(component.affectedLocations);
        EquippableItem slotContents;

        // First find all affected locations
        for (Location location : component.affectedLocations) {
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
    }

    public void equipItem(EquippableItem item) {

        // Just pulled the code that used to be here into its own function
        removeEquipmentFromAffectedLocations(item.getComponent());

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
