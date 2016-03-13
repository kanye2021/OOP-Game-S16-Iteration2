package AI.Personality.Interests;

import AI.Personality.Interests.EntityInterest.EntityInterest;
import AI.Personality.Interests.ItemInterest.ItemInterest;
import AI.Personality.Interests.PointInterest.PointInterest;

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

    public HashSet<EntityInterest> getEntityInterests() {

        HashSet<EntityInterest> subsetInterests = new HashSet<>();

        interests.forEach((interest) -> {
                    if (interest.getInterestType() == Interest.Type.ENTITY) {
                        subsetInterests.add((EntityInterest) interest);
                    }
                }
        );

        return subsetInterests;

    }

    public HashSet<ItemInterest> getItemInterests() {

        HashSet<ItemInterest> subsetInterests = new HashSet<>();

        interests.forEach((interest) -> {
                    if (interest.getInterestType() == Interest.Type.ITEM) {
                        subsetInterests.add((ItemInterest) interest);
                    }
                }
        );

        return subsetInterests;

    }

    public HashSet<PointInterest> getPointInterests() {

        HashSet<PointInterest> subsetInterests = new HashSet<>();

        interests.forEach((interest) -> {
                    if (interest.getInterestType() == Interest.Type.POINT) {
                        subsetInterests.add((PointInterest) interest);
                    }
                }
        );

        return subsetInterests;

    }

}
