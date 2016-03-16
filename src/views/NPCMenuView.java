package views;

import models.entities.characters.npcs.NPC;
import models.entities.characters.npcs.actions.Action;
import utilities.IOUtilities;
import utilities.SubState;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/28/16.
 */
public class NPCMenuView extends View {
    private final String RESOURCE_BASE_PATH = IOUtilities.getFileSystemDependentPath("./src/res/etc/");
    int selectedOption;
    //Scalable variables
    private int actionView_Width;
    private int actionView_Height;
    private int actionView_Start_X;
    private int actionView_Start_Y;
    private Font buttonFont;
    private int buttonWidth;
    private int buttonHeight;
    //Actual Data to modify with
    private NPC npc;
    private ArrayList<SubState> substates;

    public NPCMenuView(int width, int height, Display display, NPC npc) {
        super(width, height, display);
        this.npc = npc;
        selectedOption = 0;
        substates = new ArrayList<>();
    }

    @Override
    public void render(Graphics g) {
        if (substates.isEmpty()) {
            //Renders the base Menu view
            renderTitle(g);
            renderOptions(g);
        } else {
            substates.get(0).render(g); //Renders the very first substate only
        }
    }

    @Override
    public void scaleView() {

        actionView_Width = (getScreenWidth() / 6);
        //System.out.println("Action View: " + actionView_Width);
        actionView_Height = (int) (getScreenHeight() * 0.5);
//        actionView_Start_Y = (int) (getScreenHeight() * 0.3);
//        actionView_Start_X = getScreenWidth()/2;
        actionView_Start_Y = 0;
        actionView_Start_X = getScreenWidth() - actionView_Width;

        buttonFont = new Font("Helvetica", Font.BOLD, getScreenWidth() / 86);
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;
        if (substates != null) {
            for (SubState s : substates) {
                s.scaleView(); //scales every view in substate
            }
        }

    }

    public void renderTitle(Graphics g) {
        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(actionView_Start_X, actionView_Start_Y, actionView_Width, actionView_Height);
        //Image statsParchment = IOUtilities.getImageIcon(RESOURCE_BASE_PATH + "smaller_parchment.png").getImage();
        //Switched the width and height since it looks better
        //int startX = actionView_Start_X + actionView_Width/2;
        //g.drawImage(statsParchment,actionView_Start_X, actionView_Start_Y, actionView_Height, actionView_Width, getDisplay());
    }

    public void renderOptions(Graphics g) {
        ArrayList<Action> npcActions = npc.getActionList();
        int i = 0;
        Color primaryColor;
        Color secondaryColor;
        int boxX = actionView_Start_X;
        int boxY;
//        int boxWidth = buttonWidth;
//        int boxHeight = buttonHeight;
        FontMetrics fm = g.getFontMetrics(buttonFont);

        ArrayList<String> menuOptions = new ArrayList<>();
        for (int j = 0; j < npcActions.size(); j++) {
            menuOptions.add(npcActions.get(j).getName());
        }
        menuOptions.add("Exit");

        for (String optionName : menuOptions) {
            Rectangle2D rectangle = fm.getStringBounds(optionName, g);

            boxY = buttonHeight * i + actionView_Start_Y;

            int stringX = boxX + (int) (rectangle.getWidth() / 2);
            int stringY = boxY + (int) (rectangle.getHeight() / 2) + fm.getAscent();

            if (i == selectedOption) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;

            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;

            }
            g.setColor(primaryColor);
            g.fillRect(boxX, boxY, buttonWidth, buttonHeight);
            g.setColor(secondaryColor);
            g.drawString(optionName, stringX, stringY);
            i++;
        }

    }

    public void updateSelectedOption(int sel) {
        selectedOption = sel;
    }

    public void addSubState(SubState s) {
        //s.setParent(this);
        this.substates.add(s);
    }

    public void insertSubState(SubState s, int index) {
        //s.setParent(this);
        this.substates.add(index, s);
    }

    public void removeSubState(SubState s) {
        this.substates.remove(s);
    }
}
