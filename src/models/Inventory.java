package models;

import models.items.takeable.TakeableItem;

import java.util.ArrayList;

/**
 * Created by aseber on 2/22/16.
 */
public class Inventory {
    //Stores the item and the amount of it you have
    public class ItemNode{
        private TakeableItem item;
        private int amount;

        //Default constructor
        public ItemNode(){
            item = null;
            amount = 0;
        }

        //Constructor give an item
        public ItemNode(TakeableItem item){
            this.item = item;
            this.amount = 1;
        }

        //Getters for item node
        public TakeableItem getItem() {
            return item;
        }

        public int getAmount() {
            return amount;
        }

        //Setter for item node
        public void setItem(TakeableItem item) {
            this.item = item;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

    }

    //Instance variables
    private ArrayList<ItemNode> itemNodeArrayList;
    private int maxCapacity;

    //Initializes arrayList based of initialCapacity
    public Inventory(int initialCapacity){
        maxCapacity = initialCapacity;
        itemNodeArrayList = new ArrayList<ItemNode>(maxCapacity);
    }

    //Getters
    public ArrayList<ItemNode> getItemNodeArrayList(){
        return itemNodeArrayList;
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    //Setters, doubt we'll use 'em much
    public void setItemNodeArrayList(ArrayList<ItemNode> itemNodeArrayList){
        this.itemNodeArrayList = itemNodeArrayList;
    }

    public void setMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    //Returns item node at index
    public ItemNode getItemNodeAtIndex(int index){
        return itemNodeArrayList.get(index);
    }

    //Return item at index
    public TakeableItem getItemAtIndex(int index){
        return itemNodeArrayList.get(index).getItem();
    }

    //Returns index of ItemNode
    public int getIndexOfItemNode(ItemNode itemNode){
        return  itemNodeArrayList.indexOf(itemNode);
    }

    //Return index of item
    public int getIndexOfItem(TakeableItem item){
        int index = 0;
        for(ItemNode node : itemNodeArrayList){
            if(node.getItem() == item)
                index = getIndexOfItemNode(node);
        }

        return index;
    }

    //Return amount of item
    public int getAmountOfItem(TakeableItem item){
        int index = 0;
        for(ItemNode node : itemNodeArrayList){
            if(node.getItem() == item)
                index = node.getAmount();
        }
        return index;
    }



}
