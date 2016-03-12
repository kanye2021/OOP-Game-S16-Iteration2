package controllers.entityControllers.AI.Memory;

import controllers.entityControllers.AI.Personality.Interests.Interest;
import utilities.MathUtilities;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by aseber on 3/12/16.
 */
public class DecisionPicker {

    private HashMap<Decision, Double> interests = new HashMap<>();
    private HashMap<Range, Decision> interestRatios = new HashMap<>();
    private Random rng = new Random();
    private double rand;
    private double totalWeight;

    public void addDecision(Decision decision, double weight) {

        MathUtilities.putInRange(0.0, weight, Double.MAX_VALUE);

        if (weight > 0.0) {

            interests.put(decision, weight);
            totalWeight += weight;

        }

    }

    public Decision pickDecision() {

        rand = rng.nextDouble();
        normalizeRatios();

        for (java.util.AbstractMap.Entry<Range, Decision> pair : interestRatios.entrySet()) {

            if (pair.getKey().inRange(rand)) {

                return pair.getValue();

            }

        }

        // This should never occur! If it does, talk to Austin to fix it.
        System.err.println("DecisionPicker: Bad call to pickDecision() method");
        return null;

    }

    public boolean validDecisionsToPick() {

        return !interests.isEmpty();

    }

    private void normalizeRatios() {

        double beginRatio = 0.0;
        double endRatio;

        for (java.util.AbstractMap.Entry<Decision, Double> pair : interests.entrySet()) {

            endRatio = beginRatio + pair.getValue() / totalWeight;
            interestRatios.put(new Range(beginRatio, endRatio), pair.getKey());
            beginRatio = endRatio;

        }

    }

    private class Range {

        private double begin;
        private double end;

        public Range(double begin, double end) {

            this.begin = begin;
            this.end = end;

        }

        public boolean inRange(double randomNumber) {

            return (randomNumber >= begin && randomNumber <= end);

        }

    }

}
