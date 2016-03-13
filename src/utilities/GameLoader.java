package utilities;

import models.Equipment;
import models.area_effects.*;
import models.entities.*;
import models.entities.Entity;
import models.entities.npc.Dragon;
import models.entities.npc.Mount;
import models.entities.npc.NPC;
import models.entities.npc.ShopKeeper;
import models.items.Item;
import models.items.takeable.TakeableItem;
import models.items.takeable.equippable.EquippableItem;
import models.map.Decal;
import models.map.Map;
import models.map.Terrain;
import models.map.Tile;
import models.occupation.Occupation;
import models.stats.Stats;
import org.w3c.dom.*;
import org.xml.sax.SAXParseException;
import views.AreaViewport;
import views.Display;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.Area;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bradley on 2/25/2016.
 */


// TODO: Finish items and areaEffects.


public class GameLoader {

    private final String DEFAULT_MAP = IOUtilities.getFileSystemDependentPath("./src/res/maps/default_map.xml");
    private final Point DEFAULT_START_LOCATION = new Point(0,-2);
    private GameState game;
    private AreaViewport areaViewport;
    public void createNewGame(GameState gameState, String occupation){
        game = gameState;
        // Load the map
        Map newMap = loadMap(DEFAULT_MAP);
        game.setMap(newMap);

        // Create a new avatar
        Avatar newAvatar;
        switch(occupation){
            case "smasher":
                newAvatar = new SmasherAvatar(DEFAULT_START_LOCATION, newMap);
                break;
            case "summoner":
                newAvatar = new SummonerAvatar(DEFAULT_START_LOCATION, newMap);
                break;
            case "sneak":
                newAvatar = new SneakAvatar(DEFAULT_START_LOCATION, newMap);
                break;
            default:
                newAvatar = null;
                System.out.println("Error: Entity not created!");
        }
        game.setAvatar(newAvatar);

        // Add the avatar to the map.
        newMap.insertEntity(newAvatar);

        // TODO: Delete later. Just for testing and debugging pets.
        // init the pet one tile right the avatar (DEFAULT_START_LOCATION)
        Pet pet = new Pet(new Point(0, -1), newMap);
        newMap.insertEntity(pet);

        // TODO: Inilialize the npcs. (needs to be done by xml)
        //TODO: Current a tmp npc
        Point tmp = new Point(-10,-3);
        ShopKeeper newVillager = new ShopKeeper(tmp, newMap);
        newMap.insertEntity(newVillager);
        ArrayList<NPC> tmpList = new ArrayList<>();
        tmpList.add(newVillager);
        tmpList.add(pet);
        game.setNpcList(tmpList);


        Dragon dragon = new Dragon(new Point(1,-1),newMap);
        newMap.insertEntity(dragon);
    }
    public void loadGame(GameState gameState, String fileName){
        game = gameState; //Needs to update game state
        String updateFile = "./src/res/save_files/" + fileName;
        String filePath = IOUtilities.getFileSystemDependentPath(updateFile);
        System.out.println(filePath);
        //Function to call Loading the game
        loadMap(filePath);

        //Function to load the seen tiles (area viewport however is not initialized just yet so this function
        //will have to be called later)
//        loadSeenTiles(filePath);
    }
    public void loadSeenTiles(GameState gameState, String fileName){
        String updateFile = "./src/res/save_files/" + fileName;
        String filePath = IOUtilities.getFileSystemDependentPath(updateFile);
        System.out.println(filePath);
        areaViewport = gameState.getAreaViewport();

        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filePath));

            // Normalize
            doc.getDocumentElement().normalize();

            NodeList seenList = doc.getElementsByTagName("seen-tiles");
            Element seenTiles = (Element)seenList.item(0);
            // Get the tilesNodes from the xml file.
            NodeList tileNodes = seenTiles.getElementsByTagName("tile");
            int numTiles = tileNodes.getLength();
            HashMap<Point, Tile> seen = areaViewport.seenTiles;
            for (int i = 0; i < numTiles; i++) {
                Element tileElement = (Element)tileNodes.item(i);

                // The tiles will be loaded using an axial coordinate system.
                int x = Integer.parseInt(tileElement.getAttribute("x"));
                int y = Integer.parseInt(tileElement.getAttribute("y"));
                Point p = new Point();
                p.setLocation(x,y);

                AreaEffect area = getAreaEffect(tileElement);
                Item groundItem = getItemAtTile(tileElement);
                Decal decal = getDecal(tileElement, area);
                Terrain terrain = getTerrain(tileElement);
                Entity entity = null;
                Tile tmp = new Tile(terrain, decal, groundItem, entity, area);
                seen.put(p, tmp);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private Map loadMap(String filepath){

        try{
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filepath));

            // Normalize
            doc.getDocumentElement().normalize();

            NodeList mapList = doc.getElementsByTagName("map"); //Maybe in the future we have multiple maps?
            Element mapElement = (Element) mapList.item(0); //Only 1 map element atm


            // Create an empty array of tiles
            HashMap<Point, Tile> tiles = new HashMap<>();

            // Get the tilesNodes from the xml file.
            NodeList tileNodes = mapElement.getElementsByTagName("tile");
            int numTiles = tileNodes.getLength();

            //Create an empty array of NPCs
            ArrayList<NPC> npcList = new ArrayList<>();
            Avatar avatar = null;
            for (int i = 0; i < numTiles; i++) {
                Element tileElement = (Element) tileNodes.item(i);

                // The tiles will be loaded using an axial coordinate system.
                int x = Integer.parseInt(tileElement.getAttribute("x"));
                int y = Integer.parseInt(tileElement.getAttribute("y"));

                // Declare variables use to construct a tile
                Terrain terrain = getTerrain(tileElement);
                AreaEffect areaEffect = getAreaEffect(tileElement);
                Decal decal = getDecal(tileElement,areaEffect);
                Item item = getItemAtTile(tileElement);
                Entity entity = null;



                item = getItemAtTile(tileElement);

//                tiles.get(new Point(x, y)) = new Tile(terrain, areaEffect, decal, item, entity);
                tiles.put(new Point(x, y),  new Tile(terrain, decal, item, entity, areaEffect));
            }

            Map newMap = new Map(tiles);

            game.setMap(newMap);
            addEntities(mapElement, newMap);

            return newMap;

        } catch (SAXParseException e) {
            System.out.println("Error parsing");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }

        return null;
    }
    public Terrain getTerrain(Element tileElement){
        Element terrainElement = (Element) tileElement.getElementsByTagName("terrain").item(0);
        String terrainType = terrainElement.getAttribute("type");
        Terrain terrain = new Terrain(terrainType);
        return terrain;
    }
    public Decal getDecal(Element tileElement, AreaEffect areaEffect){
        Decal decal = null;
        if (areaEffect != null) {
            decal = areaEffect.getDecal();
        }
        else {
            NodeList decalNodes = tileElement.getElementsByTagName("decal");
            if (decalNodes.getLength() > 0) {
                Element decalElement = (Element) decalNodes.item(0);
                int id = Integer.parseInt(decalElement.getAttribute("id"));
                decal = new Decal(Decal.Types.values()[id]);
            }
        }
        return decal;
    }
    public AreaEffect getAreaEffect(Element tileElement){
        // Get the areaEffect if there is one
        NodeList areaEffectNodes = tileElement.getElementsByTagName("area-effect");
        AreaEffect areaEffect = null;
        if (areaEffectNodes.getLength() > 0) {
            Element areaEffectElement = (Element) areaEffectNodes.item(0);
            String areaEffectType = areaEffectElement.getAttribute("type");
            String value;
            int intValue;
            switch (areaEffectType) {
                case "take-damage":
                    value = areaEffectElement.getAttribute("value");
                    intValue = Integer.parseInt(value);
                    areaEffect = new TakeDamageAreaEffect(intValue);
                    break;
                case "heal-damage":
                    value = areaEffectElement.getAttribute("value");
                    intValue = Integer.parseInt(value);
                    areaEffect = new HealDamageAreaEffect(intValue);
                    break;
                case "level-up":
                    areaEffect = new LevelUpAreaEffect();
                    break;
                case "instant-death":
                    areaEffect = new InstantDeathAreaEffect();
                    break;
                case "teleport":
                    String[] pointValue = areaEffectElement.getAttribute("value").split(",");
                    int targetX = Integer.parseInt(pointValue[0]);
                    int targetY = Integer.parseInt(pointValue[1]);
                    areaEffect = new TeleportAreaEffect(new Point(targetX, targetY));
                    break;
                case "trap":
                    value = areaEffectElement.getAttribute("value");
                    intValue = Integer.parseInt(value);
                    areaEffect = new TrapAreaEffect(intValue);
                    break;
            }
        }
        return areaEffect;
    }
    public Item getItemAtTile(Element tileElement){
        // Get the item if there is one
        Item item = null;
        NodeList itemNodes = tileElement.getElementsByTagName("item");
        if (itemNodes.getLength() > 0) {
            Element itemElement = (Element) itemNodes.item(0);
            String itemType = itemElement.getAttribute("type");
            int id = Integer.parseInt(itemElement.getAttribute("id"));

            //if statements for the different types of items


            item = Item.ItemDictionary.itemFromID(id);
            if (item == null) {
                System.out.println("GameLoader: unrecognized itemID");
            } else {
                System.out.println("GameLoader: successfully loaded an item (id = " + id + ")");
            }
        }
        return item;
    }

    public void addEntities(Element mapElement, Map newMap){
        // TODO: Implement adding entities to the map from xml.
        // Get any entities that are on the tile.
        NodeList entityNodes = mapElement.getElementsByTagName("entity");
        ArrayList<NPC> npcList = new ArrayList<>();
        Avatar avatar = null;
        Entity entity;
        for (int i = 0; i <entityNodes.getLength(); i++){
            Element entityElement = (Element) entityNodes.item(i);
            //TODO: Load whatever attributes are necessary
            //entity = new Entity();
            String[] pointValue = entityElement.getAttribute("location").split(",");
            int targetX = Integer.parseInt(pointValue[0]);
            int targetY = Integer.parseInt(pointValue[1]);
            Point p = new Point();
            p.setLocation(targetX,targetY);

            if ((entityElement.getAttribute("type")).contains("Avatar")){
                avatar = getAvatar(entityElement, p, newMap);
                entity = avatar;
            }else {
                NPC n = getNPC(entityElement, p, newMap);
                npcList.add(n);
                entity = n;
            }
            NodeList mountList = entityElement.getElementsByTagName("mount");
            if (mountList.getLength() > 0){
                Element mountElement = (Element)mountList.item(0);
                //Element entityMount = (Element)mountElement.getElementsByTagName("entity").item(0);
                Mount mount = (Mount)getNPC(mountElement,p,newMap);
                entity.setMount(mount);
            }


            newMap.insertEntity(entity);
        }
        game.setAvatar(avatar);
        game.setNpcList(npcList);
    }
    public Avatar getAvatar(Element e, Point p, Map map){
        Avatar avatar;
        String type = e.getAttribute("occupation");
        switch(type) {
            case "Smasher":
                //You will have to set the map later on
                avatar = new SmasherAvatar(p, map);
                break;
            case "Sneak":
                avatar = new SneakAvatar(p,map);
                break;
            case "Summoner":
                avatar = new SummonerAvatar(p,map);
                break;
            default: //This shouldn't happen
                avatar = new SmasherAvatar(p, map);
                break;
        }
        updateEntity(e, avatar);
        return avatar;
    }

    public NPC getNPC(Element e, Point p, Map map){
        NPC npc = null;
        String type = e.getAttribute("type");
        switch(type) {
            case "Dragon":
                npc = new Dragon(p, map);
                break;
            case "ShopKeeper":
                npc = new ShopKeeper(p,map);
                break;
            case "Pet":
                npc = new Pet(p,map);
                break;
        }
        updateEntity(e,npc);
        return npc;
    }

    public void updateEntity(Element e, Entity entity){
        //Update Orientation
        String orientation = e.getAttribute("orientation");
        for (Map.Direction direction : Map.Direction.values()){
            if (direction.name().equals(orientation)){
                entity.setOrientation(direction);
            }
        }
        updateInventory(e, entity);
        updateStats(e, entity);
        updateEquipped(e, entity);
        //TODO:
        updateSkills(e,entity);
    }
    public void updateInventory(Element e, Entity entity){
        NodeList list = e.getElementsByTagName("item-inv");
        for (int i = 0; i < list.getLength(); i++){
            Element item = (Element)list.item(i);
            int id = Integer.parseInt(item.getAttribute("id"));
            //Items in the inventory has to be Takeable items
            entity.addItemToInventory( (TakeableItem)Item.ItemDictionary.itemFromID(id) );
        }
    }
    public void updateStats(Element e, Entity entity){
        Element stats = (Element)(e.getElementsByTagName("stats").item(0));
        int level = Integer.parseInt( stats.getAttribute("Level") );
        for (int i = 0; i < level; i++){
            entity.getStats().levelUp(); //Solely just levels up the entity
        }

    }
    public void updateEquipped(Element e, Entity entity) {
        NodeList equipedList = e.getElementsByTagName("equipment");
        Element equiped = (Element) equipedList.item(0);
        NamedNodeMap attrList = equiped.getAttributes();
        for (int i = 0; i < attrList.getLength(); i++) {
            int value = Integer.parseInt(attrList.item(i).getNodeValue());
            //-1 is the default value which states there is nothing there
            //If it is not -1 that means an item exists
            if (value != -1) {
                Item thisItem = Item.ItemDictionary.itemFromID(value);
                //This has to be equippable item if Im getting it form that equipped view
                entity.equipItem((EquippableItem)thisItem);
            }
        }
    }
    public void updateSkills(Element elm, Entity entity){

    }


}
