package controllers;

import models.Equipment;
import models.entities.Entity;
import models.items.takeable.equippable.EquippableItem;
import utilities.StateManager;
import utilities.Task;
import utilities.TaskWrapper;
import views.EquipmentView;
import views.View;

import java.awt.event.KeyEvent;

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
        ((EquipmentView) view).setEquipment(this.equipment);
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
            public void stop() {
            }
        };

        nextItem = new Task() {
            @Override
            public void run() {
                if (selectedItemIndex < Equipment.Location.values().length - 1) {
                    selectedItemIndex++;
                    ((EquipmentView) view).setSelectedIndex(selectedItemIndex);
                }
            }

            @Override
            public void stop() {
            }
        };

        useItem = new Task() {
            @Override
            public void run() {
                EquippableItem currentItem = equipment.getEquipmentLocation(Equipment.Location.values()[selectedItemIndex]);
                entity.unequipItem(currentItem);
            }

            @Override
            public void stop() {
            }
        };


        addKeyPressMapping(new TaskWrapper(previousItem, "Previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextItem, "Next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(previousItem, "Previous"), KeyEvent.VK_LEFT);
        addKeyPressMapping(new TaskWrapper(nextItem, "Next"), KeyEvent.VK_RIGHT);
        addKeyPressMapping(new TaskWrapper(useItem, "Use item"), KeyEvent.VK_ENTER);

    }

    public void setCloseEquipmentTask(Task task) {
        this.closeEquipment = task;
        addKeyPressMapping(new TaskWrapper(closeEquipment, "Close equipment"), KeyEvent.VK_ESCAPE);
        addKeyPressMapping(new TaskWrapper(closeEquipment, "Close equipment"), KeyEvent.VK_Y);
    }
}
