package AI.Personality.Interests.PointInterest;

import AI.Memory.Memory;
import AI.Memory.ThoughtInterface;
import models.entities.npc.NPC;
import models.map.Map;
import utilities.UniformPicker;

import java.awt.*;

/**
 * Created by aseber on 3/11/16.
 */
public class ExploreInterest extends PointInterest {

    public ExploreInterest(double interestLevel) {

        super(interestLevel);

    }

    private ExploreInterest(ExploreInterest interest, Point pointOfInterest) {

        super(interest.getInterestLevel());
        setPointOfInterest(pointOfInterest);

    }

    public ExploreInterest createRuntimeInterest(NPC npc) {

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

    public void update(Memory memory) {

        // Do nothing, the item doesn't move!

    }

    public double getInterestWeight() {

        return 100 * getInterestLevel();

    }

    public boolean isApplicable(ThoughtInterface memory) {

        return !memory.getNPC().getLocation().equals(getPointOfInterest());

    }

    @Override
    public String getSpecificInterest() {
        String test = "ExploreInterest";
        System.out.println(test);
        return test;
    }
}
