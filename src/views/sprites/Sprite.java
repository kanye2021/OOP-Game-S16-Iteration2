package views.sprites;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by aseber on 2/22/16.
 */
public class Sprite {

    protected Image image;

    public Sprite(String imagePath) {
        //System.out.println("SPRITE IMAGE PATH" + imagePath);
        image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(imagePath)).getImage();

    }

    public Image getImage() {

        return image;

    }

}
