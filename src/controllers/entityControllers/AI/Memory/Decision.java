package controllers.entityControllers.AI.Memory;

import controllers.entityControllers.AI.Personality.Interests.Interest;

import java.awt.*;

/**
 * Created by Bradley on 3/5/16.
 */
public class Decision {

    public Decision(Interest interest) {

        this.interest = interest;

    }

    private Interest interest;

    public boolean isInterestValid(ThoughtInterface memory) {

        return interest.isApplicable(memory);

    }

    public double getInterestLevel(){
        return interest.getInterestLevel();
    }

    public void updateInterest() {
        interest.Update();
    }

    public String getInterestType() {

        return interest.getInterestType().toString();

    }


}
