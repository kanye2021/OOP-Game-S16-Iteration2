package utilities;

import models.Entity;
import models.StatModification;
import models.StatModificationList;
import models.Stats;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;

/**
 * Created by dyeung on 2/21/16.
 */
public class Load {

    private String file; //name of the file plus the extension
    private String filePath = IOUtilities.getFileSystemDependentPath("./src/res/save_files/");
    private DocumentBuilderFactory documentBuilderFactory;
    private Document doc;
    public Load(String fileName) { //Question: Is the avatar going to be passed into the loading game function
        //For now constructor won't do anything
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        file = filePath + fileName;

    }

    public void loadGame() {
        try {
            //Creates the document part of the builder
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new File(file));
            doc.getDocumentElement().normalize();
            //First you create the avatar
            loadAvatar();

        }catch(Exception e ){
            e.printStackTrace();
        }

    }
//TODO:Create stuff for entity
//Remainder that the items are being generated then added to the inventory. Same with the stats (being created then added)
    public Entity loadAvatar(){
        try {
            Element avatar = getSingleElement("avatar");
            Point p = getLocationOf(avatar);
            String orientation = avatar.getAttribute("orientation");
            String occupation = avatar.getAttribute("occupation");

            Element inventory = getSingleElement("inventory");
            loadInventory(inventory);
            Element pStats = getSingleElement("pStats");
            loadStats(pStats);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Inventory loadInventory(Element e){ //Is suppose to return inventory
        //int sizeOfInv = getIntValueFromAttr(e,"size"); //Unnecessary for right now
        NodeList items = e.getElementsByTagName("item");
        Inventory inv = new Inventory();
        for (int i = 0; i < items.getLength(); i++) {
            Element item = (Element) items.item(i);
            int id = getIntValueFromAttr(item, "id");
            int count = getIntValueFromAttr(item, "count");
            int pos = getIntValueFromAttr(item, "pos");
            Item newItem = new Item(id);
            //Adds the item based on the position it was in the inventory
            inv.addItem(newItem, count, pos);
        }
        return inv;
    }
    public StatModificationList loadStats(Element e){ //Is supposed to return stat modification
        StatModificationList listOfStats = new StatModificationList();
        NamedNodeMap attrList = e.getAttributes();
        for (int i = 0; i < attrList.getLength(); i++) {
            Node statAttr = attrList.item(i);
            String statName = statAttr.getNodeName(); //TODO:Instead of string I would have to make an id
            int statID = 0;
            int statValue = Integer.parseInt(statAttr.getNodeValue());
            StatModification newStats = new StatModification(Stats.StatType.values()[statID], statValue);
            listOfStats.add(newStats);
        }
        return listOfStats;
    }
    public Point getLocationOf(Element e){
        int x = getIntValueFromAttr(e, "location_x");
        int y = getIntValueFromAttr(e, "location_y");
        return new Point(x,y);

    }
    public int getIntValueFromAttr(Element e, String attribute){
        return Integer.parseInt( e.getAttribute(attribute) );
    }


    //This kinda sucks but its the only way to get one element of the document
    public Element getSingleElement(String str){
        return (Element) doc.getElementsByTagName(str).item(0);
    }
}