package views;

import utilities.IOUtilities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by david on 3/1/16.
 */
public class PauseView extends View{

    //Enum of menu options to be displayed
    public enum MenuOptions {
        CONTINUE("Continue"),
        SAVE_GAME("Save Game"),
        OPTIONS("Options"),
        LOAD_GAME("Load Game"),
        EXIT_GAME("Exit Game");

        private String optionLabel;

        MenuOptions(String s) {
            this.optionLabel = s;
        }

        public String toString() {
            return optionLabel;
        }

    }

    // Constants
    private final String TITLE = "Paused";

    //Title scalable variables
    private int titleStartX;
    private int titleStartY;
    private int titleWidth;
    private int titleHeight;

    //Button Scalable variables.
    private int buttonWidth;
    private int buttonHeight;

    //View  Scalable Variables
    private int pauseViewXStart;
    private int pauseViewYStart;
    private int pauseViewWidth;
    private int pauseViewHeight;

    // Styling properties
    private Font titleFont;
    private Font generalFont;
    private int titleButtonMargin;

    // Data properties
    private MenuOptions selected;

    public PauseView(int width, int height, Display display){
        super(width, height, display);
        selected = MenuOptions.CONTINUE;
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



    @Override
    public void render(Graphics g){

        renderBackground(g);
        renderTitle(g);
        renderButtons(g);
        Toolkit.getDefaultToolkit().sync();
    }


    private void renderBackground(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(new Color(25, 25, 25));

        //Opacity stuff
        float opacity = 0.7f;
        AlphaComposite opaque =AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(opaque);
        g2d.fillRect(pauseViewXStart, pauseViewYStart, pauseViewWidth, pauseViewHeight);
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
        g.setColor(Color.lightGray);
        g.drawString(TITLE, titleX, titleY);
    }

    private void renderButtons(Graphics g){
        int start = g.getFontMetrics(titleFont).getHeight() + titleButtonMargin;

        g.setFont(generalFont);
        FontMetrics fm = g.getFontMetrics(generalFont);

        for (MenuOptions option : MenuOptions.values()) {

            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);

            int boxX = getScreenWidth() / 2 - buttonWidth / 2 + 2;
            int boxY = buttonHeight * option.ordinal() + start;
            int boxDX = buttonWidth;
            int boxDY = buttonHeight;

            int stringX = getScreenWidth() / 2 - (int) (rectangle.getWidth() / 2);
            int stringY = option.ordinal() * buttonHeight + (int) (rectangle.getHeight() / 2) + fm.getAscent() + start;

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
        }
    }

    @Override
    public void scaleView(){
        //PAUSE VIEW DIMENSIONS
        pauseViewXStart = (int) (getScreenWidth() * 0.27);
        pauseViewYStart = (int) (getScreenWidth() * 0.1);
        pauseViewWidth = (int) (getScreenWidth() * 0.5);
        pauseViewHeight = (int) (getScreenHeight() * 0.5);

        //PAUSE TITLE
        titleStartX = (int) (getScreenWidth() * 0.32);
        titleStartY = (int) (getScreenHeight() * 0.15);
        titleWidth = (int) (getScreenWidth() * 0.4);
        titleHeight = (int) (getScreenHeight() * 0.1);

        // Scale buttons
        buttonWidth = getScreenWidth() / 8;
        buttonHeight = getScreenHeight() / 30;

        // Scale font
        int titleFontSize = getScreenWidth() / 30;
        titleFont = new Font("Brush Script MT", Font.BOLD, titleFontSize);
        int generalFontSize = getScreenWidth() / 100;
        generalFont = new Font("Helvetica", Font.BOLD, generalFontSize);

        titleButtonMargin = (int) (getScreenHeight() * 0.20);
    }
}


