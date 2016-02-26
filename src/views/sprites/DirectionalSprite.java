package views.sprites;

import models.map.Map;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class DirectionalSprite {

    HashMap<Map.Direction, ImageIcon> images;

    public DirectionalSprite(HashMap<Map.Direction, String> imagePaths) {

        ImageIcon image;

//        for (Map.Direction direction : imagePaths.keySet()) {
//
//            String imagePath = IOUtilities.getFileSystemDependentPath(imagePaths.get(direction));
//            image = IOUtilities.getImageIcon(imagePath);
//            images.put(direction, image);
//
//        }

    }

    public ImageIcon getImage(Map.Direction direction) {

        return images.get(direction);

    }

}
