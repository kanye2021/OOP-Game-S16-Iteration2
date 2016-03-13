package views;

import models.entities.Avatar;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;
import models.stats.Stats;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Bradley on 2/27/16.
 */
public class AreaViewport extends View {

    // Constants
    private final float MIN_OPACITY = 0.4f; // The min opacity for a visible tile
    private final float SEEN_OPACITY = 0.3f; // The min opacity for a seen tile.

    // The meat!!!
    private Map map;
    private Avatar avatar;

    // The veggies!!!!!
    private int viewportWidth;
    private int viewportHeight;
    private int hexSize; // The size of one of the hex tile sides;
    private int hexWidth; // derived ^^
    private int hexHeight; // derived ^^
    private int horizDistanceBtwnTiles; // This is derived from hexSize
    private int vertDistanceBtwnTiles; // This is derived from hexSize
    private Point viewportOffset; // This is used to drag the viewport around.

    // Some stuff for caching
    public HashMap<Point, Tile> seenTiles = new HashMap<>();
    private boolean reRender;
    private BufferedImage cachedViewport;

    //Debug stuff
    private boolean displayDebugInformation = false;

    // Just a container to hold an entity and a location in order to draw the health bars w/o opacity messed up
    private class EntityLocationTuple {
        public final Point point;
        public final Entity entity;
        public EntityLocationTuple(Entity e, Point p) {
            this.point = p;
            this.entity = e;
        }
    }
    private ArrayList<EntityLocationTuple> entityLocationTuples;

    public AreaViewport(int width, int height, Display display, Map map, Avatar avatar){
        super(width, height, display);

        entityLocationTuples = new ArrayList<EntityLocationTuple>();
        this.map = map;
        this.avatar = avatar;
        viewportOffset = new Point(0, 0);
        scaleView();
        hexSize = 23;
        hexWidth = hexSize * 2;
        hexHeight = Math.round((float)(Math.sqrt(3) /2 * hexWidth));
        horizDistanceBtwnTiles = hexSize * 3 /2;
        vertDistanceBtwnTiles = Math.round((float)(hexSize * Math.sqrt(3)));
        cachedViewport = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        reRender = true;
    }

    public void setViewportOffset(Point offset){
        viewportOffset = offset;
        reRender = true;
    }

    @Override
    public void render(Graphics g){

        if(map.needsToBeRendered() || reRender){
            Graphics g1 = cachedViewport.getGraphics();


            // Draw a black background
            g1.setColor(Color.BLACK);
            g1.fillRect(0,0,viewportWidth, viewportHeight);

            // Get the avatar's location. This location will be shown in the center of the viewport.
            Point logicalPoint = avatar.getLocation();
            Point pixelPoint = new Point(viewportWidth/2, viewportHeight/2);

            // Create a 2D graphcis obj
            Graphics2D g2 = (Graphics2D) g1.create();

            // Clear the existing status bar locations
            this.entityLocationTuples.clear();

            breadthFirstRender(logicalPoint, pixelPoint, g2);

            map.setNeedsToBeRendered(false);
            reRender = false;
            g1.dispose();

        }

        g.drawImage(cachedViewport, 0, 0, viewportWidth, viewportHeight, getDisplay());

        for (EntityLocationTuple et : this.entityLocationTuples) {
            drawEntityHealthBar(g, et.entity, et.point);
        }

        if (displayDebugInformation) {
            g.setColor(Color.WHITE);
            g.drawString(avatar.getLocation().toString(), viewportWidth - g.getFontMetrics().stringWidth(avatar.getLocation().toString()) - 50, 25);
        }
    }

