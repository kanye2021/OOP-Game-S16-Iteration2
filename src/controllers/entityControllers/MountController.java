package controllers.entityControllers;

import models.entities.Entity;
import models.entities.npc.Mount;
import models.map.Map;
import utilities.Task;

import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by denzel on 3/1/16.
 */
public class MountController implements Observer {


    private Mount mount;
    Task moveNorth;
    Task moveNorthWest;
    Task moveSouthWest;
    Task moveSouth;
    Task moveSouthEast;
    Task moveNorthEast;
    private HashMap<Task, Point> taskMovementVector = new HashMap<>();

    public MountController(Mount mount){
        this.mount = mount;
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
            public void run() { mount.move(Map.Direction.NORTH);}

            @Override
            public void stop() {   }
        };
        moveNorthWest = new Task() {
            @Override
            public void run() { mount.move(Map.Direction.NORTH_WEST);}

            @Override
            public void stop() {   }
        };
        moveSouthWest = new Task() {
            @Override
            public void run() { mount.move(Map.Direction.SOUTH_WEST);}

            @Override
            public void stop() {   }
        };
        moveSouth = new Task() {
            @Override
            public void run() { mount.move(Map.Direction.SOUTH);}

            @Override
            public void stop() {   }
        };
        moveSouthEast = new Task() {
            @Override
            public void run() { mount.move(Map.Direction.SOUTH_EAST);}

            @Override
            public void stop() {   }
        };
        moveNorthEast = new Task() {
            @Override
            public void run() { mount.move(Map.Direction.NORTH_EAST);}

            @Override
            public void stop() {   }
        };

    }


    @Override
    public void update(Observable o, Object arg) {
        Entity owner = (Entity) o;
        Point newFolloweeLocation = owner.getLocation();


        //follow the avatar
        double mountOwnerDistance = mount.getLocation().distance(newFolloweeLocation);
        double minimumDistance = 0;
        Task minimumTask = null;

        if (mountOwnerDistance > 0) {

            // Find the movement vector that results in the pet being the closest to its owner.
            for (java.util.Map.Entry<Task, Point> entry : taskMovementVector.entrySet()) {

                Point newPetLocation = new Point(
                        mount.getLocation().x + entry.getValue().x,
                        mount.getLocation().y + entry.getValue().y
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
