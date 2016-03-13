package controllers.entityControllers.AI.Personality.Interests;

import controllers.entityControllers.AI.Memory.ThoughtInterface;
import controllers.entityControllers.AI.Memory.UniformPicker;
import models.entities.npc.NPC;
import models.map.Map;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class ExploreInterest extends PointInterest {

    public ExploreInterest(double interestLevel) {

        super(interestLevel);

    }

    private ExploreInterest(Interest interest, Point pointOfInterest) {

        super(interest.getInterestLevel());
        setPointOfInterest(pointOfInterest);

    }

    public Interest createRuntimeInterest(NPC npc) {

        UniformPicker<Map.Direction> directionPicker = new UniformPicker<>();
        Point npcLocation = npc.getLocation();

        for (Map.Direction direction : Map.Direction.values()) {

            if (npc.canTraverseTerrain(direction.neighbor(npcLocation))) {

                directionPicker.addChoice(direction, 10);

            }

        }

        Map.Direction direction = directionPicker.pickChoice();
        Point pointOfInterest = direction.neighbor(npcLocation);
        return new ExploreInterest(this, pointOfInterest);

    }

    public void update() {

        // Do nothing, the item doesn't move!

    }

    public double getInterestWeight() {

        return 100 * getInterestLevel();

    }

    public boolean isApplicable(ThoughtInterface memory) {

        return !memory.getNPC().getLocation().equals(getPointOfInterest());

    }

}
