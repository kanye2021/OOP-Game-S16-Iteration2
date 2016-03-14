package models.factions;

import AI.Memory.Relationship;

import java.util.HashMap;

/**
 * Created by aseber on 3/14/16.
 */
public enum Faction {

    RED,
    BLUE,
    ANIMAL,
    TRADE_GUILD;

    private static final Relationship neutralRelationship = new Relationship(0.0);

    static {

        RED.addAssociation(BLUE, new Relationship(-0.35));
        RED.addAssociation(ANIMAL, new Relationship(-0.5));

        BLUE.addAssociation(RED, new Relationship(-0.2));
        BLUE.addAssociation(ANIMAL, new Relationship(-0.1));

        ANIMAL.addAssociation(RED, new Relationship(-0.6));
        ANIMAL.addAssociation(BLUE, new Relationship(0.2));
        ANIMAL.addAssociation(TRADE_GUILD, new Relationship(0.1));

    }

    private HashMap<Faction, Relationship> factionRelations = new HashMap<>();

    private void addAssociation(Faction otherFaction, Relationship relationship) {

        factionRelations.put(otherFaction, relationship);

    }

    public double getAssociation(Faction otherFaction) {

        if (this == otherFaction) {
            return 1.0;
        }

        return factionRelations.getOrDefault(otherFaction, neutralRelationship).getRelationshipValue();

    }

}
