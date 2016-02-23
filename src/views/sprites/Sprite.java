package views.sprites;

import utilities.IOUtilities;

import javax.swing.*;

/**
 * Created by aseber on 2/22/16.
 */
public class Sprite {

    ImageIcon image;

    public Sprite(String imagePath) {

        image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(imagePath));

    }

    public ImageIcon getImage() {

        return image;

    }

}
