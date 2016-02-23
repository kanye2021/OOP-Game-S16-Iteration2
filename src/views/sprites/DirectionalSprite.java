package views.sprites;

import models.NavigationMediator;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by aseber on 2/22/16.
 */
public class DirectionalSprite {

    HashMap<NavigationMediator.Direction, ImageIcon> images;

    public DirectionalSprite(HashMap<NavigationMediator.Direction, String> imagePaths) {

        ImageIcon image;

        for (NavigationMediator.Direction direction : imagePaths.keySet()) {

            String imagePath = IOUtilities.getFileSystemDependentPath(imagePaths.get(direction));
            image = IOUtilities.getImageIcon(imagePath);
            images.put(direction, image);

        }

    }

    public ImageIcon getImage(NavigationMediator.Direction direction) {

        return images.get(direction);

    }

}
