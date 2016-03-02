package views;

import models.entities.npc.Action;
import models.entities.npc.NPC;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/28/16.
 */
public class NPCActionView extends View{
    //Scalable variables
    private int actionView_Width ;
    private int actionView_Height;
    private int actionView_Start_X;
    private int actionView_Start_Y;
    private Font buttonFont;
    private int buttonWidth;
    private int buttonHeight;

    int selectedOption;
    //Actual Data to modify with
    private NPC npc;

    public NPCActionView(int width, int height, Display display, NPC npc){
        super(width,height, display);
        this.npc = npc;
        scaleView(width,height);
        selectedOption = 0;
    }

    @Override
    public void render(Graphics g) {
        renderTitle(g);
        renderOptions(g);
    }
    @Override
    public void scaleView(){
//Doesn't do jack (cause its a viewport)
    }

    public void scaleView(int screenWidth, int screenHeight) {

        actionView_Width = (getScreenWidth() / 6);
        //System.out.println("Action View: " + actionView_Width);
        actionView_Height = (int) (getScreenHeight() * 0.5);
        actionView_Start_Y = 0;
        actionView_Start_X = getScreenWidth() - actionView_Width;

        buttonFont = new Font("Helvetica", Font.BOLD, getScreenWidth() / 86);
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;

    }
    public void renderTitle(Graphics g){
        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(actionView_Start_X, actionView_Start_Y, actionView_Width, actionView_Height);
    }
    public void renderOptions(Graphics g){
        ArrayList<Action> npcActions = npc.getActionList();
        int i = 0;
        Color primaryColor;
        Color secondaryColor;
        for (Action a : npcActions) {
            String actionName = a.getName();
            FontMetrics fm = g.getFontMetrics(buttonFont);

            Rectangle2D rectangle = fm.getStringBounds(actionName, g);

            int boxX = actionView_Start_X; //getScreenWidth() / 2 - buttonWidth / 2;
            int boxY = buttonHeight * i;
            int boxDX = buttonWidth;
            int boxDY = buttonHeight;
            int stringX = boxX + (int) (rectangle.getWidth() / 2);
            int stringY = i * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent();

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
            g.drawString(actionName, stringX, stringY);
            i++;
        }
    }
    public void updateSelectedOption(int sel){
        selectedOption = sel;
    }

}
