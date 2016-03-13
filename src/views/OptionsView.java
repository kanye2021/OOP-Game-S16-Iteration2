package views;

import models.skills.ActiveSkill;
import models.skills.Skill;
import models.skills.SkillList;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 3/11/16.
 */
public class OptionsView extends View {


    // Constants
    private final String TITLE = "Set KeyBindings";

    //Title scalable variables
    private int titleStartX;
    private int titleStartY;
    private int titleWidth;
    private int titleHeight;

    //Button Scalable variables.
    private int buttonWidth;
    private int buttonHeight;

    //View  Scalable Variables
    private int optionsViewXStart;
    private int optionsViewYStart;
    private int optionsViewWidth;
    private int optionsViewHeight;

    // Styling properties
    private Font titleFont;
    private Font generalFont;
    private Font instructionFont;
    private int titleButtonMargin;

    // Data properties
    private ArrayList<ActiveSkill> skills;
    private int selected;
    private boolean listeningForNewKeyBind;
    private int indexForNewKeyBind;
    String selectInstructions;
    String keyBindInstructions;
    String leaveInstructions;

    public OptionsView(int width, int height, Display display, SkillList theSkills){
        super(width, height, display);
        selected = 0;
        skills = new ArrayList<ActiveSkill>();
        listeningForNewKeyBind = false;
        indexForNewKeyBind = 0;
        selectInstructions = "Hit [ENTER] on your selected skill to begin keybinding";
        keyBindInstructions = "Press any key to change keybinding. Hit [ENTER] to save";
        leaveInstructions = "[ESC] to leave";

        // init active skill array
        // Only want active skills
        ActiveSkill activeSkill;
        for (Skill skill : theSkills) {
            if (skill.isActive()) {
                activeSkill = (ActiveSkill)skill;
                skills.add(activeSkill);
            }
        }
    }

    public int getSelected() {
        return selected;
    }

    public void previousOption() {
        if (selected == 0) {
            selected = skills.size() - 1;
        } else {
            selected--;
        }
    }
    public void nextOption() {
        if (selected == skills.size() - 1) {
            selected = 0;
        } else {
            selected++;
        }
    }



    @Override
    public void render(Graphics g){

        renderBackground(g);
        renderTitle(g);
        renderButtons(g);
        Toolkit.getDefaultToolkit().sync();
    }


    private void renderBackground(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.lightGray);

