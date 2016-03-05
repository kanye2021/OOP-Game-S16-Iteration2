package controllers.NPCInteractions;

import controllers.GameViewController;
import controllers.ViewController;
import models.Inventory;
import models.entities.Avatar;
import models.entities.Entity;
import models.entities.npc.ShopKeeper;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import utilities.StateManager;
import utilities.Task;
import views.NPCMenuView;
import views.NPCShopView;
import views.View;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by dyeung on 3/2/16.
 */
public class NPCShopController extends ViewController {
    private int selectedItemIndex;
    private Task previousItem;
    private Task nextItem;
    // useItem will equip an equippable item or use a consumable or whatever, or not do anything if not consumable or equippable
    private Task playerBuyItem;
    private Task playerSellItem;
    private Task closeInventory;
    private Task escape;

    private Entity shopKeeper;
    private Inventory inventory;
    private ArrayList<Inventory.ItemNode> itemNodeArrayList;
    private NPCMenuView menuView;
    private Avatar avatar; //The person who is doing the buying
    private GameViewController gameViewController;
    public NPCShopController(View view, StateManager stateManager, GameViewController gvController, Entity entity, Avatar avatar) {
        super(view, stateManager);
        selectedItemIndex = 0;
        this.shopKeeper = entity;
        this.inventory = entity.getInventory();
        this.itemNodeArrayList = inventory.getItemNodeArrayList();
        this.avatar = avatar;
        this.gameViewController = gvController;
    }

    @Override
    protected void initKeyPressMapping() {

        previousItem = new Task() {
            @Override
            public void run() {
                if (selectedItemIndex > 0) {
                    selectedItemIndex--;
                    ((NPCShopView) view).updateSelected(selectedItemIndex);
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
                    ((NPCShopView) view).updateSelected(selectedItemIndex);
                }
            }
            @Override
            public void stop() {}
        };

        playerSellItem = new Task() {
            @Override
            public void run() {
                TakeableItem currentItem = itemNodeArrayList.get(selectedItemIndex).getItem();
                shopKeeper.dropItem(currentItem);
            }

            @Override
            public void stop() {}
        };

        playerBuyItem = new Task() {
            @Override
            public void run() {
                TakeableItem currentItem = itemNodeArrayList.get(selectedItemIndex).getItem();
                if (avatar.getAmountofMoney() > currentItem.getMonetaryValue()) {
                    //avatar.equipItem((EquippableItem) currentItem);
                    ((ShopKeeper)shopKeeper).sell(currentItem);
                    avatar.buyItem(currentItem);
                } else {
                    // weird to tell them item to use itself then pass the entity o_O ?
//                    currentItem.onUse();
                }
            }

            @Override
            public void stop() {}
        };
        escape = new Task(){

            @Override
            public void run() {
                System.out.println("Making sure this is turned on");
                gameViewController.turnOffSubState();
            }

            @Override
            public void stop() {

            }
        };


        addKeyPressMapping(previousItem, KeyEvent.VK_UP);
        addKeyPressMapping(nextItem, KeyEvent.VK_DOWN);
        addKeyPressMapping(previousItem, KeyEvent.VK_LEFT);
        addKeyPressMapping(nextItem, KeyEvent.VK_RIGHT);
        addKeyPressMapping(playerBuyItem, KeyEvent.VK_ENTER);
        addKeyPressMapping(escape, KeyEvent.VK_BACK_SPACE);

    }

    public void setCloseInventory(Task task) {
        this.closeInventory = task;
        addKeyPressMapping(closeInventory, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(closeInventory, KeyEvent.VK_I);
    }
}
