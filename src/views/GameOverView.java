package views;

import utilities.IOUtilities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Bradley on 2/17/16.
 */
public class GameOverView extends View {

    // Constants
    private final String TITLE = "GAME OVER";
    private final String START_MENU_IMAGE_LOCATION = IOUtilities.getFileSystemDependentPath("./src/res/start_menu/");
    // Scalable variables.
    private int buttonWidth;
    private int buttonHeight;
    // Styling properties
    private Font titleFont;
    private Font generalFont;
    private int titleButtonMargin;
    // Data properties
    private MenuOptions selected;

    public GameOverView(int width, int height, Display display) {
        super(width, height, display);
        selected = MenuOptions.MAIN_MENU;
    }

    public MenuOptions getSelected() {
        return selected;
    }

    public void previousOption() {
        if (this.selected.ordinal() == 0) {
            selected = MenuOptions.values()[MenuOptions.values().length - 1];
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
    public void scaleView() {
        // Scale buttons
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;

        // Scale font
        int titleFontSize = getScreenWidth() / 12;
        titleFont = new Font("Brush Script MT", Font.BOLD, titleFontSize);
        int generalFontSize = getScreenWidth() / 86;
        generalFont = new Font("Helvetica", Font.BOLD, generalFontSize);

        titleButtonMargin = (int) (getScreenHeight() * 0.15);
    }

    @Override
    public void render(Graphics g) {

        renderBackground(g);
        renderTitle(g);
        renderButtons(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void renderBackground(Graphics g) {

    }

    private void renderTitle(Graphics g) {
        g.setFont(titleFont);
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(TITLE);
        int x = getScreenWidth() / 2 - titleWidth / 2;
        int y = fm.getHeight();

        g.setColor(Color.white);
        g.drawString(TITLE, x, y);
    }

    private void renderButtons(Graphics g) {


        int start = g.getFontMetrics(titleFont).getHeight() + titleButtonMargin;

        g.setFont(generalFont);
        FontMetrics fm = g.getFontMetrics(generalFont);

        for (MenuOptions option : MenuOptions.values()) {

            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);

            int boxX = getScreenWidth() / 2 - buttonWidth / 2;
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

    public enum MenuOptions {
        MAIN_MENU("Main Menu"),
        EXIT_GAME("Exit Game");

        private String optionLabel;

        MenuOptions(String s) {
            this.optionLabel = s;
        }

        public String toString() {
            return optionLabel;
        }
    }
}
