package models.map;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by Bradley on 2/26/2016.
 */
public class Terrain {
    public String type;

    public Terrain(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPathToFile(){
        return type + ".png";
    }

    public Image getImage(){
        String filePath = IOUtilities.getFileSystemDependentPath("./src/res/terrain/" + type + ".png");
        return IOUtilities.getImageIcon(filePath).getImage();
    }
}
