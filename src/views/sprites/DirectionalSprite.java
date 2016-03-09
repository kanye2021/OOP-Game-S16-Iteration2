package views.sprites;

import models.map.Map;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class DirectionalSprite implements Drawable {

    private HashMap<Map.Direction, Sprite> images;
    private Map.Direction direction = Map.Direction.NORTH;

    public DirectionalSprite(HashMap<Map.Direction, String> imagePaths) {

        images = new HashMap<>();

        for (Map.Direction direction : imagePaths.keySet()) {

            images.put(direction, new Sprite(imagePaths.get(direction)));

        }

    }

    public void setDirection(Map.Direction direction) {

        this.direction = direction;

    }

    public Image getImage() {

        return images.get(direction).getImage();

    }

}
