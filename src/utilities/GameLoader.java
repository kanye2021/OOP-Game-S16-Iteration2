package utilities;

import controllers.entityControllers.PetController;
import models.entities.*;
import models.items.Item;
import models.map.Decal;
import models.map.Map;
import models.map.Terrain;
import models.map.Tile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Bradley on 2/25/2016.
 */


// TODO: Finish items and areaEffects.

public class GameLoader {

    private final String DEFAULT_MAP = IOUtilities.getFileSystemDependentPath("./src/res/maps/default_map.xml");
    private final Point DEFAULT_START_LOCATION = new Point(0,-2);

    public void createNewGame(GameState game, String occupation){

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

        // TODO: Inilialize the npcs.
        game.setNpcList(null);

        // TODO: Delete later. Just for testing and debugging pets.
        // init the pet one tile right the avatar (DEFAULT_START_LOCATION)
        Pet pet = new Pet(new Point(0, -1), newMap);
        PetController petController = new PetController(pet);
        newMap.insertEntity(pet);
        game.setAvatarsPet(pet, petController);

        // To Denzel: how to create an item ->
        // From: Sergio
        Item item = Item.ItemDictionary.GOLD_BOOTS.createInstance();
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
            Element map = (Element) mapList.item(0); //Only 1 map element atm


            // Create an empty array of tiles
            HashMap<Point, Tile> tiles = new HashMap<>();

            // Get the tilesNodes from the xml file.
            NodeList tileNodes = doc.getElementsByTagName("tile");
            int numTiles = tileNodes.getLength();

            for (int i = 0; i < numTiles; i++) {
                Element tileElement = (Element) tileNodes.item(i);

                // The tiles will be loaded using an axial coordinate system.
                int x = Integer.parseInt(tileElement.getAttribute("x"));
                int y = Integer.parseInt(tileElement.getAttribute("y"));

                // Declare variables use to construct a tile
                Terrain terrain = null;
//                AreaEffect areaEffect = null;
                Decal decal = null;
                Item item = null;
                Entity entity = null;

                // Get the terrain on the tile
                Element terrainElement = (Element) tileElement.getElementsByTagName("terrain").item(0);
                String terrainType = terrainElement.getAttribute("type");
                terrain = new Terrain(terrainType);

//                // Get the areaEffect if there is one
//                NodeList areaEffectNodes = tileElement.getElementsByTagName("area-effect");
//                if (areaEffectNodes.getLength() > 0) {
//                    Element areaEffectElement = (Element) areaEffectNodes.item(0);
//                    String areaEffectType = areaEffectElement.getAttribute("type");
//                    switch (areaEffectType) {
//                        case "take-damage":
//                            areaEffect = new TakeDamageAreaEffect();
//                            break;
//                        case "heal-damage":
//                            areaEffect = new HealDamageAreaEffect();
//                            break;
//                        case "level-up":
//                            areaEffect = new LevelUpAreaEffect();
//                            break;
//                        case "instant-death":
//                            areaEffect = new InstantDeathAreaEffect();
//                            break;
//                    }
//                }

                //Decal
                NodeList decalNodes = tileElement.getElementsByTagName("decal");
                if (decalNodes.getLength() > 0) {
                    Element decalElement = (Element) decalNodes.item(0);
                    int id = Integer.parseInt(decalElement.getAttribute("id"));
                    decal = new Decal(Decal.Types.values()[id]);
                }

                // Get the item if there is one
                NodeList itemNodes = tileElement.getElementsByTagName("item");
                if (itemNodes.getLength() > 0) {
                    Element itemElement = (Element) itemNodes.item(0);
                    String itemType = itemElement.getAttribute("type");
                    int id = Integer.parseInt(itemElement.getAttribute("id"));


                    //if statements for the different types of items

                    //if take-able

//                    } else if (itemType.equals(Item.Type.ONE_SHOT.toString())) {
//                        item = new OneShotItem(OneShotItem.Effects.values()[id]);
//                    } else if (itemType.equals((Item.Type.INTERACTIVE.toString()))) {
//                        item = new InteractiveItem(InteractiveItem.Quests.values()[id]);
//                    } else if (itemType.equals(Item.Type.OBSTACLE.toString())) {
//                        item = new Obstacle(Obstacle.Obstacles.values()[id]);
//                    } else {
//                        System.out.println("What the fuck");
//                    }

                }


                // TODO: Implement adding entities to the map from xml.
                // Get any entities that are on the tile.
//                NodeList entityNodes = tileElement.getElementsByTagName("entity");
//                if (entityNodes.getLength() > 0) {
//                    Element entityElement = (Element) entityNodes.item(0);
//                    //TODO: Load whatever attributes are necessary
////                    entity = new Entity();
//                }

//                tiles.get(new Point(x, y)) = new Tile(terrain, areaEffect, decal, item, entity);
                tiles.put(new Point(x, y),  new Tile(terrain, decal, item, entity));
            }

            return new Map(tiles);

        } catch (SAXParseException e) {
            System.out.println("Error parsing");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }

        return null;
    }
}
