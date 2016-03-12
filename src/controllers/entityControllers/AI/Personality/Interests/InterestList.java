package controllers.entityControllers.AI.Personality.Interests;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by aseber on 3/9/16.
 */
public class InterestList {

    HashSet<Interest> interests = new HashSet<>();

    public InterestList(Interest... inputInterests) {

        interests.addAll(Arrays.asList(inputInterests));

    }

    private void addInterest(Interest interest) {

        interests.add(interest);

    }

    public HashSet<Interest> getInterests() {

        return interests;

    }

    public HashSet<Interest> getInterests(Interest.Type interestType) {

        HashSet<Interest> subsetInterests = new HashSet<>();

        interests.forEach((interest) -> {
                if (interest.getInterestType() == interestType) {
                    subsetInterests.add(interest);
                }
            }
        );

        return subsetInterests;

    }

}
