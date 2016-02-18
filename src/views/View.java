package views;

import java.awt.*;

/**
 * Created by Bradley on 2/17/16.
 */
public abstract class View {

    private int screenWidth;
    private int screenHeight;

    public View(int width, int height){

        this.screenWidth = width;
        this.screenHeight = height;
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
    public int getScreenWidth(){
        return screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

}
