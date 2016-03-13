package views;

import models.skills.ActiveSkill;
import models.skills.Skill;
import models.skills.SkillList;
import utilities.InputMapping;
import utilities.Task;
import utilities.TaskWrapper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sergiopuleri on 3/11/16.
 */
public class OptionsView extends View {


    // Necessary to render generic task keybindings on view
    // Dont want to actually mess with the actual inputMapping object.
    public class KeyCodeTaskObject {

        Integer keyCode;
        TaskWrapper taskWrapper;

        public KeyCodeTaskObject(Integer keyCode, TaskWrapper taskWrapper) {
            this.keyCode = keyCode;
            this.taskWrapper = taskWrapper;
        }

        public Integer getKeyCode() {
            return keyCode;
        }

        public TaskWrapper getTaskWrapper() {
            return taskWrapper;
        }

        public void setKeyCode(Integer keyCode) {
            this.keyCode = keyCode;
        }

        public void setTaskWrapper(TaskWrapper taskWrapper) {
            this.taskWrapper = taskWrapper;
        }
    }

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
    private int sidePadding;

    // Data properties
    private ArrayList<ActiveSkill> skills;
    private ArrayList<KeyCodeTaskObject> listOfGenericKeybindings;
    private InputMapping inputMappingHashmap;
    private int selected;
    private boolean listeningForNewKeyBind;
    private int indexForNewKeyBind;
    String selectInstructions;
    String keyBindInstructions;
    String leaveInstructions;
    private int skillCount;
    private int taskCount;

    public OptionsView(int width, int height, Display display, SkillList theSkills, InputMapping inputMapping){
        super(width, height, display);
        selected = 0;
        skills = new ArrayList<ActiveSkill>();
        listOfGenericKeybindings = new ArrayList<KeyCodeTaskObject>();
        this.inputMappingHashmap = inputMapping;
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
        skillCount = skills.size();

        // init array of general keybindings
        // Only wanan display if it is not a skill
        for (Map.Entry<Integer, TaskWrapper> entry : inputMappingHashmap.entrySet()) {
            Integer key = entry.getKey();
            TaskWrapper wrapper = entry.getValue();
            String descriptor = wrapper.getDescriptor();

            if (descriptor.contains("Move") || descriptor.contains("Open") || descriptor.contains("Pause")) {
                listOfGenericKeybindings.add(new KeyCodeTaskObject(key, wrapper));

            }
        }
        taskCount = listOfGenericKeybindings.size();
    }

    public int getSelected() {
        return selected;
    }

