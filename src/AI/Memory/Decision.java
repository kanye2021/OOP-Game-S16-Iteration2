package AI.Memory;

import AI.Personality.Interests.Interest;

import java.awt.*;

/**
 * Created by Bradley on 3/5/16.
 */
public class Decision {

    private Interest interest;

    public Decision(Interest interest) {

        this.interest = interest;

    }

    public boolean isInterestValid(ThoughtInterface memory) {

        return interest.isApplicable(memory);

    }

    public double getInterestLevel() {
        return interest.getInterestLevel();
    }

    public Point getPointOfInterest() {

        return interest.getPointOfInterest();

    }

    public void updateInterest(Memory memory) {
        interest.update(memory);
    }

    public String getInterestType() {

        return interest.getInterestType().toString();

    }

    public String getSpecificInterest() {
        return interest.getSpecificInterest();
    }


}
