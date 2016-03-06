package models.skills.SneakSkills;

import models.entities.Entity;
import models.map.Map;
import models.map.Tile;
import models.map.Trap;
import models.skills.ActiveSkill;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */

//TODO:Figure out a way to put trap in the XML.
public class DetectRemoveTrapSkill extends ActiveSkill {

    public DetectRemoveTrapSkill(){
        cooldownTime = 3*SECONDS;
        cooldown = false;
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.DETECT_REMOVE_TRAP;

    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("Cooldown time is not over!");
            return;
        }
        if(findTrap(entity)==null){
            cooldown = false;
            System.out.println("Could not find trap");
            return;
        }
        cooldown = true;
        Trap foundTrap = findTrap(entity);
        foundTrap.setHidden(false);//Makes it so trap is not hidden anymore

        System.out.println("I am detect and remove trap skill");
        //TODO:Do shit here!
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {


                        cooldown = false;
                    }
                },
                cooldownTime
        );

    }
    //TODO:Map a key press to here for sneak
    public void removeTrap(Entity entity){
        System.out.println("Remove traphouse called");
        if(findTrap(entity)==null){
            System.out.println("It appears that nothing is there");
            return;
        }
        Trap trap = findTrap(entity);
        boolean hidden = trap.getHidden();
        if(hidden){
            System.out.println("Oh boi the trap is there but its hidden therefore cannot remove");
            return;
        }
        //Remove the trap or delete the trap object
        //Not sure if this works
        trap = null;
    }
    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

    public Trap findTrap(Entity entity){
        Point currentLocation = entity.getLocation();
        Point offset = new Point();
        Map.Direction entityOrientation = entity.getOrientation();
        //How to find target based off of location.
        Point desiredLocation = new Point();
//TODO:Refractor else if cascade into a function in Skills Class
        if(entityOrientation== Map.Direction.NORTH){
            offset.x=0;
            offset.y=-1;
        }
        else if(entityOrientation == Map.Direction.NORTH_EAST){
            offset.x=1;
            offset.y=-1;
        }
        else if(entityOrientation == Map.Direction.SOUTH_EAST){
            offset.x=1;
            offset.y=0;
        }
        else if(entityOrientation == Map.Direction.SOUTH){
            offset.x=0;
            offset.y=1;
        }
        else if(entityOrientation == Map.Direction.SOUTH_WEST){
            offset.x=-1;
            offset.y=1;
        }
        else if(entityOrientation == Map.Direction.NORTH_WEST){
            offset.x=-1;
            offset.y=0;
        }
        else{
            offset.x=0;
            offset.y=0;
            System.out.println("Really? You put in that much work to break the program?");
        }
        desiredLocation.x = currentLocation.x+offset.x;
        desiredLocation.y = currentLocation.y+offset.y;

        Map map = entity.getMap();
        Tile desiredTile = map.getTileAt(desiredLocation);

        if(desiredTile == null){
            return null;
        }
        if (desiredTile.hasTrap()) {
            return desiredTile.getTrap();
        }
        return null;
    }

}
