package utilities;

import models.map.Map;
import views.sprites.DirectionalSprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by denzel on 3/10/16.
 */


//this nigga makes the panties drop.
public class Animator {

    //a bufferedImage arraylist
    private ArrayList<Image> bufferedImages;

    //the sprite from the bufferedImage
    private Image image;

    //frame count
    private int frame, pauseFrame;

    //animation speed
    private long speed, previous;

    //boolean if the animation is still running
    private boolean running;

    //Every Animator object needs the images on how to animate
    public Animator(ArrayList<Image> bufferedImages) {

        this.bufferedImages = bufferedImages;
    }

    //sets the speed of the animation to happen at every ____ seconds
    public void setSpeed(long speed) {
        this.speed = speed;
    }

    //be able to update the frame from the bufferedImage arrayList
    public Image update(long time) {

        running = true;

        //if the animation is still running
        if (running) {
            //if the time - previous animation is greater than the speed time
            if (time - previous >= speed) {
                //increment frames
                if (bufferedImages != null) {
                        if (frame < 6) {
                            image = bufferedImages.get(frame);
                            previous = time;
                            frame++;
                            return image;
                        } else {
                            //reset the frames
                            reset();
                        }
                    }
                }
            }
            return image;
        }



    //be able to pause at the exact frame left off at
    public void pause(){
        pauseFrame = frame;
        running = false;
    }

    //be able to resume at the exact frame paused at
    public void resume(){
        running = true;
        frame = pauseFrame;
    }

    //reset everything
    public void reset(){
        frame = 0;
        running = false;
    }

}
