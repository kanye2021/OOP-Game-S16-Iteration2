package AI.Thought;

import AI.Memory.Decision;
import AI.Memory.ThoughtInterface;
import AI.Personality.Interests.EntityInterest.EntityInterest;
import AI.Personality.Interests.Interest;
import AI.Personality.Interests.InterestList;
import AI.Personality.Interests.ItemInterest.ItemInterest;
import AI.Personality.Interests.PointInterest.PointInterest;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.Item;
import utilities.MathUtilities;
import utilities.UniformPicker;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bradley on 3/5/16.
 */
public class FrontalLobe {

    protected NPC npc;
    double rand;
    private Random rng = new Random();
    private ThoughtInterface memory;

    public FrontalLobe(NPC npc, ThoughtInterface memory) {
        this.npc = npc;
        this.memory = memory;
    }

    public void process() {

        findNewEntities();

        rand = rng.nextDouble();
        Decision currentDecision = memory.getDecision();

        //First check if our current decision is not null
        if (currentDecision != null) {

            // Then check if our current decision is valid given the visual memory.
            if (currentDecision.isInterestValid(memory)) {

                // Then check if we should get a new Interest based on scatter_brainedness
                if (memory.getPersonality().getScatter_Brainedness() < rand) {

                    // If all of these are true, we may continue with our current decision!
                    return;

                }

            }

        }

        // Otherwise select a new decision
        selectNewDecision();

    }

    private void findNewEntities() {
        for (Entity entity : memory.getSeenEntities().keySet()) {

            if (!memory.relationshipExists(entity)) {

                // friendly Faction should add some good weight
                // especially those part of the same faction
                // enemy faction should add some bad weight
                double aggressiveFactor = -memory.getPersonality().getAggressiveness();
                double scatter_brainedNess = memory.getPersonality().getScatter_Brainedness();
                double scatterBrainFactor = scatter_brainedNess * rng.nextGaussian() - (scatter_brainedNess / 2);
                double factionFactor = npc.getFaction().getFactionWeight(entity.getFaction());

                double relationalValue = aggressiveFactor + scatterBrainFactor + factionFactor;
                relationalValue = MathUtilities.putInRange(-1.0, relationalValue, 1.0);
                System.out.println(npc.getType() + " FrontalLobe: Added " + entity.getType() + " as a new relationship: " + relationalValue);
                memory.addEntityRelationship(entity, relationalValue);

            }

        }

    }

    private void selectNewDecision() {

        UniformPicker<Decision> validDecisions = new UniformPicker();

        ////// Get all valid decisions //////

        // Each Interest should evaluate the weight based on stuff it takes in. Need to find out
        // how to pass the correct info to take in.

        Interest interestToAdd;
        Point pointOfInterest;
        InterestList interests = memory.getPersonality().getInterests();
        double weight;

        // Find all entity interests and add them
        for (java.util.AbstractMap.Entry<Entity, Point> pair : memory.getSeenEntities().entrySet()) {
            Entity entityOfInterest = pair.getKey();
            pointOfInterest = pair.getValue();
            for (EntityInterest interest : interests.getEntityInterests()) {
                weight = interest.getInterestWeight(entityOfInterest, memory);

                if (weight > 0) {

                    interestToAdd = interest.createRuntimeInterest(entityOfInterest, pointOfInterest);

                    validDecisions.addChoice(new Decision(interestToAdd), weight);

                }

            }

        }

        // Find all item interests and add them
        for (java.util.AbstractMap.Entry<Item, Point> pair : memory.getSeenItems().entrySet()) {
            Item itemOfInterest = pair.getKey();
            pointOfInterest = pair.getValue();

            for (ItemInterest interest : interests.getItemInterests()) {
                weight = interest.getInterestWeight(itemOfInterest, memory);
                if (weight > 0) {

                    interestToAdd = interest.createRuntimeInterest(itemOfInterest, pointOfInterest);
                    validDecisions.addChoice(new Decision(interestToAdd), weight);

                }

            }

        }


        for (PointInterest interest : interests.getPointInterests()) {

            weight = interest.getInterestWeight();
            interestToAdd = interest.createRuntimeInterest(npc);
            validDecisions.addChoice(new Decision(interestToAdd), weight);

        }


        ////// And pick one //////

        if (validDecisions.validDecisionsToPick()) {

            Decision newDecision = validDecisions.pickChoice();

            memory.setDecision(newDecision);
            System.out.println("FrontalLobe: " + npc.getType() + ": " + newDecision.getSpecificInterest());

        }

    }

}
