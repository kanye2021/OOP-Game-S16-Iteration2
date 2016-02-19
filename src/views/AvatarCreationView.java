package views;

import controllers.AvatarCreationViewController;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class AvatarCreationView extends View {

    public enum OccupationOptions {
        SMASHER("Smasher", "specialized in hand-to-hand combat"),
        SUMMONER("Summoner", "specialized in spell-casting"),
        SNEAK("Sneak", "specialized in ranged weapons, evading detection, finding/removing traps");

        private String text;
        private String description;

        private OccupationOptions(String text, String description) {
            this.text = text;
            this.description = description;
        }
        public String getText() {
            return text;
        }
        public String getDescription() {
            return description;
        }

    }
    // Constants
    private final String AVATAR_CREATE_TEXT = "Please select an Occupation";
    private String arrowFilePath;


    // Scalable variables.
    private int buttonWidth;
    private int buttonHeight;

    // Styling properties
    private Font smallFont;
    private Font titleFont;

    // Data Options
    private OccupationOptions selected;

    public AvatarCreationView(int width, int height){
        super(width, height);
        selected = OccupationOptions.SMASHER;
        arrowFilePath = "./src/res/arrow.png";
        arrowFilePath = IOUtilities.getFileSystemDependentPath(arrowFilePath);

    }

    public OccupationOptions getSelected() {
        return selected;
    }

    public void previousOption() {
        if (this.selected.ordinal() == 0) {
            selected =  OccupationOptions.values()[OccupationOptions.values().length - 1];
        } else {
            selected = OccupationOptions.values()[selected.ordinal() - 1];
        }
    }
    public void nextOption() {
        if (this.selected.ordinal() == OccupationOptions.values().length - 1) {
            selected = OccupationOptions.values()[0];
        } else {
            selected = OccupationOptions.values()[selected.ordinal() + 1];
        }
    }

    @Override
    public void scaleView(){
        // Scale buttons
//        buttonWidth = getScreenWidth() / 6;
//        buttonHeight = getScreenHeight() / 25;

        // Scale font
        int titleFontSize = getScreenWidth() / 43;
        titleFont = new Font("Brush Script MT", Font.BOLD, titleFontSize);
        int smallFontSize = getScreenWidth() / 85;
        smallFont = new Font("Helvetica", Font.BOLD, smallFontSize);

//        titleButtonMargin = (int) (getScreenHeight() * 0.15);
    }


    @Override
    public void render(Graphics g) {

        // Text

        FontMetrics fm = g.getFontMetrics(titleFont);
        g.setColor(Color.white);
        g.setFont(titleFont);
        Rectangle2D r1 = fm.getStringBounds(AVATAR_CREATE_TEXT, g);
        int x = (getScreenWidth() - (int) r1.getWidth()) / 2;
        int y = (getScreenHeight() - (int) r1.getHeight()) / 7 + fm.getAscent();
        g.drawString(AVATAR_CREATE_TEXT, x, y);


        // Paint Occupation Titles + Descriptions
        int stringX = 100;
        int arrow_x_offset = stringX;
        int stringY = getScreenHeight() / 3;
        int stringY2 = 0;
        int prevY = 0;
        for (OccupationOptions occupation : OccupationOptions.values()) {

            fm = g.getFontMetrics(titleFont);
            Rectangle2D rectangle1 = fm.getStringBounds(occupation.getText(), g);
            fm = g.getFontMetrics(smallFont);
            Rectangle2D rectangle2 = fm.getStringBounds(occupation.getDescription(), g);


            stringY = prevY != 0 ? prevY + ((int) rectangle2.getHeight()) + 50 : stringY;
            stringY2 = ((int) (rectangle1.getHeight()) + fm.getAscent()) + stringY;

            Color primaryColor = Color.white;

            if (occupation == getSelected()) {
                // Drawing Arrow next to selection
                ImageIcon ii = new ImageIcon(arrowFilePath);
                Image arrow = ii.getImage();
                arrow_x_offset -= arrow.getWidth(null) + 10;
                g.drawImage(arrow, arrow_x_offset, stringY, null);
            }

            g.setColor(primaryColor);
            g.setFont(titleFont);
            g.drawString(occupation.getText(), stringX, stringY);
            g.setFont(smallFont);
            g.drawString(occupation.getDescription(), stringX, stringY2);

            prevY = stringY2;


        }

        Toolkit.getDefaultToolkit().sync();

    }
}
