package views;

import java.awt.*;

/**
 * Created by Bradley on 2/17/16.
 */
public abstract class View {

    private int screenWidth;
    private int screenHeight;
    private Display display;

    public View(int width, int height){

        this.screenWidth = width;
        this.screenHeight = height;
        scaleView();
    }

    public abstract void render(Graphics g);

    public void onWindowResize(Component component) {
        System.out.println("View: ON WINDOW RESIZE: " + component.getWidth());
        screenWidth = component.getWidth();
        screenHeight = component.getHeight();
        scaleView();
    }

    public void setDisplay(Display display){
        this.display = display;
    }

    public abstract void scaleView();

    // Accessors
    public final int getScreenWidth(){
        System.out.println("View: GET WIDTH " + this.screenWidth);
        return this.screenWidth;
    }

    public final int getScreenHeight(){
        return this.screenHeight;
    }

    public Display getDisplay(){
        return display;
    }

}
