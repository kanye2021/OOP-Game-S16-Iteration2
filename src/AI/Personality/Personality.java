package AI.Personality;

import AI.Personality.Interests.*;
import AI.Personality.Interests.EntityInterest.FollowInterest;
import AI.Personality.Interests.ItemInterest.ItemPickupInterest;
import AI.Personality.Interests.PointInterest.ExploreInterest;

/**
 * Created by Bradley on 3/5/16.
 */
public enum Personality {

    DOG(0.25, 0.3, 0.2, new InterestList(new FollowInterest(1.0), new ItemPickupInterest(0.4), new ExploreInterest(0.2))),
    DEFAULT(0.25, 0.3, 0.2, new InterestList(new ExploreInterest(1.0)));
    /*HITLER(1.0, 0.0, 0.0, 1.0, 1.0), // Will attack no matter what
    HOSTILE(0.9, 0.1, 0.1, 0.9, 1.0), // Will almost definitely attack
    ANGRY(0.7, 0.2, 0.2, 0.5, 1.0), // Is very likely to attack
    IRITABLE(0.6, 0.5, 0.5, 0.4, 0.8), // Will probably attack
    AGNOSTIC(0.1, 0.1, 0.1, 0.3, 0.1 ), // Doesn't really want to help or hurt you
    KIND(0.1, 0.8, 0.3, 0.4, 0.6), // WIll probably help you ,will still attack if you attack back.
    FRIENDLY(0.0, 1.0, 0.5, 0.3, 0.5), // Very friendly, will want to help you. Will still attack if he catches you pick pocekting
    PUSHOVER(0.0, 1.0, 0.8, 0.2, 0.3), // Will almost always let thing slide,
    MICHAEL_SERA(0.0, 1.0, 1.0, 0.0, 0.0), // Complete pussy. You can walk all over this NPC.
    PET(0.1, 0.0, 0.1, 0.3, 0.1); // Basically an agnostic that won't trade.*/
    
    /*private double attackOnSightProbability; // How likely he is to engage automatically.
    private double tradeProbability; // How likely it is he will want to trade.
    private double pursuadeProbablity; // How likelly you will be able to barder.
    private double vigilance; // How likely he is to see you pickpocketing.
    private double attackWhenAttackedProbability; // How likely he is to attack you if you pickpocket (and get caught) or attack.*/


    private double scatter_Brainedness;   // How likely the entity is to change their decision before it is completed
                                            // Exists on a continuum:
                                            // Clear-headed (0.0) <--------------------> Scatter-brain-Jane (1.0)
    private double aggressiveness;        // How aggressive the entity is with other entities
                                            // Exists on a continuum:
                                            // friendly (0.0) <---------- | passive (0.5) | ---------> aggressive(1.0)
    private double laziness;              // How likely the entity is to not carry out a decision that tick
                                            // Exists on a continuum:
                                            // Cocaine cowboy  (0.0) <--------------------> Snorlax (1.0)
    private InterestList interests;       // A list of all interests a personality has.
                                            // Useful for defining what a NPC is capable of.

    Personality(double scatter_Brainedness, double aggressiveness, double laziness, InterestList interests) {

        // The new constructor for personalities.
        this.scatter_Brainedness = scatter_Brainedness;
        this.aggressiveness = aggressiveness;
        this.laziness = laziness;
        this.interests = interests;

    }

    public double getScatter_Brainedness() {

        return scatter_Brainedness;

    }

    public double getAggressiveness() {

        return aggressiveness;

    }

    public double getLaziness() {

        return laziness;

    }

    public InterestList getInterests() {

        return interests;

    }

}