    public void previousOption() {
        if (selected == 0) {
            selected = skillCount + taskCount - 1;
        } else {
            selected--;
        }
    }
    public void nextOption() {
        if (selected == skillCount + taskCount - 1) {
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

        // Iterate through all skills
        ActiveSkill skill;
        int i = 0;
        for (; i < skills.size(); i++) {

            // Get dat skill
            skill = skills.get(i);
            String currentKeyBind = KeyEvent.getKeyText(skill.getKeyBind());
            renderTaskNameAndKeyBind(g2d, skill.getName(), start, i, currentKeyBind, opaque, reset);
        }

        // Now iterate of inputhappings hasmap
        for (KeyCodeTaskObject keyCodeTask : listOfGenericKeybindings) {
            TaskWrapper wrapper = keyCodeTask.taskWrapper;
            Integer key = keyCodeTask.keyCode;

            String descriptor = wrapper.getDescriptor();
            String currentKeyBind = KeyEvent.getKeyText(key);
            // Render
            renderTaskNameAndKeyBind(g2d, descriptor, start, i,currentKeyBind, opaque, reset );
            i++;
        }

        // Write instructions :~)

        // Setup font
        g2d.setFont(instructionFont);
        FontMetrics fm = g2d.getFontMetrics(instructionFont);

        // Set appropiate instructions based on state
        String currentInstructions;
        if (isListeningForNewKeyBind()) {
            currentInstructions = keyBindInstructions;
        } else currentInstructions = selectInstructions;

        // Get some rectzzzzzz
        Rectangle2D rectangle = fm.getStringBounds(currentInstructions, g2d);
        Rectangle2D exitRect = fm.getStringBounds(leaveInstructions, g2d);

        // Get some coordinates & write some strings
        int instructionsStartY = optionsViewYStart + optionsViewHeight - (int)(rectangle.getHeight()*2.5);
//        int instructionsStartY = i * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent() + start;
        int instructionsX =  optionsViewXStart + (optionsViewWidth - (int)rectangle.getWidth())/2;
        g2d.drawString(currentInstructions, instructionsX, instructionsStartY);

        // Do it agen pls
        int exitX = optionsViewXStart + (optionsViewWidth - (int)exitRect.getWidth())/2;
        int exitY = instructionsStartY + (int)exitRect.getHeight() + fm.getAscent();
        g2d.drawString(leaveInstructions, exitX, exitY);
    }

    private void renderTaskNameAndKeyBind(Graphics2D g2d, String descriptor, int startY, int index, String keyBind, AlphaComposite opaque, Composite reset) {
        // Setup basic font
        g2d.setFont(generalFont);
        FontMetrics fm = g2d.getFontMetrics(generalFont);

        // Rectangles r cool 2
        Rectangle2D rectangle = fm.getStringBounds(descriptor, g2d);

        // So are boxes
        int boxX = optionsViewXStart + (optionsViewWidth - buttonWidth)/2;
        int boxY = buttonHeight * index + startY;
        int boxDX = buttonWidth;
        int boxDY = buttonHeight;

        int stringX = optionsViewXStart + sidePadding;
        int stringY = index * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent() + startY;

        Color primaryColor;
        Color secondaryColor;
        Color keyBindHighLight;

        if (index == selected) {
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
        g2d.drawString(descriptor, stringX, stringY);

        if (listeningForNewKeyBind && indexForNewKeyBind == index) {
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

        rectangle = fm.getStringBounds(keyBind, g2d);
        keyBindX = keyBindX + (keyBindBoxW - (int)rectangle.getWidth())/2;
        keyBindY = keyBindY + (keyBindBoxH - (int)rectangle.getHeight())/2 + fm.getAscent();
        g2d.drawString(keyBind, keyBindX, keyBindY);
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

    public ArrayList<KeyCodeTaskObject> getListOfGenericKeybindings() {
        return listOfGenericKeybindings;
    }

    public ActiveSkill getCurrentSelectedSkill() {
        return skills.get(selected);
    }

    public KeyCodeTaskObject getCurrentSelectedTask() {
        return listOfGenericKeybindings.get(selected - skillCount);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public int getSkillCount() {
        return skillCount;
    }

    public InputMapping getInputMappingHashmap() {
        return inputMappingHashmap;
    }

    @Override
    public void scaleView(){
        //PAUSE VIEW DIMENSIONS
        optionsViewWidth = (int) (getScreenWidth() * 0.5);
//        optionsViewHeight = (int) (getScreenHeight() * 0.6);
        optionsViewHeight =  (int) (getScreenHeight() * 0.90);

        optionsViewXStart = (getScreenWidth() - optionsViewWidth)/2;
//        optionsViewYStart = (getScreenHeight() - optionsViewHeight)/4;
        optionsViewYStart = 0;

        //PAUSE TITLE
        titleWidth = (int) (getScreenWidth() * 0.4);
        titleHeight = (int) (getScreenHeight() * 0.1);

        titleStartX = optionsViewXStart + (optionsViewWidth - titleWidth)/2;
        titleStartY = optionsViewYStart;


        // Scale buttons
        buttonWidth = getScreenWidth() / 8;
        buttonHeight = getScreenHeight() / 30;


        // Scale font
        int titleFontSize = getScreenWidth() / 30;
        titleFont = new Font("Brush Script MT", Font.BOLD, titleFontSize);
        int generalFontSize = getScreenWidth() / 100;
        generalFont = new Font("Helvetica", Font.BOLD, generalFontSize);
        int instructionFontSize = (int)(generalFontSize*1.5);
        instructionFont = new Font("Helvetica", Font.BOLD, instructionFontSize);


        titleButtonMargin = titleStartY + getScreenHeight()/40;
        sidePadding =  optionsViewWidth/20;
    }
}
