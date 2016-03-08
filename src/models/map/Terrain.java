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

    public Image getImage(){
        String filePath = IOUtilities.getFileSystemDependentPath("./src/res/terrain/" + type + ".png");
        return IOUtilities.getImageIcon(filePath).getImage();
    }

    @Override
    public boolean equals(Object o) {

        System.out.println("EQUALS");
        System.out.println(this.getType());

        if (o instanceof Terrain) {

            Terrain otherTerrain = (Terrain) o;

            System.out.println(this.getType() + " = " + otherTerrain.getType());
            return this.getType().equals(otherTerrain.getType());

        }

        return false;

    }

}
