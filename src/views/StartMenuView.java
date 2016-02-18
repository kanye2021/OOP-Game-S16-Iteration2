package views;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by Bradley on 2/17/16.
 */
public class StartMenuView extends View {

    // Constants
    private final String TITLE = "Kanye 2020";
    private final String START_MENU_IMAGE_LOCATION = IOUtilities.getFileSystemDependentPath("./src/res/start_menu/");

    // Scalable variables.
    private int buttonWidth ;
    private int buttonHeight;

    private Font titleFont;
    private int titleButtonMargin;

    public StartMenuView(int width, int height){
        super(width, height);
    }

    @Override
    public void scaleView(){
        // Scale buttons
        buttonWidth = getScreenWidth() / 6;
        buttonHeight = getScreenHeight() / 25;

        // Scale font
        int fontSize = getScreenWidth() / 12;
        titleFont = new Font("Brush Script MT", Font.BOLD, fontSize);

        titleButtonMargin = (int) (getScreenHeight() * 0.15);
    }

    @Override
    public void render(Graphics g){

        renderBackground(g);
        renderTitle(g);
        renderButtons(g);
        Toolkit.getDefaultToolkit().sync();
    }





    private void renderBackground(Graphics g){

    }

    private void renderTitle(Graphics g){
        g.setFont(titleFont);
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(TITLE);
        int x = getScreenWidth() / 2 - titleWidth / 2;
        int y = fm.getHeight();

        g.setColor(Color.blue);
        g.drawString(TITLE, x, y);
    }

    private void renderButtons(Graphics g){

    }
}
