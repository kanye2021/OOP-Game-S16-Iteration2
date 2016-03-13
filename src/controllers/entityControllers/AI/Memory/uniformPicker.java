package controllers.entityControllers.AI.Memory;

import utilities.MathUtilities;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by aseber on 3/12/16.
 */
public class UniformPicker<T> {

    private HashMap<T, Double> originalChoices = new HashMap<>();
    private HashMap<Range, T> choiceRatios = new HashMap<>();
    private Random rng = new Random();
    private double rand;
    private double totalWeight;

    public void addChoice(T decision, double weight) {

        MathUtilities.putInRange(0.0, weight, Double.MAX_VALUE);

        if (weight > 0.0) {

            originalChoices.put(decision, weight);
            totalWeight += weight;

        }

    }

    public T pickChoice() {
        rand = rng.nextDouble();
        normalizeRatios();

        for (java.util.AbstractMap.Entry<Range, T> pair : choiceRatios.entrySet()) {
            if (pair.getKey().inRange(rand)) {
                return pair.getValue();
            }

        }

        // This should never occur! If it does, talk to Austin to fix it.
        System.err.println("UniformPicker: Bad call to pickDecision() method");
        return null;

    }

    public boolean validDecisionsToPick() {

        return !originalChoices.isEmpty();

    }

    private void normalizeRatios() {

        choiceRatios.clear();

        double beginRatio = 0.0;
        double endRatio;

        for (java.util.AbstractMap.Entry<T, Double> pair : originalChoices.entrySet()) {

            endRatio = beginRatio + pair.getValue() / totalWeight;
            choiceRatios.put(new Range(beginRatio, endRatio), pair.getKey());
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
