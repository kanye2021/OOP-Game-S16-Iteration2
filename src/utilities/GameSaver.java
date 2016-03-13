package utilities;

import controllers.GameViewController;
import models.Equipment;
import models.Inventory;
import models.area_effects.*;
import models.entities.Avatar;
import models.entities.Entity;
import models.entities.npc.Mount;
import models.entities.npc.NPC;
import models.items.Item;
import models.map.Map;
import models.map.Tile;
import models.skills.SkillList;
import models.stats.Stats;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import views.AreaViewport;

import javax.print.Doc;
import javax.print.attribute.IntegerSyntax;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by dyeung on 3/12/16.
 */
public class GameSaver {
    //TODO: MIGHT HAVE TO CHANGE ALL FUNCTIONS TO PRIVATE


    private Map map;
    private Avatar avatar;
    private ArrayList<NPC> npcList;
    private String fileName;
    private AreaViewport areaViewport;
    public GameSaver(Map map, Avatar avatar, ArrayList<NPC> npcList, AreaViewport av){
        this.map = map;
        this.avatar = avatar;
        this.npcList = npcList;
        this.areaViewport = av;
    }
    public void saveGame(String fN){
        this.fileName = fN + ".xml";
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            String filePath = "./src/res/save_files/" + fileName;

            Element mainRootElement = doc.createElementNS(filePath, "Save_File"); //1 will be edited in the feature
            doc.appendChild(mainRootElement);
            // append child elements to root element
            mainRootElement.appendChild(saveMap(doc, map));
            HashMap<Point, Tile> seenTiles = areaViewport.seenTiles; //IDK WHY THIS IS PUBLIC?????
            mainRootElement.appendChild(saveSeenTiles(doc,seenTiles));

            //Originally was done so that each entity will be a list (no longer the case but possibly a better change)
            //mainRootElement.appendChild(saveEntities(doc, avatar));

            //Write to XML
            writeToXml(doc,filePath);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public Node saveMap(Document doc, Map currentMap){
        Element map = doc.createElement("map");
        HashMap<Point, Tile> tiles = currentMap.getTiles();
        Iterator it = tiles.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            map.appendChild(saveTile(doc, (Point)pair.getKey(), (Tile)pair.getValue()));
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return map;
    }
    public Node saveSeenTiles(Document doc, HashMap<Point, Tile> seenTiles){
        Element seen = doc.createElement("seen-tiles");
        Iterator it = seenTiles.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            seen.appendChild(saveTile(doc, (Point)pair.getKey(), (Tile)pair.getValue()));
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return seen;
    }
    public Node saveTile(Document doc, Point location, Tile currentTile){
        Element tile = doc.createElement("tile");

        tile.setAttributeNode( saveAttr(doc,"x", (int)location.getX()) );
        tile.setAttributeNode( saveAttr(doc,"y", (int)location.getY()) );

        Element terrain = doc.createElement("terrain");
        terrain.setAttributeNode(saveAttr(doc, "type", currentTile.getTerrainType()));
        tile.appendChild(terrain);

        if(currentTile.hasEntity()){
            tile.appendChild(saveEntity(doc, currentTile.getEntity()));
        }
        if (currentTile.getAreaEffect() != null) {
            tile.appendChild( saveAreaEffect( doc,currentTile.getAreaEffect()));
        }
        ArrayList<Item> items = currentTile.getItems();
        for(Item item: items){
            Element itemElement = doc.createElement("item");
            itemElement.setAttributeNode( saveAttr(doc, "id", item.getItemId()) );
            itemElement.setAttributeNode( saveAttr(doc, "type", item.getType()) );

            tile.appendChild(itemElement);
        }
        return tile;
    }
    public Node saveAreaEffect(Document doc, AreaEffect aoe){
        Element area = doc.createElement("area-effect");
        area.setAttributeNode( saveAttr(doc, "type", aoe.getType()));

        if (aoe.getType() == "teleport"){
            Point p = ((TeleportAreaEffect)aoe).getPoint();
            String value = (int)p.getX() + "," + (int)p.getY();
            area.setAttributeNode( saveAttr(doc, "value", value));
        }else {
            area.setAttributeNode( saveAttr(doc, "value", aoe.getValue()));
        }

        return area;
    }
    // --------------Entity Stuff ------------------//
    public Node saveEntity(Document doc, Entity currentEntity){
        Element entity = doc.createElement("entity");
        int locationX = (int) currentEntity.getLocation().getX();
        int locationY = (int) currentEntity.getLocation().getY();
        entity.setAttributeNode( saveAttr(doc, "location", locationX + "," + locationY));
        entity.setAttributeNode( saveAttr(doc, "occupation", currentEntity.getOccupation()) );
        entity.setAttributeNode( saveAttr(doc, "orientation", currentEntity.getOrientation().name()) );
        entity.setAttributeNode( saveAttr(doc, "type", currentEntity.getType()));

        entity.appendChild( saveInventory( doc, currentEntity.getInventory()) );
        entity.appendChild( saveEquipped(doc, currentEntity.getEquipment()));
        //entity.appendChild( saveStats(doc, currentEntity.getStats()));
        entity.appendChild( saveStats(doc, currentEntity));
        entity.appendChild( saveSkills(doc, currentEntity.getSkills()));

        if (currentEntity.getMount() != null){ //if the entity has a mount
            entity.appendChild( saveMount(doc, currentEntity.getMount()));
        }
        //TODO: Pet should be fine since they exist on the map and the location will realize its a pet
        return entity;
    }
    public Node saveMount(Document doc, Mount mount){
        Element mountElm = doc.createElement("mount");
        int locationX = (int) mount.getLocation().getX();
        int locationY = (int) mount.getLocation().getY();
        mountElm.setAttributeNode( saveAttr(doc, "location", locationX + "," + locationY));
        mountElm.setAttributeNode( saveAttr(doc, "occupation", mount.getOccupation()) );
        mountElm.setAttributeNode( saveAttr(doc, "orientation", mount.getOrientation().name()) );
        mountElm.setAttributeNode( saveAttr(doc, "type", mount.getType()));

        mountElm.appendChild( saveInventory( doc, mount.getInventory()) );
        mountElm.appendChild( saveEquipped(doc, mount.getEquipment()));
        //mountElm.appendChild( saveStats(doc, mount.getStats()));
        mountElm.appendChild( saveStats(doc, mount));
        mountElm.appendChild( saveSkills(doc, mount.getSkills()));
        return mountElm;
    }
    public Node saveInventory(Document doc, Inventory inv){
        Element inventory = doc.createElement("inventory");
        for(int i = 0; i < inv.getCurrentSize(); i++){
            inventory.appendChild( saveItem(doc, inv.getItemNodeAtIndex(i)));
        }
        return inventory;
    }
    public Node saveItem(Document doc, Inventory.ItemNode i){
        Element item = doc.createElement("item-inv");
        Item tmpI = i.getItem();
        item.setAttributeNode(saveAttr(doc, "id", tmpI.getItemId()) );
        item.setAttributeNode(saveAttr(doc, "count", i.getAmount()) );

        return item;
    }
    public Node saveEquipped(Document doc, Equipment equip){
        Element equipment = doc.createElement("equipment");
        for (Equipment.Location location : Equipment.Location.values()) {
            boolean hasItemAtSlot = equip.isEquipmentAtLocation(location);
            int value;
            if(hasItemAtSlot){
                value = equip.getEquipmentLocation(location).getItemId();
            }else {
                value = -1;
            }
            equipment.setAttributeNode( saveAttr(doc, location.getDescriptor(), value));
        }

        return equipment;
    }
    //TODO: fix this, when you get the stats you are actually creating new stats from occupation
    //Big problem since none of those stats are "saved"
    public Node saveStats(Document doc, Entity entity){
        Stats currentStats = entity.getStats();
        Element stats = doc.createElement("stats");
        for (Stats.Type type : Stats.Type.values()){
            String statString = type.getDescriptor();
            int value = currentStats.getStat(type);
            if (type.equals(Stats.Type.LEVEL)){
                value = entity.getLevel();
            }
            stats.setAttributeNode( saveAttr(doc, statString, value) );
        }
        return stats;
    }
    public Node saveSkills(Document doc, SkillList currentSkills){
        Element skills = doc.createElement("skills");
        for (int i = 0; i <currentSkills.size(); i++){
            Element skill = doc.createElement("skill");
            Attr a = saveAttr(doc, currentSkills.get(i).getName(), currentSkills.get(i).getLevel());
            skill.setAttributeNode(a);
            skills.appendChild(skill);
        }
        return skills;
    }
    //---------------Helper functions ---------------
    public Attr saveAttr(Document doc, String attr, String value) {
        Attr a = doc.createAttribute(attr);
        a.setValue(value);
        return a;
    }
    public Attr saveAttr(Document doc, String attr, int value) {
        Attr a = doc.createAttribute(attr);
        a.setValue(Integer.toString(value) );
        return a;
    }

    //----------Function to transform saved (doc) into Xml and the Console -------
    public static void writeToXml(Document doc, String fileName){
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));

            //Formats it nicely (5 is the max number of child nodes
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformerFactory.setAttribute("indent-number", 5);
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount",
                    Integer.toString(5));
            transformer.transform(source, result);

            //Output to console for testing
            //StreamResult consoleResult = new StreamResult(System.out);
            //transformer.transform(source, consoleResult);
            System.out.println("Saved success!");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
