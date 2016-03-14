package models.map;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by Bradley on 2/26/2016.
 */
public class Decal {

    private int id;
    private Image image;
    private boolean visible;
    public Decal(Types type) {
        id = type.ordinal();
        image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(getPathToFile())).getImage();
        visible = true;
    }

    public Decal(Decal decal) {
        this.id = decal.getID();
        this.image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(decal.getPathToFile())).getImage();
        this.visible = decal.isVisible();
    }

    public Image getImage() {
        return image;
    }

    public int getID() {
        return id;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getPathToFile() {
        return Types.values()[getID()].getPathToFile();
    }

    public enum Types {
        RED_CROSS("red-cross.png"),
        SKULL_CROSSBONES("skull-and-crossbones.png"),
        GOLD_STAR("gold-star.png"),
        TELEPORT("teleport-sample.png"),
        TRAP("trap-sample.png");

        final String rootPath = "./src/res/decals/";
        String pathToFile;

        Types(String pathToFile) {
            this.pathToFile = rootPath + pathToFile;
        }

        public String getPathToFile() {
            return pathToFile;
        }
    }
}
