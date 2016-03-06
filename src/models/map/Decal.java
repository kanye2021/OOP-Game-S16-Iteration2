package models.map;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by Bradley on 2/26/2016.
 */
public class Decal {

    public enum Types {
        RED_CROSS("red-cross.png"),
        SKULL_CROSSBONES("skull-and-crossbones.png"),
        GOLD_STAR("gold-star.png"),
        TELEPORT("teleport-sample.png");

        String pathToFile;
        final String rootPath = "./src/res/decals/";

        Types(String pathToFile) { this.pathToFile = rootPath + pathToFile; }

        public String getPathToFile() { return pathToFile; }
    }

    private int id;
    private Image image;

    public Decal(Types type) {
        id = type.ordinal();
        image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(getPathToFile())).getImage();

    }

    public Image getImage() { return image; }
    public int getID() { return id; }

    public String getPathToFile() { return Types.values()[getID()].getPathToFile(); }
}
