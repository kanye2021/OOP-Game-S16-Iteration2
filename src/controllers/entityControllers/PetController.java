package controllers.entityControllers;

import models.entities.Entity;
import models.entities.Pet;
import models.map.Map;
import utilities.NavigationUtilities;
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
    private HashMap<Map.Direction, Task> taskMovementVector = new HashMap<>();

    public PetController(Pet pet){
        this.pet = pet;
        createMovementTasks();

        taskMovementVector.put(Map.Direction.NORTH, moveNorth);
        taskMovementVector.put(Map.Direction.NORTH_WEST, moveNorthWest);
        taskMovementVector.put(Map.Direction.NORTH_EAST, moveNorthEast);
        taskMovementVector.put(Map.Direction.SOUTH_EAST, moveSouthEast);
        taskMovementVector.put(Map.Direction.SOUTH, moveSouth);
        taskMovementVector.put(Map.Direction.SOUTH_WEST, moveSouthWest);

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

        if (petOwnerDistance > maximumDistanceFromOwner) {

            Task minimumTask = taskMovementVector.get(NavigationUtilities.getDirectionToMove(pet, petLocation, followeeLocation));

            // And apply it.
            if (minimumTask != null) {

                minimumTask.run();
                minimumTask.stop();

            }
        }
    }


}
