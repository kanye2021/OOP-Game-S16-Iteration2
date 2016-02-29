package views;

import models.entities.npc.NPC;

import java.awt.*;

/**
 * Created by dyeung on 2/28/16.
 */
public class NPCActionView extends View{
    //Scalable variables
    private  int actionView_Width ;
    private  int actionView_Height;

    //Actual Data to modify with
    private NPC npc;

    public NPCActionView(int width, int height, NPC npc){
        super(width,height);
        this.npc = npc;
        scaleView(width,height);
    }

    @Override
    public void render(Graphics g) {
        renderTitle(g);
        renderOptions(g);
    }
    @Override
    public void scaleView(){
//Doesn't do jack (cause its a viewport)
    }

    public void scaleView(int screenWidth, int screenHeight) {

        //actionView_StartY = TITLE_START_Y + TITLE_HEIGHT;
        actionView_Width = (int)(getScreenWidth() * 0.8);
        System.out.println("Action View: " + actionView_Width);
        actionView_Height = (int) (getScreenWidth() * 0.3);
    }
    public void renderTitle(Graphics g){
        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(0, 0, actionView_Width, actionView_Height);
    }
    public void renderOptions(Graphics g){

    }
}
