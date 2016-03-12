package controllers.entityControllers.AI.Memory;

import controllers.entityControllers.AI.Personality.Interests.Interest;

import java.awt.*;

/**
 * Created by Bradley on 3/5/16.
 */
public class Decision {

    public Decision(Interest interest, Point pointOfInterest) {

        this.interest = interest;
        this.pointOfInterest = pointOfInterest;

    }

    private Interest interest;
    private Point pointOfInterest;

    public boolean isInterestValid(ThoughtInterface memory) {

        return interest.isApplicable(memory);

    }

    public void updateInterest() {

        interest.Update();

    }

    public String getInterestType() {

        return interest.getInterestType().toString();

    }

    public Point getPointOfInterest() {

        return pointOfInterest;

    }

}
