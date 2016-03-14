package models.skills.SneakSkills;

import models.area_effects.AreaEffect;
import models.area_effects.TrapAreaEffect;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;
import models.map.Trap;
import models.skills.ActiveSkill;
import views.sprites.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by aseber on 2/25/16.
 */

//TODO:Figure out a way to put trap in the XML.
public class DetectRemoveTrapSkill extends ActiveSkill {

    private boolean detectedTrap;
    private Point detectedTrapPoint;

    public DetectRemoveTrapSkill() {
        cooldownTime = 3*SECONDS;
        cooldown = false;
        cost = 5;
        level = 1;
        detectedTrap = false;
        detectedTrapPoint = new Point();
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.DETECT_REMOVE_TRAP;

    }

    @Override
    public String getName() {
        return "Detect Remove Trap";
    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "sneak-detectRemovetrap.png");
    }

    @Override
    public void onActivate(Entity entity) {
        if(isCooldown()){
            System.out.println("Cooldown time is not over!");
            return;
        }
        if(!payMana(entity,cost)){
            return;
        }

        if (!detectedTrap) {
            Point point = findTrap(entity);
            if (point == null) {
                cooldown = false;
                System.out.println("Could not find trap");
                return;
            }
            // Else I did find a trap and made it visible
            else {
                detectedTrap = true;
                detectedTrapPoint = point;
            }
            System.out.println("I am detect and remove trap skill");
        }
        // Now, i want to remove that trap
        else {
            removeTrapAtPoint(detectedTrapPoint, entity);
            // Reset stuff
            detectedTrap = false;
            detectedTrapPoint = new Point();
        }


    }

    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

    public Point findTrap(Entity entity) {
        boolean foundATrap = false;
        Point currentLocation = entity.getLocation();
        Point offset = new Point();
        Map.Direction entityOrientation = entity.getOrientation();

        //How to find target based off of location.
        Point desiredLocation = new Point();
        if(entityOrientation== Map.Direction.NORTH) {
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
            foundATrap = false;
        }
        AreaEffect areaEffect = desiredTile.getAreaEffect();
        if(areaEffect==null){
            foundATrap = false;
        }
        String typeOfAOE = areaEffect.getType();
        if(typeOfAOE== "trap"){

            System.out.println("Trap is here yo");
            //return desiredTile;
            map.setDecalVisibilityAtPoint(true, desiredLocation);
            return desiredLocation;
        }
        else{
            System.out.println("Lol there is no trap here");
        }

        if (!foundATrap) {
            // Return null if couldnt find a trap
            return null;
        }
        return null;
    }

    private void removeTrapAtPoint(Point point, Entity entity) {
        Map map = entity.getMap();
        map.removeAreaEffectAt(point);
    }

}
