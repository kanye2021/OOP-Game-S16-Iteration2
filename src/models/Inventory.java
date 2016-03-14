package models;

import models.items.Item;
import models.items.takeable.TakeableItem;
import models.map.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by aseber on 2/22/16.
 */
public class Inventory {
    //Instance variables
    private ArrayList<ItemNode> itemNodeArrayList;
    private int maxCapacity;
    private int money;
    //Initializes arrayList based of initialCapacity
    public Inventory(int initialCapacity, int moneyInput) {
        maxCapacity = initialCapacity;
        money = moneyInput;
        itemNodeArrayList = new ArrayList<ItemNode>(maxCapacity);
    }

    public void clear() {
        itemNodeArrayList.clear();
    }

    //Getters
    public ArrayList<ItemNode> getItemNodeArrayList() {
        return itemNodeArrayList;
    }

    // Setters / Modifiers
    public void setItemNodeArrayList(ArrayList<ItemNode> itemNodeArrayList) {
        this.itemNodeArrayList = itemNodeArrayList;
    }

    // Check that inventory is not empty before calling this method.
    public TakeableItem getRandomItem() {
        ItemNode randomNode = itemNodeArrayList.get((new Random()).nextInt(itemNodeArrayList.size()));
        return randomNode.getItem();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    //Returns item node at index
    public ItemNode getItemNodeAtIndex(int index) {
        return itemNodeArrayList.get(index);
    }

    //Return item at index
    public TakeableItem getItemAtIndex(int index) {
        return itemNodeArrayList.get(index).getItem();
    }

    //Returns index of ItemNode
    public int getIndexOfItemNode(ItemNode itemNode) {
        return itemNodeArrayList.indexOf(itemNode);
    }

    //Return index of item
    public int getIndexOfItem(TakeableItem item) {
        int index = 0;
        for (ItemNode node : itemNodeArrayList) {
            if (node.getItem() == item)
                index = getIndexOfItemNode(node);
        }

        return index;
    }

    //Return amount of item
    public int getAmountOfItem(TakeableItem item) {
        int index = 0;
        for (ItemNode node : itemNodeArrayList) {
            if (node.getItem() == item)
                index = node.getAmount();
        }
        return index;
    }

    public int getAmountOfItem(Item.ItemDictionary item) {
        int index = 0;
        for (ItemNode node : itemNodeArrayList) {
            if (node.getItem().getID() == item)
                index = node.getAmount();
        }
        return index;
    }

    public boolean addItem(TakeableItem item) {
        // If at capacity, return false and dont add item
        if (itemNodeArrayList.size() == maxCapacity) {
            return false;
        } else {
            itemNodeArrayList.add(new ItemNode(item));
            return true;
        }
    }

    public boolean removeItem(TakeableItem item) {
        // Search the array list for the matching item
        for (Iterator<ItemNode> iterator = itemNodeArrayList.iterator(); iterator.hasNext(); ) {
            ItemNode node = iterator.next();
            // References shud be the same so this equality shud work.
            // If not gotta override .equals()
            if (node.getItem() == item) {
                int amount = node.getAmount();
                // If have more than one, just decrease amount, otherwise remove that node.
                if (amount > 1) {
                    node.setAmount(amount - 1);
                } else {
                    itemNodeArrayList.remove(node);
                }
                return true;
            }
        }
        return false;
    }

    public void dropAll(Map map, Point location) {

        // Safe way to delete things from a list while iterating through it :)
        for (Iterator<ItemNode> iterator = itemNodeArrayList.iterator(); iterator.hasNext(); ) {
            ItemNode node = iterator.next();

            int amount = node.getAmount();
            for (int i = 0; i < amount; i++) {
                map.insertItemAtPoint(node.getItem(), location);
            }
            iterator.remove();
        }
    }

    // Removes the item and returns it to the map.
    public void dropItem(TakeableItem item, Map map, Point location) {
        if (removeItem(item)) {
            map.insertItemAtPoint(item, location);
        }
    }

    public boolean isEmpty() {
        return itemNodeArrayList.size() == 0;
    }

    public int getCurrentSize() {
        return itemNodeArrayList.size();
    }

    public int getMoney() {
        return money;
    }

    //Stores the item and the amount of it you have
    public class ItemNode {
        private TakeableItem item;
        private int amount;

        //Default constructor
        public ItemNode() {
            item = null;
            amount = 0;
        }

        //Constructor give only an item
        public ItemNode(TakeableItem item) {
            this.item = item;
            this.amount = 1;
        }

        //Constructor give only item + amount
        public ItemNode(TakeableItem item, int amount) {
            this.item = item;
            this.amount = amount;
        }

        //Getters for item node
        public TakeableItem getItem() {
            return item;
        }

        //Setter for item node
        public void setItem(TakeableItem item) {
            this.item = item;
        }

        public Image getImage() {
            return item.getImage();
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

    }

}
