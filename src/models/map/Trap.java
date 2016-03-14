package models.map;

import utilities.IOUtilities;
import utilities.MathUtilities;

import java.awt.*;

/**
 * Created by ben on 3/5/16.
 */
public class Trap {

    private final int normalDamage = 5;
    public String type;
    private int trapDamage;
    private boolean hidden;

    public Trap(String type) {
        this.type = type;
        hidden = true;
        trapDamage = MathUtilities.putInRange(1, normalDamage, 9);
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getType() {
        return type;
    }

    public Image getImage() {
        String filePath = IOUtilities.getFileSystemDependentPath("./src/res/terrain/" + type + ".png");
        return IOUtilities.getImageIcon(filePath).getImage();
    }
}
