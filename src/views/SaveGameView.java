package views;

import controllers.SaveGameViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by dyeung on 2/18/16.
 */
public class SaveGameView extends View {
    public enum MenuOptions {
        SAVE_GAME_EXIT("Save Game and Exit"),
        SAVE_GAME("Save Game"),
        EXIT("Return back to Game");

        private String optionLabel;

        MenuOptions(String s) {
            this.optionLabel = s;
        }

        public String toString() {
            return optionLabel;
        }
    }
    private JTextField saveStateName;
    private boolean firstRender;
    //Scalable variables
    private int buttonWidth;
    private int buttonHeight;
    private int heightOffset;
    private Font buttonFont;

    private MenuOptions selected;

    // Data properties
    public SaveGameView(int width, int height, Display display){
        super(width, height, display);
        selected = MenuOptions.SAVE_GAME_EXIT;
        firstRender = true;
    }
    public MenuOptions getSelected() {
        return selected;
    }

    public void previousOption() {
        if (this.selected.ordinal() == 0) {
            selected =  MenuOptions.values()[MenuOptions.values().length - 1];
        } else {
            selected = MenuOptions.values()[selected.ordinal() - 1];
        }
    }
    public void nextOption() {
        if (this.selected.ordinal() == MenuOptions.values().length - 1) {
            selected = MenuOptions.values()[0];
        } else {
            selected = MenuOptions.values()[selected.ordinal() + 1];
        }
    }

    public void scaleView(){ //Reminder that this is called by view initially
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;
        heightOffset = getScreenHeight()/6;
        buttonFont = new Font("Helvetica", Font.BOLD, getScreenWidth()/85);
    }

    /// ----------Render Stuff --------

    public void render(Graphics g){
        renderTitle(g);
        renderButtons(g);
    }


    public void renderTitle(Graphics g){
        // Text Field
        int ytextField = heightOffset;
        int xtextField = getScreenWidth()/2 - buttonWidth/2;

        FontMetrics fm = g.getFontMetrics(buttonFont);
        g.setColor(Color.white);
        String title = "Save File Name";
        g.drawString(title, xtextField, ytextField);

        int textHeightOffset = heightOffset + fm.getHeight();
        if(firstRender) {
            saveStateName = new JTextField(20);
            saveStateName.setBounds(xtextField, textHeightOffset, 200, 40);
            saveStateName.setVisible(true);
            Display d = getDisplay();
            d.add(saveStateName);
            firstRender = false;
        }
        saveStateName.requestFocus();
    }
    public void renderButtons(Graphics g){
        FontMetrics fm = g.getFontMetrics(buttonFont);
        g.setFont(buttonFont);
        int fraction = 1;
        for (MenuOptions option : MenuOptions.values()) {

            //Box Stuff
            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);
            int boxX = getScreenWidth() / 2 - buttonWidth / 2;
            int boxY = heightOffset + ((fraction)* getScreenHeight()/6 ) - buttonHeight/2;
            int boxDX = buttonWidth;
            int boxDY = buttonHeight;

            // String stuff
            Rectangle2D r = fm.getStringBounds(option.toString(), g);
            int stringX = getScreenWidth() / 2 - (int) (r.getWidth() / 2);
            int stringY = boxY + (buttonHeight/2);

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
    public void setSelected(MenuOptions so){
        selected = so;
    }
    public String getSaveFileName(){
        return saveStateName.getText();
    }
    public JTextField getSaveStateName(){
        return saveStateName;
    }
}
