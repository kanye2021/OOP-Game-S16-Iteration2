package models;

/**
 * Created by Bradley on 2/18/16.
 */
public interface Occupation {

    // Iteration 1 requirements.

    String getOccupationType();

    // Initialize stats.
    void initStats();

    // Modify stats.
    void modifyStats(StatModification mod);

    // Iteration 2 requirements

    // Skills will be objects and each occupation type will create differnet
    // types of skill objects.
    void initSkills();

    // Items will have occupations that are able to equip it as properties.

    // This function will carry out the occupation specific basic attack.
    // The basic attack will also be affected by the primary weapon equipped.
    // Takes into account the stats (offensive rating)
    void basicAttack();
}
