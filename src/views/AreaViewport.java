package views;

import models.entities.Avatar;
import models.entities.Entity;
import models.map.Map;
import models.map.Terrain;
import models.map.Tile;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Bradley on 2/27/16.
 */
public class AreaViewport extends View implements Observer {

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


    public AreaViewport(int width, int height, Display display, Map map, Avatar avatar){
        super(width, height, display);

        this.map = map;
        this.avatar = avatar;
        avatar.addObserver(this);
        scaleView(width, height);
    }

    @Override
    public void render(Graphics g) {

        // Draw a black background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,viewportWidth, viewportHeight);


        // Get the avatar's location. This location will be shown in the center of the viewport.
        Point logicalPoint = avatar.getLocation();
        Point pixelPoint = new Point(viewportWidth/2, viewportHeight/2);

        // The map is rendered recursively starting from the center point. We then recurse through every value of x,
        // and for each value of x, we make sure to visit every value of y.
        renderRecursiveX(new Point(logicalPoint), new Point(pixelPoint), 1, g);
        renderRecursiveX(new Point(logicalPoint), new Point(pixelPoint), -1, g);
    }


    // This determines of the image desired to be drawn is within the visible portion of the area viewport.
    private boolean isInRangeOfViewport(Point p){
        // Check if the x coordinate is in range
        if(p.getX() + hexWidth/2 < 0 || p.getX() - hexWidth/2 > viewportWidth){
            return false;
        }
        // TODO: This is kinda bad (we are no longer checking vertical bounds of the viewport). Because of the way things
        // are being rendered, stopping when the Y value is out of bounds would result in the sides being cut off if the viewport
        // is wider than it is tall.
//        if(p.getY() + hexHeight/2 < 0 || p.getY() - hexHeight/2 > viewportHeight){
//            return false;
//        }
        return true;
    }

    // This will traverse the map keeping y fixed, and moving in the direction of x specified by sign.
    private void renderRecursiveX(Point logicalPoint, Point pixelPoint, int sign, Graphics g){
        // Make sure that the point exists and that it is in range of the map
        if(map.getTileAt(logicalPoint) == null || !isInRangeOfViewport(pixelPoint)){
            return;
        }


        // Call recursive functions to traverse y values. This will traverse every value of y now, keeping x constant.
        renderRecursiveY(new Point(logicalPoint), new Point(pixelPoint), 1, g);
        renderRecursiveY(new Point(logicalPoint), new Point(pixelPoint), -1, g);

        logicalPoint.translate(sign, 0);
        pixelPoint.translate(sign * horizDistanceBtwnTiles, sign * vertDistanceBtwnTiles /2);

        renderRecursiveX(logicalPoint, pixelPoint, sign, g);
    }

    // This is the function that will be called for every tile that is to be displayed. It is the result of recursively
    // Traversing throgh all the values of x, and for each value of x, traversing through all values of y.
    private void renderRecursiveY(Point logicalPoint, Point pixelPoint, int sign, Graphics g ){

        Tile tile = map.getTileAt(logicalPoint);
        if(tile == null || !isInRangeOfViewport(pixelPoint)){
            return;
        }

        // Do the actual Drawing here!
        Polygon tilePolygon = getHexTile(pixelPoint);
        g.setColor(Color.BLACK);
        g.drawPolygon(tilePolygon); // This part kinda helps the tiles come together. Due to the math involved in rendering
                                    // The hex tiles, there are a few points where we have to cast to an int and lose precision.

        // Get the old clip (Should be the entire window).
        Shape oldClip = g.getClip();

        // Set the clip to just the hex tile
        g.setClip(tilePolygon);

        // Draw the terrain
        Terrain terrain = tile.getTerrain();
        Image terrainImage = terrain.getImage();

        int terrainX = (int)(pixelPoint.getX() - hexWidth/2);
        int terrainY = (int)(pixelPoint.getY()) - hexHeight/2;
        g.drawImage(terrainImage, terrainX, terrainY, hexWidth, hexHeight, getDisplay());


        // TODO: Implement items and areaEffects / Decals
//        // Draw the items
//        Item item = tile.getItem();
//        if(item!=null){
//            Image itemImage = item.getImage();
//            itemImage = itemImage.getScaledInstance(hexSize, hexSize, 0); // TODO SEE WHAT THE LAST PARAMETER IS WHEN YOU HAVE WIFI
//
//            int itemX = (int)(pixelPoint.getX() - itemImage.getWidth(null) /2);
//            int itemY = (int)(pixelPoint.getY() - itemImage.getHeight(null) /2);
//            g.drawImage(itemImage, itemX, itemY, getDisplay());
//        }

        // Display entities on the map
        Entity entity = tile.getEntity();
        if(entity != null){
            Image entityImage = entity.getImage();
//            entityImage = entityImage.getScaledInstance(hexSize, hexSize, 0); // TODO SEE WHAT THE LAST PARAMETER IS WHEN YOU HAVE WIFI

            // Resize the entity image
            int scaledWidth = hexWidth * 3/4;
            int scaledHeight = hexHeight * 3/4;

            int entityX = (int)(pixelPoint.getX() - scaledWidth / 2);
            int entityY = (int)(pixelPoint.getY() - scaledHeight / 2);
            g.drawImage(entityImage, entityX, entityY, scaledWidth, scaledHeight, getDisplay());
        }

        // Return the clip to normal
        g.setClip(oldClip);

        // Calculate the next logical, and pixel point to draw.
        logicalPoint.translate(0, sign);
        pixelPoint.translate(0, sign * vertDistanceBtwnTiles);

        // Recurse!
        renderRecursiveY(logicalPoint, pixelPoint, sign, g);
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

    @Override
    public void scaleView() {
//        viewportHeight = getScreenHeight() * 4/5;
//        viewportWidth = getScreenWidth();
//        hexSize = (int) (viewportWidth * .02);
//        hexWidth = hexSize * 2;
//        hexHeight = (int) (Math.sqrt(3)/2 * hexWidth);
//        horizDistanceBtwnTiles = hexSize * 3 /2;
//        vertDistanceBtwnTiles = (int) (hexSize * Math.sqrt(3));

    }

    // I had to overload this method because for some reason, getScreenWidth() and height would not give the correct values.
    // This was really wierd and I have no idea why.
    public void scaleView(int screenWidth, int screenHeight){
        viewportHeight = screenHeight * 4/5;
        viewportWidth = screenWidth;
        hexSize = 23;
        hexWidth = hexSize * 2;
        hexHeight = Math.round((float)(Math.sqrt(3) /2 * hexWidth));
        horizDistanceBtwnTiles = hexSize * 3 /2;
        vertDistanceBtwnTiles = Math.round((float)(hexSize * Math.sqrt(3)));
    }


    @Override
    public void update(Observable o, Object arg) {
        getDisplay().repaint();
    }
}
