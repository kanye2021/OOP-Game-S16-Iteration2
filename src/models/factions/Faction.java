package models.factions;

import utilities.RelationshipList;

/**
 * Created by aseber on 3/14/16.
 */
public enum Faction {

    RED,
    BLUE,
    ANIMAL,
    TRADE_GUILD;


    static {

        RED.addAssociation(BLUE, -0.35);
        RED.addAssociation(ANIMAL, -0.5);

        BLUE.addAssociation(RED, -0.2);
        BLUE.addAssociation(ANIMAL, -0.1);

        ANIMAL.addAssociation(RED, -0.6);
        ANIMAL.addAssociation(BLUE, 0.2);
        ANIMAL.addAssociation(TRADE_GUILD, 0.1);

    }

    RelationshipList<Faction> factionRelations = new RelationshipList<>();

    private void addAssociation(Faction otherFaction, double relationship) {

        factionRelations.modifyRelationship(otherFaction, relationship);

    }

    public double getAssociation(Faction otherFaction) {

        if (this == otherFaction) {
            return 1.0;
        }

        return factionRelations.getRelationship(otherFaction).getRelationshipValue();

    }

}