        //Opacity stuff
        float opacity = 0.7f;
        AlphaComposite opaque =AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(opaque);
        g2d.fillRect(optionsViewXStart, optionsViewYStart, optionsViewWidth, optionsViewHeight);
    }

    private void renderTitle(Graphics g){
        int titleMargin = 5;

        // Get ready to draw the title
        g.setFont(titleFont);
        FontMetrics fm = g.getFontMetrics(titleFont);
        Rectangle2D titleRect = fm.getStringBounds(TITLE, g);

        // Get the location of the title
        int titleX = titleStartX + titleWidth / 2 - (int) titleRect.getWidth() / 2;
        int titleY = titleStartY + (int) titleRect.getHeight() + titleMargin;

        // Draw the title
        g.setColor(Color.RED);
        g.drawString(TITLE, titleX, titleY);
    }

    private void renderButtons(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();


        //Opacity stuff
        float opacity = 0.0f;
        AlphaComposite opaque =AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);

        //Original Composite
        Composite reset = g2d.getComposite();

        int start = g2d.getFontMetrics(titleFont).getHeight() + titleButtonMargin;

        FontMetrics fm;
        ActiveSkill skill;
        int sidePadding =  optionsViewWidth/20;
        for (int i = 0; i < skills.size(); i++) {
            // Setup basic font
            g2d.setFont(generalFont);
            fm = g2d.getFontMetrics(generalFont);

            // Get dat skill
            skill = skills.get(i);

            // Rectangles r cool 2
            Rectangle2D rectangle = fm.getStringBounds(skill.getName(), g2d);

            // So are boxes
            int boxX = optionsViewXStart + (optionsViewWidth - buttonWidth)/2;
            int boxY = buttonHeight * i + start;
            int boxDX = buttonWidth;
            int boxDY = buttonHeight;

            int stringX = optionsViewXStart + sidePadding;
            int stringY = i * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent() + start;

            Color primaryColor;
            Color secondaryColor;
            Color keyBindHighLight;

            if (i == selected) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.RED;

            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;

            }

            g2d.setColor(primaryColor);
            g2d.setComposite(opaque);
            g2d.fillRect(boxX, boxY, boxDX, boxDY);
            g2d.setColor(secondaryColor);
            g2d.setComposite(reset);
            g2d.drawString(skill.getName(), stringX, stringY);

            if (listeningForNewKeyBind && indexForNewKeyBind == i) {
                keyBindHighLight = Color.yellow;
            } else {
                keyBindHighLight = Color.black;
            }

            // Draw KeyBind Box on the Right
            int keyBindBoxW = (int)(buttonHeight*0.8);
            int keyBindBoxH = (int)(buttonHeight*0.8);
            int keyBindX = optionsViewXStart + optionsViewWidth - sidePadding*2;
            int keyBindY = stringY -keyBindBoxH/2;
            g2d.setColor(keyBindHighLight);
            g2d.drawRect(keyBindX, keyBindY , keyBindBoxW, keyBindBoxH);

            // Draw current KeyBind in dat Box
            g2d.getFont().deriveFont(Font.BOLD);
            fm = g2d.getFontMetrics(generalFont);
            int keyCode = skill.getKeyBind();
            String keyBind = KeyEvent.getKeyText(keyCode);
            rectangle = fm.getStringBounds(keyBind, g);
            keyBindX = keyBindX + (keyBindBoxW - (int)rectangle.getWidth())/2;
            keyBindY = keyBindY + (keyBindBoxH - (int)rectangle.getHeight())/2 + fm.getAscent();
            g2d.drawString(keyBind, keyBindX, keyBindY);

            // Write instructions :~)

            // Setup font
            g2d.setFont(instructionFont);
            fm = g2d.getFontMetrics(instructionFont);

            // Set appropiate instructions based on state
            String currentInstructions;
            if (isListeningForNewKeyBind()) {
                currentInstructions = keyBindInstructions;
            } else currentInstructions = selectInstructions;

            // Get some rectzzzzzz
            rectangle = fm.getStringBounds(currentInstructions, g2d);
            Rectangle2D exitRect = fm.getStringBounds(leaveInstructions, g2d);

            // Get some coordinates & write some strings
            int instructionsStartY = optionsViewYStart + optionsViewHeight - (int)(rectangle.getHeight()*2.5);
            int instructionsX =  optionsViewXStart + (optionsViewWidth - (int)rectangle.getWidth())/2;
            g2d.drawString(currentInstructions, instructionsX, instructionsStartY);

            // Do it agen pls
            int exitX = optionsViewXStart + (optionsViewWidth - (int)exitRect.getWidth())/2;
            int exitY = instructionsStartY + (int)exitRect.getHeight() + fm.getAscent();
            g2d.drawString(leaveInstructions, exitX, exitY);

        }
    }

    public int getIndexForNewKeyBind() {
        return indexForNewKeyBind;
    }

    public void setIndexForNewKeyBind(int indexForNewKeyBind) {
        this.indexForNewKeyBind = indexForNewKeyBind;
    }

    public boolean isListeningForNewKeyBind() {
        return listeningForNewKeyBind;
    }

    public void setListeningForNewKeyBind(boolean listeningForNewKeyBind) {
        this.listeningForNewKeyBind = listeningForNewKeyBind;
    }

    public ActiveSkill getCurrentSelectedSkill() {
        return skills.get(selected);
    }


    @Override
    public void scaleView(){
        //PAUSE VIEW DIMENSIONS
        optionsViewWidth = (int) (getScreenWidth() * 0.5);
        optionsViewHeight = (int) (getScreenHeight() * 0.6);
        optionsViewXStart = (getScreenWidth() - optionsViewWidth)/2;
        optionsViewYStart = (getScreenHeight() - optionsViewHeight)/2;

        //PAUSE TITLE
        titleWidth = (int) (getScreenWidth() * 0.4);
        titleHeight = (int) (getScreenHeight() * 0.1);

        titleStartX = optionsViewXStart + (optionsViewWidth - titleWidth)/2;
        titleStartY = optionsViewYStart;


        // Scale buttons
        buttonWidth = getScreenWidth() / 8;
        buttonHeight = getScreenHeight() / 15;

        // Scale font
        int titleFontSize = getScreenWidth() / 30;
        titleFont = new Font("Brush Script MT", Font.BOLD, titleFontSize);
        int generalFontSize = getScreenWidth() / 100;
        generalFont = new Font("Helvetica", Font.BOLD, generalFontSize);
        int instructionFontSize = (int)(generalFontSize*1.5);
        instructionFont = new Font("Helvetica", Font.BOLD, instructionFontSize);


        titleButtonMargin = titleStartY + getScreenHeight()/40;
    }
}
