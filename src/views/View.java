package views;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 2/17/16.
 */
public abstract class View {

    private int screenWidth;
    private int screenHeight;
    private Display display;

    public View(int width, int height, Display display){

        this.screenWidth = width;
        this.screenHeight = height;
        this.display = display;
        scaleView();
    }

    public abstract void render(Graphics g);

    public void onWindowResize(Component component) {
        screenWidth = component.getWidth();
        screenHeight = component.getHeight();
        scaleView();
    }

    public abstract void scaleView();

    // Accessors
    public final int getScreenWidth(){
        return this.screenWidth;
    }

    public final int getScreenHeight(){
        return this.screenHeight;
    }

    public Display getDisplay(){
        return display;
    }

}
