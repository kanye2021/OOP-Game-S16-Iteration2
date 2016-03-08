package views.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by aseber on 3/7/16.
 */
public class DecoratedSprite extends Sprite {

    private BufferedImage newImage;
    HashSet<Color> colors = new HashSet<>();

    public DecoratedSprite(String imagePath, HashMap<Color, Color> colorMap) {

        super(imagePath);
        getColors();

        for (java.util.Map.Entry<Color, Color> entry : colorMap.entrySet()) {

            setColor(entry.getKey(), entry.getValue());

        }

    }

    public void printColors() {

        for (Color color : colors) {

            System.out.println(color);

        }

    }

    private void setColor(Color oldColor, Color newColor) {

        for (int i = 0; i < newImage.getWidth(); ++i) {

            for (int j = 0; j < newImage.getHeight(); ++j) {

                Color currentColor = new Color(newImage.getRGB(i, j));

                if (currentColor.equals(oldColor)) {

                    newImage.setRGB(i, j, newColor.getRGB());

                }

            }

        }

    }

    private void getColors() {

        newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
        newImage.getGraphics().drawImage(image, 0, 0, null);

        for (int i = 0; i < newImage.getWidth(); ++i) {

            for (int j = 0; j < newImage.getHeight(); ++j) {

                colors.add(new Color(newImage.getRGB(i, j)));

            }

        }

    }

    @Override
    public Image getImage() {

        return newImage;

    }

}
