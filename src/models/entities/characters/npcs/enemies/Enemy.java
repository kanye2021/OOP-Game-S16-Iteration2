package models.entities.characters.npc;

import models.entities.characters.npcs.NPC;
import models.factions.Faction;
import models.factions.FactionAssociation;
import models.map.Map;
import models.occupation.Occupation;
import models.skills.SkillList;
import models.stats.StatModificationList;
import utilities.IOUtilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dyeung on 2/28/16.
 */
public class Enemy extends NPC {
    public Enemy(Point location, Map map) {

        super(location, map);

    }

    protected Occupation initOccupation() {
        Occupation occupation = new Occupation() {
            @Override
            protected StatModificationList initStats() {
                return null;
            }

            @Override
            protected SkillList initSkills() {
                return null;
            }

            @Override
            public String getOccupation() {
                return "";
            }
        };
        return occupation;
    }
    //TODO: Needs to be separated entity controllers that do different things


    protected final FactionAssociation initFaction() {

        return new FactionAssociation(0.65, Faction.RED);

    }

    protected HashMap<Map.Direction, String> initSprites() {
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-smasher-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();
        return imagePaths;
    }

    public void talk() {

    }

    @Override
    public String getType() {
        return "Enemy";
    }

    @Override
    protected ArrayList<Image> getAnimatorImages() {
        return null;
    }
}
