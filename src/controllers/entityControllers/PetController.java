package controllers.entityControllers;

import models.entities.Avatar;
import models.entities.Entity;
import models.entities.Pet;
import models.map.Map;
import utilities.InputMapping;
import utilities.Task;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sergiopuleri on 2/28/16.
 */
public class PetController extends NPCController implements Observer {

    private Pet pet;
    Task moveNorth;
    Task moveNorthWest;
    Task moveSouthWest;
    Task moveSouth;
    Task moveSouthEast;
    Task moveNorthEast;
    

    public PetController(Pet pet){
        this.pet = pet;
        createMovementTasks();
    }
    
    private void createMovementTasks() {        
             moveNorth = new Task() {
                @Override
                public void run() { pet.move(Map.Direction.NORTH);}

                @Override
                public void stop() { pet.stopMoving(); }
            };
            moveNorthWest = new Task() {
                @Override
                public void run() { pet.move(Map.Direction.NORTH_WEST);}

                @Override
                public void stop() { pet.stopMoving(); }
            };
            moveSouthWest = new Task() {
                @Override
                public void run() { pet.move(Map.Direction.SOUTH_WEST);}

                @Override
                public void stop() { pet.stopMoving(); }
            };
            moveSouth = new Task() {
                @Override
                public void run() { pet.move(Map.Direction.SOUTH);}

                @Override
                public void stop() { pet.stopMoving(); }
            };
            moveSouthEast = new Task() {
                @Override
                public void run() { pet.move(Map.Direction.SOUTH_EAST);}

                @Override
                public void stop() { pet.stopMoving(); }
            };
            moveNorthEast = new Task() {
                @Override
                public void run() { pet.move(Map.Direction.NORTH_EAST);}

                @Override
                public void stop() { pet.stopMoving(); }
            };       
        
    }

    @Override
    public void update(Observable o, Object arg) {
        Entity followee = (Entity)o;
        Point newFolloweeLocation = followee.getLocation();
        Double deltaX = this.pet.getLocation().getX() - newFolloweeLocation.getX();
        Double deltaY = this.pet.getLocation().getY() - newFolloweeLocation.getY();
        //System.out.println("@@@DELTAX: " + deltaX);
        //System.out.println("@@@DELTAY: " + deltaY);
        // Follow the avatar
        updatePetsLocation(deltaX, deltaY);

    }

    private void updatePetsLocation(double deltaX, double deltaY) {
        if (deltaY == 2.0 && deltaX == 0.0) {
            moveNorth.run();
            moveNorth.stop();
       }
        else if (deltaY == 0.0 && deltaX == -1.0) {
            moveSouth.run();
            moveSouth.stop();
        }
        else if (deltaY == 2.0 && deltaX == -1.0) {
            moveNorthEast.run();
            moveNorthEast.stop();
        }
        else if (deltaY == 1.0 && deltaX == -1.0) {
            moveNorth.run();
            moveNorth.stop();
        }
        else if (deltaY == -2.0 && deltaX == 1.0) {
            moveSouthWest.run();
            moveSouthWest.stop();
        }
        else if (deltaX == 2.0 && deltaY == -1.0) {
            moveNorthEast.run();
            moveNorthEast.stop();
        }
        else if (deltaY == 2.0 && deltaX == -2.0) {
            moveNorthEast.run();
            moveNorthEast.stop();
        }
        else if (deltaY == -1.0 && deltaX == -1.0) {
            moveSouth.run();
            moveSouth.stop();
        }
        else if (deltaY == 1.0 && deltaX == -2.0) {
            moveSouthEast.run();
            moveSouthEast.stop();
        }
        else if (deltaY == 1.0 && deltaX == 1.0) {
            moveNorth.run();
            moveNorth.stop();
        }
        else if (deltaY == 0.0 && deltaX == 1.0) {
            moveNorth.run();
            moveNorth.stop();
        }
        else if (deltaY == -2.0 && deltaX == 0.0) {
            moveSouth.run();
            moveSouth.stop();
        }
        else if (deltaY == -2.0 && deltaX == 2.0) {
            moveSouthWest.run();
            moveSouthWest.stop();
        }
        else if (deltaY == -1.0 && deltaX == 1.0) {
            moveNorthWest.run();
            moveNorthWest.stop();
        }
        else if (deltaY == 0.0 && deltaX == -2.0) {
            moveSouthEast.run();
            moveSouthEast.stop();
        }
        else if (deltaY == 0.0 && deltaX == 2.0) {
            moveNorthWest.run();
            moveNorthWest.stop();
        }
        else {
            //System.out.println("PEt did not attempt to move");
        }
    }

}
