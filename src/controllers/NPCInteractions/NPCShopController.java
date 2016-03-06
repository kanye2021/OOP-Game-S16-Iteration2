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

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by dyeung on 3/2/16.
 */
public class NPCShopController extends ViewController {
    private int selectedItemIndex;
    private int selectedView;
    private Task previousItem;
    private Task nextItem;
    // useItem will equip an equippable item or use a consumable or whatever, or not do anything if not consumable or equippable
    private Task playerBuyItem;
    private Task playerSellItem;
    private Task closeInventory;
    private Task escape;
    private Task switchViews;
    private Task doAction;

    private Entity shopKeeper;
    private ArrayList<Inventory.ItemNode> shopNodeList;
    private ArrayList<Inventory.ItemNode> avatarNodeList;
    private ArrayList<Inventory.ItemNode> currentList;
    private NPCMenuView menuView;
    private Avatar avatar; //The person who is doing the buying
    private GameViewController gameViewController;
    public NPCShopController(View view, StateManager stateManager, GameViewController gvController, Entity entity, Avatar avatar) {
        super(view, stateManager);
        selectedItemIndex = 0;
        selectedView = 0;
        this.shopKeeper = entity;
        this.shopNodeList = entity.getInventory().getItemNodeArrayList();
        this.avatarNodeList = avatar.getInventory().getItemNodeArrayList();
        this.avatar = avatar;
        this.gameViewController = gvController;
        updateCurrentList();
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
                if (selectedItemIndex < currentList.size() - 1){
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
                TakeableItem currentItem = avatarNodeList.get(selectedItemIndex).getItem();
                avatar.sellItem(currentItem,1);
                ((ShopKeeper)shopKeeper).buy(currentItem);
            }

            @Override
            public void stop() {}
        };

        playerBuyItem = new Task() {
            @Override
            public void run() {
                TakeableItem currentItem = shopNodeList.get(selectedItemIndex).getItem();
                if (avatar.getAmountofMoney() > currentItem.getMonetaryValue()) {
                    //avatar.equipItem((EquippableItem) currentItem);
                    avatar.buyItem(currentItem);
                    ((ShopKeeper)shopKeeper).sell(currentItem);
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
        switchViews = new Task(){

            @Override
            public void run() {
                System.out.println("Making sure it works: " +selectedView);
                selectedView = selectedView ^ 1;
                ((NPCShopView)view).updateSelectedView(selectedView);
                selectedItemIndex = 0;
                ((NPCShopView)view).updateSelected(selectedItemIndex); //resets back to zero
                updateCurrentList();
            }

            @Override
            public void stop() {

            }
        };
        doAction = new Task(){

            @Override
            public void run() {
                if (selectedView == 0) {
                    playerSellItem.run();
                }else {
                    playerBuyItem.run();
                }
            }

            @Override
            public void stop() {

            }
        };

        addKeyPressMapping(previousItem, KeyEvent.VK_UP);
        addKeyPressMapping(nextItem, KeyEvent.VK_DOWN);
        addKeyPressMapping(previousItem, KeyEvent.VK_LEFT);
        addKeyPressMapping(nextItem, KeyEvent.VK_RIGHT);
        addKeyPressMapping(doAction, KeyEvent.VK_ENTER);
        addKeyPressMapping(escape, KeyEvent.VK_BACK_SPACE);
        addKeyPressMapping(switchViews, KeyEvent.VK_TAB);

    }

    public void setCloseInventory(Task task) {
        this.closeInventory = task;
        addKeyPressMapping(closeInventory, KeyEvent.VK_ESCAPE);
        addKeyPressMapping(closeInventory, KeyEvent.VK_I);
    }
    public void updateCurrentList(){
        if (selectedView == 0) {
            currentList = avatarNodeList;
        }else {
            currentList = shopNodeList;
        }
    }
}