    // This will traverse through all the tiles using a breadth first search. It will then render that tile.
    private void breadthFirstRender(Point logicalPoint, Point pixelPoint, Graphics2D g){

        // Get the radius of visibliity.
        int radiusOfVisibility = avatar.getRadiusOfVisiblility();

        // This hashmap will keep track of what we have already renderred in our traversal.
        HashMap<Point, Boolean> hasBeenRendered = new HashMap<>();

        // Create an empty queue of tile nodes.
        Queue<TileNode> tileQueue = new LinkedList<>();

        // Convert the first tile into a TileNode and push it into the queue.
        TileNode root = new TileNode(map.getTileAt(logicalPoint), logicalPoint, pixelPoint);
        root.distanceFromAvatar = 0;

        // Offset the root based upon the offset ammound
        root.pixelPoint.translate((int)viewportOffset.getX(), (int)viewportOffset.getY());
        tileQueue.offer(root); // offer is analogous to push (or enqueue).

        while(!tileQueue.isEmpty()){

            // Pop the current tile off the queue.
            TileNode currentTileNode = tileQueue.poll(); // poll is analogous to pop (or dequeue).

            // Check to see if the tile has already been renderred.
            if((hasBeenRendered.get(currentTileNode.logicalPoint) == null) || !hasBeenRendered.get(currentTileNode.logicalPoint) && isInRangeOfViewport(currentTileNode.pixelPoint)) {
                hasBeenRendered.put(currentTileNode.logicalPoint, true); // Mark the tile as having been renderred.

                // Render the current Tile
                if (!displayDebugInformation) {
                    if (currentTileNode.distanceFromAvatar < radiusOfVisibility) {

                        // Mark this tile as having been seen.
                        seenTiles.put(new Point(currentTileNode.logicalPoint), new Tile(currentTileNode.tile));

                        // Set the opacity based on the distance from the avatar.
                        float opacity = 1.0f - (float) (currentTileNode.distanceFromAvatar * 0.15);
                        opacity = opacity < MIN_OPACITY ? MIN_OPACITY : opacity;

                        renderTile(currentTileNode, g, opacity); // Render the tile.
                    } else if (seenTiles.get(currentTileNode.logicalPoint) != null) {
                        currentTileNode.tile = seenTiles.get(currentTileNode.logicalPoint); // Switch out the actual tile with the seen tile.
                        renderTile(currentTileNode, g, SEEN_OPACITY);
                    }
                } else {

                    // Yes, Austin put this in. You can tell by the spacing.
                    renderTile(currentTileNode, g, 1f);

                }

                // Push all the adjacent nodes onto the queue
                for(TileNode tileNode: getAdjacentTiles(currentTileNode)){
                    tileNode.distanceFromAvatar = currentTileNode.distanceFromAvatar + 1;
                    tileQueue.offer(tileNode);
                }
            }
        }
    }

    private void renderTile(TileNode tileNode, Graphics2D g, float opacity){

        // Do the actual Drawing here!
        Polygon tilePolygon = getHexTile(tileNode.pixelPoint);
        g.setColor(Color.BLACK);
        g.drawPolygon(tilePolygon); // This part kinda helps the tiles come together. Due to the math involved in rendering
        // The hex tiles, there are a few points where we have to cast to an int and lose precision.

        // Get the old clip (Should be the entire window).
        Shape oldClip = g.getClip();

        // Set the clip to just the hex tile
        g.setClip(tilePolygon);

        // Set the opacity.
        AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g.setComposite(acomp);

        // Render the tile image.
        Image tileImage = tileNode.tile.getTileImage();
        int tileX = (int) (tileNode.pixelPoint.getX() - hexWidth / 2);
        int tileY = (int) (tileNode.pixelPoint.getY()) - hexHeight / 2;
        g.drawImage(tileImage, tileX, tileY, hexWidth, hexHeight, getDisplay());

        // Add this entity to list of entities and their locations to render its health later alligator
        if(tileNode.tile.getEntity()!=null){
            this.entityLocationTuples.add(new EntityLocationTuple(tileNode.tile.getEntity(), new Point((int)tileNode.pixelPoint.getX(), (int)tileNode.pixelPoint.getY())));
        }

        g.setClip(oldClip);
    }


//    private void renderTile(TileNode tileNode, Graphics2D g, float opacity){
//
//        // Do the actual Drawing here!
//        Polygon tilePolygon = getHexTile(tileNode.pixelPoint);
//        g.setColor(Color.BLACK);
//        g.drawPolygon(tilePolygon); // This part kinda helps the tiles come together. Due to the math involved in rendering
//        // The hex tiles, there are a few points where we have to cast to an int and lose precision.
//
//        // Get the old clip (Should be the entire window).
//        Shape oldClip = g.getClip();
//
//        // Set the clip to just the hex tile
//        g.setClip(tilePolygon);
//
//        // Set the opacity.
//        AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
//        g.setComposite(acomp);
//
//        // Draw the terrain
//        Terrain terrain = tileNode.tile.getTerrain();
//        Image terrainImage = terrain.getImage();
//
//        int terrainX = (int) (tileNode.pixelPoint.getX() - hexWidth / 2);
//        int terrainY = (int) (tileNode.pixelPoint.getY()) - hexHeight / 2;
//        g.drawImage(terrainImage, terrainX, terrainY, hexWidth, hexHeight, getDisplay());
//
//
//        // Draw the items
//        Item item = tileNode.tile.getItem();
//        if(item!=null){
//            Image itemImage = item.getImage();
//
//            // Resize the item image
//            int scaledWidth = hexWidth * 1;
//            int scaledHeight = hexHeight * 1;
//
//            int itemX = (int)(tileNode.pixelPoint.getX() - scaledWidth /2);
//            int itemY = (int)(tileNode.pixelPoint.getY() - scaledHeight /2);
//            g.drawImage(itemImage, itemX, itemY, scaledWidth, scaledHeight,  getDisplay());
//        }
//
//        // Draw Area Effects / Decals
//        Decal decal = tileNode.tile.getDecal();
//        if(decal!=null && decal.isVisible()){
//            Image decalImage = decal.getImage();
//
//            // Resize the item image
//            int scaledWidth = hexWidth * 3/5;
//            int scaledHeight = hexHeight * 3/5;
//
//            int itemX = (int)(tileNode.pixelPoint.getX() - scaledWidth /2);
//            int itemY = (int)(tileNode.pixelPoint.getY() - scaledHeight /2);
//            g.drawImage(decalImage, itemX, itemY, scaledWidth, scaledHeight,  getDisplay());
//        }
//
//
//        // Display entities on the map
//        Entity entity = tileNode.tile.getEntity();
//        if (entity != null) {
//
//            Image entityImage = entity.getImage();
//
//            // Resize the entity image
//            int scaledWidth = hexWidth * 3 / 4;
//            int scaledHeight = hexHeight * 3 / 4;
//
//            int entityX = (int) (tileNode.pixelPoint.getX() - scaledWidth / 2);
//            int entityY = (int) (tileNode.pixelPoint.getY() - scaledHeight / 2);
//
//            if (entity.getMount() != null){
//                Image mountImage = entity.getMount().getImage();
//                g.drawImage(mountImage, entityX, entityY + scaledHeight/3, scaledWidth, scaledHeight, getDisplay());
//                g.drawImage(entityImage, entityX, entityY, scaledWidth, scaledHeight/2, getDisplay());
//            }else{
//                g.drawImage(entityImage, entityX, entityY, scaledWidth, scaledHeight, getDisplay());
//
//            }
//
//
//            // Add this entity to list of entities and their locations to render its health later alligator
//            this.entityLocationTuples.add(new EntityLocationTuple(entity, new Point(entityX, entityY)));
//
//        }
//
//        g.setClip(oldClip);
//    }



