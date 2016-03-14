package controllers;

import models.Inventory;
import models.entities.Entity;
import models.entities.npc.actions.Action;
import models.entities.npc.NPC;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import utilities.StateManager;
import utilities.Task;
import utilities.TaskWrapper;
import views.InventoryView;
import views.NPCMenuView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 3/2/16.
 */
public class InventoryViewController extends ViewController {

    private int selectedItemIndex;
    private Task previousItem;
    private Task nextItem;
    private Task dropItem;
    // useItem will equip an equippable item or use a consumable or whatever, or not do anything if not consumable or equippable
    private Task useItem;
    private Task closeInventory;


    private Entity entity;
    private Inventory inventory;
    private ArrayList<Inventory.ItemNode> itemNodeArrayList;

    public InventoryViewController(View view, StateManager stateManager, Entity entity) {
        super(view, stateManager);
        selectedItemIndex = 0;
        this.entity = entity;
        this.inventory = entity.getInventory();
        this.itemNodeArrayList = inventory.getItemNodeArrayList();
        ((InventoryView)view).setItemNodeList(this.itemNodeArrayList);
    }

    @Override
    protected void initKeyPressMapping() {

        previousItem = new Task() {
            @Override
            public void run() {
                if (selectedItemIndex > 0) {
                    selectedItemIndex--;
                    ((InventoryView) view).updateSelected(selectedItemIndex);
                }
            }

            @Override
            public void stop() {}
        };

        nextItem = new Task() {
            @Override
            public void run() {
                if (selectedItemIndex < itemNodeArrayList.size() - 1){
                    selectedItemIndex++;
                    ((InventoryView) view).updateSelected(selectedItemIndex);
                }
            }
            @Override
            public void stop() {}
        };

        dropItem = new Task() {
            @Override
            public void run() {
                TakeableItem currentItem = itemNodeArrayList.get(selectedItemIndex).getItem();
                entity.dropItem(currentItem);
                selectedItemIndex--;
                if (selectedItemIndex < 0) selectedItemIndex = 0;
                ((InventoryView) view).updateSelected(selectedItemIndex);
            }

            @Override
            public void stop() {}
        };

        useItem = new Task() {
            @Override
            public void run() {
                TakeableItem currentItem = itemNodeArrayList.get(selectedItemIndex).getItem();
                int invSize = itemNodeArrayList.size();
                if (currentItem.isEquipable()) {
                    entity.equipItem((EquippableItem) currentItem);
                    if(invSize != itemNodeArrayList.size())
                        selectedItemIndex--;
                } else {
                    currentItem.onUse(entity);
                    if(invSize != itemNodeArrayList.size())
                        selectedItemIndex--;
                }
                if (selectedItemIndex < 0) selectedItemIndex = 0;
                ((InventoryView) view).updateSelected(selectedItemIndex);
            }

            @Override
            public void stop() {}
        };


        addKeyPressMapping(new TaskWrapper(previousItem, "previous"), KeyEvent.VK_UP);
        addKeyPressMapping(new TaskWrapper(nextItem, "next"), KeyEvent.VK_DOWN);
        addKeyPressMapping(new TaskWrapper(previousItem, "previous"), KeyEvent.VK_LEFT);
        addKeyPressMapping(new TaskWrapper(nextItem, "next"), KeyEvent.VK_RIGHT);
        addKeyPressMapping(new TaskWrapper(useItem, "Use Item"), KeyEvent.VK_ENTER);
        addKeyPressMapping(new TaskWrapper(dropItem, "Drop Item"), KeyEvent.VK_D);

    }

    public void setCloseInventory(Task task) {
        this.closeInventory = task;
        addKeyPressMapping(new TaskWrapper(closeInventory, "Close"), KeyEvent.VK_ESCAPE);
        addKeyPressMapping(new TaskWrapper(closeInventory, "Close"), KeyEvent.VK_I);
    }
}
