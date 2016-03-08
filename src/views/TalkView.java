package views;

import models.entities.npc.NPC;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by david on 3/3/16.
 */
public class TalkView extends View {
    //View  Scalable Variables
    private int talkViewXStart;
    private int talkViewYStart;
    private int talkViewWidth;
    private int talkViewHeight;


    //Content to be scaled
    private NPC npc;

    //Styling Properties:
    private Font generalFont;


    public TalkView(int width, int height, Display display, NPC npc){
        super(width, height, display);
        scaleView();
        this.npc = npc;
    }




    @Override
    public void render(Graphics g){

        renderBackground(g);
        renderDialogue(g);
        Toolkit.getDefaultToolkit().sync();
    }


    private void renderBackground(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.lightGray);

        //Opacity stuff
        float opacity = 0.7f;
        AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(opaque);
        g2d.fillRect(talkViewXStart, talkViewYStart, talkViewWidth, talkViewHeight);
    }

    //Can only render 41 character per line currently
    private void renderDialogue(Graphics g){
        int stringLocation = 0; //where the you currently are
        String subString = null;

        //String Locations
        int stringX = (int) (getScreenWidth() * 0.31);
        int stringY = (int) (getScreenHeight() * 0.18);

        while(stringLocation <= npc.getDialogue().get(npc.getDialogueLocation()).length()){
            if(npc.getDialogue().get(npc.getDialogueLocation()).length() - stringLocation >= 38){
                subString = npc.getDialogue().get(npc.getDialogueLocation()).substring(stringLocation, stringLocation + 38);
                stringLocation += 38;
                g.drawString(subString, stringX, stringY);
                stringY += (int) (getScreenHeight() * 0.02);
            }
            else{
                subString = npc.getDialogue().get(npc.getDialogueLocation()).substring(stringLocation,  npc.getDialogue().get(npc.getDialogueLocation()).length());
                g.drawString(subString, stringX, stringY);
                break;
            }
        }

    }


    @Override
    public void scaleView(){
        //PAUSE VIEW DIMENSIONS
        talkViewXStart = (int) (getScreenWidth() * 0.3);
        talkViewYStart = (int) (getScreenHeight() * 0.15);
        talkViewWidth = (int) (getScreenWidth() * 0.3);
        talkViewHeight = (int) (getScreenHeight() * 0.15);


        // Scale font
        int generalFontSize = getScreenWidth() / 100;
        generalFont = new Font("Helvetica", Font.BOLD, generalFontSize);

    }
}
