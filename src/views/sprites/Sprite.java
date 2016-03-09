package views.sprites;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public class Sprite implements Drawable {

    private Image image;

    public Sprite(String imagePath) {

        image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(imagePath)).getImage();

    }

    public Image getImage() {

        return image;

    }

}
