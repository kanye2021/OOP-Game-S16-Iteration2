package views.sprites;

import models.map.Map;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class DirectionalSprite {

    HashMap<Map.Direction, Image> images;

    public DirectionalSprite(HashMap<Map.Direction, String> imagePaths) {

        images = new HashMap<>();

        Image image;

        for (Map.Direction direction : imagePaths.keySet()) {

            String imagePath = IOUtilities.getFileSystemDependentPath(imagePaths.get(direction));
            image = IOUtilities.getImageIcon(imagePath).getImage();
            images.put(direction, image);

        }

    }

    public Image getImage(Map.Direction direction) {

        return images.get(direction);

    }

}
