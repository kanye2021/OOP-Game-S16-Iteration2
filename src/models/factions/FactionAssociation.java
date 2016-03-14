package models.factions;

import utilities.MathUtilities;

/**
 * Created by aseber on 3/14/16.
 */
public class FactionAssociation {

    private double strength; // Spans [-1, 1]
    private Faction factionOfInterest;

    public FactionAssociation(double strength, Faction factionOfInterest) {

        MathUtilities.putInRange(0.0, strength, 1.0);

        this.strength = strength;
        this.factionOfInterest = factionOfInterest;

    }

    public double getStrength() {

        return strength;

    }

    public double getFactionWeight(Faction otherFaction) {

        return strength * factionOfInterest.getAssociation(otherFaction);

    }

    public double getFactionWeight(FactionAssociation otherFaction) {

        return getFactionWeight(otherFaction.factionOfInterest);

    }

}
