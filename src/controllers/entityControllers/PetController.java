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

    private int maximumDistanceFromOwner = 2;

    private Pet pet;
    Task moveNorth;
    Task moveNorthWest;
    Task moveSouthWest;
    Task moveSouth;
    Task moveSouthEast;
    Task moveNorthEast;
    private HashMap<Task, Map.Direction> taskMovementVector = new HashMap<>();

    public PetController(Pet pet){
        this.pet = pet;
        createMovementTasks();

        taskMovementVector.put(moveNorth, Map.Direction.NORTH);
        taskMovementVector.put(moveNorthWest, Map.Direction.NORTH_WEST);
        taskMovementVector.put(moveNorthEast, Map.Direction.NORTH_EAST);
        taskMovementVector.put(moveSouthEast, Map.Direction.SOUTH_EAST);
        taskMovementVector.put(moveSouth, Map.Direction.SOUTH);
        taskMovementVector.put(moveSouthWest, Map.Direction.SOUTH_WEST);

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

        Point followeeLocation = ((Entity) o).getLocation();
        Point petLocation = pet.getLocation();

        // Follow the avatar
        double petOwnerDistance = pet.getLocation().distance(followeeLocation);
        Task minimumTask = null;

        if (petOwnerDistance > maximumDistanceFromOwner) {

            double minimumDistance = Double.MAX_VALUE;

            // Find the movement vector that results in the pet being the closest to its owner.
            for (java.util.Map.Entry<Task, Map.Direction> entry : taskMovementVector.entrySet()) {

                Point newPetLocation = entry.getValue().neighbor(petLocation);

                double newPetDistance = newPetLocation.distance(followeeLocation);

                if (newPetDistance < minimumDistance && pet.canTraverseTerrain(newPetLocation)) {

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
