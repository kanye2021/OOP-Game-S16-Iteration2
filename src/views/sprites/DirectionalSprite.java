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

    public void addImage(NavigationMediator.Direction direction, String imagePath) {

        images.put(direction, IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(imagePath)));

    }

    public ImageIcon getImage(NavigationMediator.Direction direction) {

        return images.get(direction);

    }

}
