package controllers;

import models.Equipment;
import models.Inventory;
import models.entities.Entity;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import utilities.StateManager;
import utilities.Task;
import views.EquipmentView;
import views.InventoryView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 3/4/16.
 */
public class EquipmentViewController extends ViewController {

    private int selectedItemIndex;
    private Task previousItem;
    private Task nextItem;
    // useItem will equip unequip the current selected item
    private Task useItem;
    private Task closeEquipment;


    private Entity entity;
    private Equipment equipment;

    public EquipmentViewController(View view, StateManager stateManager, Entity entity) {
        super(view, stateManager);
        selectedItemIndex = 0;
        this.entity = entity;
        this.equipment = entity.getEquipment();
        ((EquipmentView)view).setEquipment(this.equipment);
    }

    @Override
    protected void initKeyPressMapping() {

        previousItem = new Task() {
            @Override
            public void run() {
                if (selectedItemIndex > 0) {
                    selectedItemIndex--;
                    ((EquipmentView) view).setSelectedIndex(selectedItemIndex);
                }
            }
            @Override
            public void stop() {}
        };

        nextItem = new Task() {
            @Override
            public void run() {
                if (selectedItemIndex < Equipment.Location.values().length - 1){
                    selectedItemIndex++;
                    ((EquipmentView) view).setSelectedIndex(selectedItemIndex);
                }
            }
            @Override
            public void stop() {}
        };

        useItem = new Task() {
            @Override
            public void run() {
                EquippableItem currentItem = equipment.getEquipmentLocation(Equipment.Location.values()[selectedItemIndex]);
                entity.unequipItem(currentItem);
            }

            @Override
            public void stop() {}
        };



        addKeyPressMapping(previousItem, KeyEvent.VK_UP);
        addKeyPressMapping(nextItem, KeyEvent.VK_DOWN);
        addKeyPressMapping(previousItem, KeyEvent.VK_LEFT);
        addKeyPressMapping(nextItem, KeyEvent.VK_RIGHT);
        addKeyPressMapping(useItem, KeyEvent.VK_ENTER);

    }

    public void setCloseEquipmentTask(Task task) {
        this.closeEquipment = task;
        addKeyPressMapping(closeEquipment, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(closeEquipment, KeyEvent.VK_Y);
    }
}
