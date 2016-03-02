package models.entities.npc;

import controllers.entityControllers.EntityController;
import models.map.Map;
import models.occupation.Occupation;
import models.skills.SkillList;
import models.stats.StatModificationList;
import utilities.IOUtilities;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by dyeung on 2/28/16.
 */
public class Enemy extends NPC{
    @Override
    public void startInteraction(NPC npc) {
        super.startInteraction();
    }

    public Enemy(Point location, Map map){
        super(location,map);

    }
    protected Occupation initOccupation(){
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



    protected HashMap<Map.Direction, String> initSprites(){
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/entitys/entity-smasher-");

        HashMap<Map.Direction, String> imagePaths = new HashMap<>();
        return imagePaths;
    }

}