    private void drawEntityHealthBar(Graphics g, Entity entity, Point p) {
        int entityX = (int) p.getX() - hexWidth*3/8;
        int entityY = (int) p.getY() - hexHeight*3/8;

        Stats stats = entity.getStats();
        // Start with the healthbar
        // Get the necessary stats
        int health = stats.getStat(Stats.Type.HEALTH);
        int maxHealth = stats.getStat(Stats.Type.MAX_HEALTH);

        // Sizes
        int healthBarWidth = getScreenWidth()/12;
        int healthBarHeight = getScreenHeight()/53;

        // Set the font
        Font f = new Font("Courier New", 1, 14);
        g.setFont(f);


        // Set the location and size of the health bar.
        int healthBarX = entityX - healthBarWidth/3;
        int healthBarY = entityY - healthBarHeight;


        // Draw the outline of the health bar.
        g.setColor(Color.RED);
        g.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);

        // Determine what fraction of the healthbar should be shown.
        double healthFraction = (double) health / (double) maxHealth;
        int healthFillWidth = (int) (healthFraction * healthBarWidth);

        // Fill the healthbar
        g.setColor(Color.GREEN);
        g.fillRect(healthBarX, healthBarY, healthFillWidth, healthBarHeight);

        // Display the fraction of health
        g.setColor(Color.WHITE);
        String healthFractionString = "(" + health + "/" + maxHealth + ")";
        FontMetrics fm = g.getFontMetrics(f);

        // Place the font at the right of the bar
        Rectangle2D healthFractionRect = fm.getStringBounds(healthFractionString, g);

