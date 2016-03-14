package models.map;

import models.entities.npc.Mount;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 3/10/2016.
 */
public class TileImage extends BufferedImage {

    Graphics2D g;

    public TileImage(int width, int height, int imageType) {
        super(width, height, imageType);

        g = this.createGraphics();
    }

    public static TileImage copyImage(TileImage tileImage){
        TileImage b = new TileImage(tileImage.getWidth(), tileImage.getHeight(), tileImage.getType());
        Graphics g = b.getGraphics();
        g.drawImage(tileImage, 0, 0, null);
        g.dispose();
        return b;
    }

    public void generate(Tile tile){

        // Clear the Image
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());

        // Draw the terrain
        Image terrain = tile.getTerrainImage();
        g.drawImage(terrain, 0, 0, getWidth(), getHeight(), null);

        // Draw the items
        Image item = tile.getItemImage();
        if(item!=null){
            g.drawImage(item, 0, 0, getWidth(), getHeight(), null);
        }

        // Draw the decals
        Image decal = tile.getDecalImage();
        if(decal!=null){
            int width = getWidth() * 3/5;
            int height = getHeight() * 3/5;
            int x = getWidth()/2 - width/2;
            int y = getHeight()/2 - height/2;
            g.drawImage(decal, x, y, width, height, null);
        }

        Image projectile = tile.getProjectileImage();
        if(projectile!=null){
            int width = getWidth() * 3/4;
            int height = getHeight() * 3/4;
            int x = getWidth()/2 - width/2;
            int y = getHeight()/2 - height/2;

            g.drawImage(projectile,x,y,width,height,null);
        }


        // Draw the Entity
        Image entity = tile.getEntityImage();
        if(entity!=null){
            int width = getWidth() * 3/4;
            int height = getHeight() * 3/4;
            int x = getWidth()/2 - width/2;
            int y = getHeight()/2 - height/2;

            // TODO: I don't like that I have to do this....
            Mount m = tile.getEntity().getMount();
            if(m!=null){
                g.drawImage(m.getImage(), x, y, width, height, null);

                width *= 0.50;
                height *= 0.50;
                x = getWidth()/2 - width/2;
                y = getHeight()/2 - height/2;

                g.drawImage(entity, x, y, width, height, null);
            }else{
                g.drawImage(entity, x, y, width, height, null);
            }
        }
    }
}
