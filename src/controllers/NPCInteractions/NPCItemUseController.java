package controllers.NPCInteractions;

import controllers.ViewController;
import models.Inventory;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.takeable.TakeableItem;
import models.items.takeable.consumable.ConsumableItem;
import models.items.takeable.equippable.EquippableItem;
import utilities.StateManager;
import utilities.Task;
import utilities.Toast;
import views.InventoryView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by David on 3/11/2016.
 */
public class NPCItemUseController extends ViewController {
    private int selectedItemIndex;
    private Task previousItem;
    private Task nextItem;
    private Task dropItem;
    // useItem will equip an equippable item or use a consumable or whatever, or not do anything if not consumable or equippable
    private Task useItem;
    private Task closeMenu;

    private NPC npc;
    private Entity entity;
    private Inventory inventory;
    private ArrayList<Inventory.ItemNode> itemNodeArrayList;

    public NPCItemUseController(View view, StateManager stateManager, Entity entity, NPC npc) {
        super(view, stateManager);
        selectedItemIndex = 0;
        this.entity = entity;
        this.npc = npc;
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

        useItem = new Task() {
            @Override
            public void run() {
                TakeableItem currentItem = itemNodeArrayList.get(selectedItemIndex).getItem();
                if (currentItem.isEquipable()) {
                    Toast.createToastWithTimer("Can't use that on the NPC", 500);
                }
                else if(currentItem.isConsumable()){
                    currentItem.onUse(npc);
                    entity.getInventory().removeItem(currentItem);
                }
                else{
                    Toast.createToastWithTimer("Nothing happened...", 1000);
                }
                selectedItemIndex--;
                if (selectedItemIndex < 0)
                    selectedItemIndex = 0;
                ((InventoryView) view).updateSelected(selectedItemIndex);
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

    public void setClose(Task task) {
        this.closeMenu = task;
        addKeyPressMapping(closeMenu, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(closeMenu, KeyEvent.VK_I);
    }
}