        int healthFractionX = healthBarX + (healthBarWidth - (int) healthFractionRect.getWidth())/2;
        int healthFractionY = healthBarY + healthBarHeight - 4;
        g.drawString(healthFractionString, healthFractionX, healthFractionY);

    }

    // This will be used in the BF traversal to get the list of adjacent tiles.
    private ArrayList<TileNode> getAdjacentTiles(TileNode tile){
        ArrayList<TileNode> adjacentTiles = new ArrayList<>();

        // Get the tile adjacent to the north.
        Point northLogicalPoint = new Point(tile.logicalPoint); // Get the tiles logical point
        northLogicalPoint.translate(0, -1);
        
        Point northPixelPoint = new Point(tile.pixelPoint);     // Get the tiles pixel point;
        northPixelPoint.translate(0, -vertDistanceBtwnTiles);

        Tile northTile = map.getTileAt(northLogicalPoint);
        if(northTile != null){
            adjacentTiles.add(new TileNode(northTile, northLogicalPoint, northPixelPoint));
        }

        // Get the tile to the south of the current position.
        Point southLogicalPoint = new Point(tile.logicalPoint);
        southLogicalPoint.translate(0, 1);
        
        Point southPixelPoint = new Point(tile.pixelPoint);
        southPixelPoint.translate(0, vertDistanceBtwnTiles);
        
        Tile southTile = map.getTileAt(southLogicalPoint);
        if(southTile != null){
            adjacentTiles.add(new TileNode(southTile, southLogicalPoint, southPixelPoint));
        }

        
        // Get the tile to the north west of the current position.
        Point northWestLogicalPoint = new Point(tile.logicalPoint);
        northWestLogicalPoint.translate(-1, 0);

        Point northWestPixelPoint = new Point(tile.pixelPoint);
        northWestPixelPoint.translate(-horizDistanceBtwnTiles, -vertDistanceBtwnTiles / 2);
        
        Tile northWestTile = map.getTileAt(northWestLogicalPoint);
        if(northWestTile != null){
            adjacentTiles.add(new TileNode(northWestTile, northWestLogicalPoint, northWestPixelPoint));
        }
        
        
        // Get the tile to the south east of the current position.
        Point southEastLogicalPoint = new Point(tile.logicalPoint);
        southEastLogicalPoint.translate(1, 0);
        
        Point southEastPixelPoint = new Point(tile.pixelPoint);
        southEastPixelPoint.translate(horizDistanceBtwnTiles, vertDistanceBtwnTiles / 2);
        
        Tile southEastTile = map.getTileAt(southEastLogicalPoint);
        if(southEastTile != null){
            adjacentTiles.add(new TileNode(southEastTile, southEastLogicalPoint, southEastPixelPoint));
        }

        
        // Get the tile to the north east of the current position.
        Point northEastLogicaPoint = new Point(tile.logicalPoint);
        northEastLogicaPoint.translate(1, -1);

        Point northEastPixelPoint = new Point(tile.pixelPoint);
        northEastPixelPoint.translate(horizDistanceBtwnTiles, -vertDistanceBtwnTiles / 2);
        
        Tile northEastTile = map.getTileAt(northEastLogicaPoint);
        if(northEastTile != null){
            adjacentTiles.add(new TileNode(northEastTile, northEastLogicaPoint, northEastPixelPoint));
        }

        
        // Get the tile to the south west of the current position.
        Point southWestLogicalPoint = new Point(tile.logicalPoint);
        southWestLogicalPoint.translate(-1, 1);
        
        Point southWestPixelPoint = new Point(tile.pixelPoint);
        southWestPixelPoint.translate(-horizDistanceBtwnTiles, vertDistanceBtwnTiles / 2);

        Tile southWestTile = map.getTileAt(southWestLogicalPoint);
        if(southWestTile != null){
            adjacentTiles.add(new TileNode(southWestTile,southWestLogicalPoint, southWestPixelPoint));
        }

        return adjacentTiles;
    }
    
    // This determines of the image desired to be drawn is within the visible portion of the area viewport.
    private boolean isInRangeOfViewport(Point p){
        // Check if the x coordinate is in range
        if(p.getX() + hexWidth/2 < 0 || p.getX() - hexWidth/2 > viewportWidth){
            return false;
        }

        if(p.getY() + hexHeight/2 < 0 || p.getY() - hexHeight/2 > viewportHeight){
            return false;
        }
        return true;
    }

    public Polygon getHexTile(Point center){

        Polygon hex = new Polygon();
        // Add a point for each vertex on the hex
        for(int i=0; i<6; i++){
            int angleDeg = 60 * i;
            double angleRad = Math.PI / 180 * angleDeg;
            int x = (int) (center.getX() + hexSize * Math.cos(angleRad));
            int y = (int) (center.getY() + hexSize * Math.sin(angleRad));
            hex.addPoint(x, y);
        }
        return hex;
    }

    public void toggleDebugInformation() {

        displayDebugInformation = !displayDebugInformation;

    }

    @Override
    public void scaleView() {
        viewportHeight = getScreenHeight();
        viewportWidth = getScreenWidth();
        cachedViewport = new BufferedImage(viewportWidth, viewportHeight, BufferedImage.TYPE_INT_ARGB);
        reRender = true;
    }

    public void setAvatar(Avatar a) {
        this.avatar = a;
    }

    // This is a simple data stortage class used for the traversing tile and rendering.
    class TileNode{
        public Tile tile;
        public Point logicalPoint;
        public Point pixelPoint;
        public int distanceFromAvatar;

        public TileNode(Tile tile, Point logicalPoint, Point pixelPoint){
            this.tile = tile;
            this.logicalPoint = logicalPoint;
            this.pixelPoint = pixelPoint;
            this.distanceFromAvatar = -1;
        }
    }
}
