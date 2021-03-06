package AI.Vision;

import AI.Memory.VisualInterface;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import models.map.Map;
import models.map.Tile;
import utilities.TileUtilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bradley on 3/5/16.
 */
public class VisualCortex {

    private NPC npc;
    private Map map;
    private VisualInterface memory;

    public VisualCortex(NPC npc, VisualInterface memory) {
        this.npc = npc;
        this.map = npc.getMap();
        this.memory = memory;
    }

    // Process what is going on around you and add all of that stuff to a VisualInfo objec
    // For now, all visual info is processed 120 degrees in the direction of the orientation.
    public void process() {

        // Get all the tiles the entity can see
        HashMap<Tile, Point> tiles = TileUtilities.getTilesRecursively(map, npc.getLocation(), npc.getRadiusOfVisiblility());

        Tile tile;
        Point pointOfTile;

        Entity entityAtTile;
        for (java.util.AbstractMap.Entry<Tile, Point> pair : tiles.entrySet()) {

            tile = pair.getKey();
            pointOfTile = pair.getValue();

            entityAtTile = tile.getEntity();

            if (entityAtTile != null) {

                memory.addVisualInput(entityAtTile, pointOfTile);

            }

            // Get items here.
            ArrayList<Item> itemList = tile.getItems();
            for (Item item : itemList) {
                if (item != null) {
                    memory.addVisualInput(item, pointOfTile);
                }
            }


        }

    }

}
