package AI.Personality;

import AI.Personality.Interests.EntityInterest.AttackInterest;
import AI.Personality.Interests.EntityInterest.FollowInterest;
import AI.Personality.Interests.EntityInterest.TradeInterest;
import AI.Personality.Interests.InterestList;
import AI.Personality.Interests.ItemInterest.ItemPickupInterest;
import AI.Personality.Interests.PointInterest.ExploreInterest;

/**
 * Created by Bradley on 3/5/16.
 */
public enum Personality {

    // should not trade unless avatar, should not follow unless followable
    // FACTIONS!

    DOGE(0.075, 0.3, 0.15, new InterestList(new FollowInterest(1.0), new ExploreInterest(0.1))),
    FAT_LARD_SHOPKEEPER(0.03, -0.25, 0.95, new InterestList(new TradeInterest(0.75), new ExploreInterest(0.01))),
    SKINNY_SHOPKEEPER(0.03, -0.25, 0.1, new InterestList(new TradeInterest(0.75), new ExploreInterest(0.35), new ItemPickupInterest(0.5))),
    JORGE_BOSS(0.15, -0.85, 0.05, new InterestList(new AttackInterest(0.85), new ExploreInterest(0.35), new ItemPickupInterest(0.65))),
    DEFAULT(0.05, -0.15, 0.2, new InterestList(new ExploreInterest(0.1)));

    private double scatter_Brainedness;   // How likely the entity is to change their decision before it is completed
                                            // Exists on a continuum:
                                            // Clear-headed (0.0) <--------------------> Scatter-brain-Jane (1.0)
    private double aggressiveness;        // How aggressive the entity is with other entities
                                            // Exists on a continuum:
                                            // friendly (-1.0) <---------- | passive (0.0) | ---------> aggressive(1.0)
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
