package utilities;

import models.entities.Entity;
import models.map.Map;

import java.awt.*;

/**
 * Created by aseber on 3/7/16.
 */
public final class NavigationUtilities {

    public static final Map.Direction getDirectionToMove(Entity entity, Point startPoint, Point endPoint) {

        double minimumDistance = Double.MAX_VALUE;
        Map.Direction minimumDirection = null;

        // Find the movement vector that results in the pet being the closest to its owner.
        for (Map.Direction direction : Map.Direction.values()) {

            Point newStartLocation = direction.neighbor(startPoint);

            double newDistance = newStartLocation.distanceSq(endPoint);

            if (newDistance < minimumDistance && entity.canTraverseTerrain(newStartLocation)) {

                minimumDistance = newDistance;
                minimumDirection = direction;

            }

        }

        return minimumDirection;

    }

}
