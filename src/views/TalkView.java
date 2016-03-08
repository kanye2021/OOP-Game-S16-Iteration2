package views;

import models.entities.npc.Talk;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by david on 3/3/16.
 */
public class TalkView extends View {
    //View  Scalable Variables
    private int talkViewXStart;
    private int talkViewYStart;
    private int talkViewWidth;
    private int talkViewHeight;

    //String Scalables
    int stringX;
    int stringY;

    //Content to be scaled
    private String dialogue;

    //Styling Properties:
    private Font generalFont;

    public TalkView(int width, int height, Display display, String dialogue){
        super(width, height, display);
        scaleView();
        this.dialogue = dialogue;
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
        AlphaComposite opaque =AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(opaque);
        g2d.fillRect(talkViewXStart, talkViewYStart, talkViewWidth, talkViewHeight);
    }

    private void renderDialogue(Graphics g){
        g.drawString(dialogue, stringX, stringY);
    }


    @Override
    public void scaleView(){
        //PAUSE VIEW DIMENSIONS
        talkViewXStart = (int) (getScreenWidth() * 0.3);
        talkViewYStart = (int) (getScreenHeight() * 0.15);
        talkViewWidth = (int) (getScreenWidth() * 0.3);
        talkViewHeight = (int) (getScreenHeight() * 0.15);

        //STRING DIMENSIONS
        stringX = (int) (getScreenWidth() * 0.31);
        stringY = (int) (getScreenHeight() * 0.18);


        // Scale font
        int generalFontSize = getScreenWidth() / 100;
        generalFont = new Font("Helvetica", Font.BOLD, generalFontSize);

    }
}
