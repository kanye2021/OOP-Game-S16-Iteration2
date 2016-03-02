package views;

import controllers.SaveGameViewController;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by dyeung on 2/18/16.
 */
public class SaveGameView extends View {
    //Scalable variables
    private int buttonWidth;
    private int buttonHeight;
    private int heightOffset;
    private Font buttonFont;

    // Data properties
    SaveGameViewController.SaveOptions selected;
    public SaveGameView(int width, int height){
        super(width, height);
    }
    public void render(Graphics g){
        renderTitle(g);
        renderButtons(g);
    }
    public void scaleView(){ //Reminder that this is called by view initially
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;
        heightOffset = getScreenHeight()/6;
        buttonFont = new Font("Helvetica", Font.BOLD, getScreenWidth()/85);
    }
    public void renderTitle(Graphics g){

    }
    public void renderButtons(Graphics g){
        FontMetrics fm = g.getFontMetrics(buttonFont);
        g.setFont(buttonFont);
        int fraction = 1;
        for (SaveGameViewController.SaveOptions option : SaveGameViewController.SaveOptions.values()) {

            //Box Stuff
            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);
            int boxX = getScreenWidth() / 2 - buttonWidth / 2;
            int boxY = heightOffset + ((fraction)* getScreenHeight()/6 ) - buttonHeight/2;
            int boxDX = buttonWidth;
            int boxDY = buttonHeight;

            // String stuff
            Rectangle2D r = fm.getStringBounds(option.toString(), g);
            int stringX = getScreenWidth() / 2 - (int) (r.getWidth() / 2);
            int stringY = boxY + (int) (r.getHeight()) + fm.getAscent();

            Color primaryColor;
            Color secondaryColor;

            if (option == selected) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;
            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;
            }

            g.setColor(primaryColor);
            g.fillRect(boxX, boxY, boxDX, boxDY);
            g.setColor(secondaryColor);
            g.drawString(option.toString(), stringX, stringY);
            g.drawRect(boxX, boxY, boxDX, boxDY);

            fraction+=1;

        }

       // Toolkit.getDefaultToolkit().sync(); //Not sure if this is necessary
        //java docs said "This method ensures that the display is up-to-date. It is useful for animation."
    }
    public void setSelected(SaveGameViewController.SaveOptions so){
        selected = so;
    }
}
