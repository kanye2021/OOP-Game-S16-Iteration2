package views;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/18/16.
 */

public class LoadGameView extends View {
    //Properties
    ArrayList<File> listOfSaveFiles;
    int selectedOption;
    //Scalable variables for resizing
    private int buttonWidth;
    private int buttonHeight;
    private int titleButtonMargin;
    private Font titleFont;
    private Font buttonFont;

    public LoadGameView(int width, int height, Display display) {
        super(width, height, display);
        selectedOption = 0;
    }

    public void render(Graphics g) {
        if (listOfSaveFiles.isEmpty()) {
            renderTitle(g, false); //Changes the message of the title to no save files present
        } else {
            renderTitle(g, true);
            renderSaveFiles(g);
        }
    }

    public void scaleView() { //Remainder that this is called from class view
        titleFont = new Font("Courier New", Font.PLAIN, getScreenWidth() / 19);
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;
        buttonFont = new Font("Helvetica", Font.BOLD, getScreenWidth() / 86);
        titleButtonMargin = (int) (getScreenHeight() * 0.15);

    }

    public void renderTitle(Graphics g, boolean hasTitle) {
        // Draw title
        FontMetrics fm = g.getFontMetrics(titleFont);
        g.setFont(titleFont);
        g.setColor(Color.white);
        String titleString = "Select file";
        if (!hasTitle) {
            titleString = "No games found!";
        }
        Rectangle2D rec = fm.getStringBounds(titleString, g);
        int xTitle = (getScreenWidth() / 2 - (int) (rec.getWidth() / 2));
        int yTitle = getScreenHeight() / 6 + (int) (rec.getHeight() / 2);
        g.drawString(titleString, xTitle, yTitle);
    }

    public void renderSaveFiles(Graphics g) {
        int START_FILES = g.getFontMetrics(titleFont).getHeight() + titleButtonMargin;
        FontMetrics fm = g.getFontMetrics(buttonFont);
        g.setFont(buttonFont);
        Color primaryColor;
        Color secondaryColor;
        //Displays all the buttons for the files found
        for (int i = 0; i < listOfSaveFiles.size(); i++) {
            File file = listOfSaveFiles.get(i);
            String fileName = file.getName();
            Rectangle2D rectangle = fm.getStringBounds(fileName, g);

            int boxX = getScreenWidth() / 2 - buttonWidth / 2;
            int boxY = buttonHeight * i + START_FILES;
            int boxDX = buttonWidth;
            int boxDY = buttonHeight;
            int stringX = getScreenWidth() / 2 - (int) (rectangle.getWidth() / 2);
            int stringY = i * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent() + START_FILES;

            if (i == selectedOption) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;

            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;

            }
            g.setColor(primaryColor);
            g.fillRect(boxX, boxY, boxDX, boxDY);
            g.setColor(secondaryColor);
            g.drawString(fileName, stringX, stringY);
        }

    }

    //Called by the view controller
    public void updateSaveFiles(ArrayList<File> newList) {
        listOfSaveFiles = newList;
    }

    public void updateOption(int newOption) {
        selectedOption = newOption;
    }

}
