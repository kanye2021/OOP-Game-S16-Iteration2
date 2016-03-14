package views.sprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by aseber on 3/7/16.
 */
public class DecoratedSprite implements Drawable {

    private BufferedImage image;

    public DecoratedSprite(String imagePath, HashMap<Color, Color> colorMap) {

        Image temporaryImage = new Sprite(imagePath).getImage();
        image = new BufferedImage(temporaryImage.getWidth(null), temporaryImage.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
        image.getGraphics().drawImage(temporaryImage, 0, 0, null);

        for (java.util.Map.Entry<Color, Color> entry : colorMap.entrySet()) {

            setColor(entry.getKey(), entry.getValue());

        }

    }

    private void setColor(Color oldColor, Color newColor) {

        for (int i = 0; i < image.getWidth(); ++i) {

            for (int j = 0; j < image.getHeight(); ++j) {

                Color currentColor = new Color(image.getRGB(i, j));

                if (currentColor.equals(oldColor)) {

                    image.setRGB(i, j, newColor.getRGB());

                }

            }

        }

    }

    public Image getImage() {

        return image;

    }

}
