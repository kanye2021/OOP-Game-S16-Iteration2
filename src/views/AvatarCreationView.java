package views;

import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class AvatarCreationView extends View {

    public enum OccupationOptions {
        SMASHER("Smasher", "Specialized in hand-to-hand combat"),
        SUMMONER("Summoner", "Specialized in spell-casting"),
        SNEAK("Sneak", "Specialized in ranged weapons, evading detection, finding/removing traps");

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
    private final String AVATAR_CREATE_TEXT = "Please select an occupation";
    private String arrowFilePath;


    // Scalable variables.
    private int buttonWidth;
    private int buttonHeight;
    private int arrowWidth;
    private int arrowHeight;

    // Styling properties
    private Font smallFont;
    private Font titleFont;

    // Data Options
    private OccupationOptions selected;

    public AvatarCreationView(int width, int height, Display display){
        super(width, height, display);
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
        // Scale font
        int titleFontSize = getScreenWidth() / 43;
        titleFont = new Font("Courier New", Font.BOLD, titleFontSize);
        int smallFontSize = getScreenWidth() / 85;
        smallFont = new Font("Helvetica", Font.BOLD, smallFontSize);
        arrowWidth = getScreenWidth()/24;
        arrowHeight = (int)(((double)getScreenHeight())/22.6);
    }


    @Override
    public void render(Graphics g) {

        // Title Text
        FontMetrics fm = g.getFontMetrics(titleFont);
        g.setColor(Color.white);
        g.setFont(titleFont);
        Rectangle2D r1 = fm.getStringBounds(AVATAR_CREATE_TEXT, g);
        int x = (getScreenWidth() - (int) r1.getWidth()) / 2;
        int y = (getScreenHeight() - (int) r1.getHeight()) / 7 + fm.getAscent();
        g.drawString(AVATAR_CREATE_TEXT, x, y);


        // Paint occupation Titles + Descriptions
        int stringX = getScreenWidth()/12;
        int arrow_x_offset = stringX;
        int stringY = getScreenHeight()/3;
        int stringY2 = 0;
        int prevY = 0;
        // Loop through menu options and display on screen
        for (OccupationOptions occupation : OccupationOptions.values()) {
            fm = g.getFontMetrics(titleFont);
            Rectangle2D rectangle1 = fm.getStringBounds(occupation.getText(), g);
            fm = g.getFontMetrics(smallFont);
            Rectangle2D rectangle2 = fm.getStringBounds(occupation.getDescription(), g);

            stringY = prevY != 0 ? prevY + ((int) rectangle2.getHeight()) + getScreenHeight()/16 : stringY;
            stringY2 = ((int) (rectangle1.getHeight()) + fm.getAscent()) + stringY;

            Color primaryColor = Color.white;

            if (occupation == getSelected()) {
                // Drawing Arrow next to selection
                ImageIcon ii = new ImageIcon(arrowFilePath);
                Image arrow = ii.getImage();
                arrow_x_offset -= arrowWidth + getScreenWidth()/120;
                g.drawImage(arrow, arrow_x_offset, stringY, arrowWidth, arrowHeight, null);
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
