package controllers.entityControllers;

import models.entities.Entity;
import models.entities.Pet;
import models.map.Map;
import utilities.Task;

import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by sergiopuleri on 2/28/16.
 */
public class PetController extends NPCController implements Observer {

    private int maximumDistanceFromOwner = 0;

    private Pet pet;
    Task moveNorth;
    Task moveNorthWest;
    Task moveSouthWest;
    Task moveSouth;
    Task moveSouthEast;
    Task moveNorthEast;
    private HashMap<Task, Point> taskMovementVector = new HashMap<>();

    public PetController(Pet pet){
        this.pet = pet;

        createMovementTasks();
        taskMovementVector.put(moveNorth, new Point(0, -1));
        taskMovementVector.put(moveNorthWest, new Point(-1, -1));
        taskMovementVector.put(moveNorthEast, new Point(1, -1));
        taskMovementVector.put(moveSouthEast, new Point(1, 1));
        taskMovementVector.put(moveSouth, new Point(0, 1));
        taskMovementVector.put(moveSouthWest, new Point(-1, 1));

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
        Entity followee = (Entity) o;

        Point newFolloweeLocation = followee.getLocation();

        // Follow the avatar
        double petOwnerDistance = pet.getLocation().distance(newFolloweeLocation);
        double minimumDistance = Double.MAX_VALUE;
        Task minimumTask = null;

        if (petOwnerDistance > maximumDistanceFromOwner) {

            // Find the movement vector that results in the pet being the closest to its owner.
            for (java.util.Map.Entry<Task, Point> entry : taskMovementVector.entrySet()) {

                Point newPetLocation = new Point(
                        pet.getLocation().x + entry.getValue().x,
                        pet.getLocation().y + entry.getValue().y
                );

                double newPetDistance = newPetLocation.distance(newFolloweeLocation);
                if (newPetDistance < minimumDistance) {

                    minimumDistance = newPetDistance;
                    minimumTask = entry.getKey();

                }

            }

            // And apply it.
            if (minimumTask != null) {

                minimumTask.run();
                minimumTask.stop();

            }
        }
    }


}
