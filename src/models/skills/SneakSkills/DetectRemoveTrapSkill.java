package models.skills.SneakSkills;

import models.area_effects.AreaEffect;
import models.area_effects.TrapAreaEffect;
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
        if(!findTrap(entity)){
            cooldown = false;
            System.out.println("Could not find trap");
            return;
        }
        cooldown = true;



        System.out.println("I am detect and remove trap skill");

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

        if(!findTrap(entity)){
            System.out.println("It appears that nothing is there");
            return;
        }

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

        if(desiredTile.getAreaEffect().getDecal().isVisible()){

            AreaEffect areaEffect = desiredTile.getAreaEffect();
            desiredTile.getAreaEffect().getDecal().setVisible(false);//"Deletes it visibilitywise"

            TrapAreaEffect areaEffect1 = (TrapAreaEffect) areaEffect;

            areaEffect1.setRemoved(true);//Makes it "Removed" So when onTouch is called nothing happens
            //TODO:Figure out if it is possible to delete the object

        }

    }
    @Override
    public KeyEvent[] initActivatorKeys() {

        return null;

    }

    public boolean findTrap(Entity entity){
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
            return false;
        }
        AreaEffect areaEffect = desiredTile.getAreaEffect();
        if(areaEffect==null){
            return false;
        }
        String typeOfAOE = areaEffect.getType();
        if(typeOfAOE== "trap"){
            System.out.println("Trap is here yo");
            //return desiredTile;
            desiredTile.getAreaEffect().getDecal().setVisible(true);
            return true;
        }
        else{
            System.out.println("Lol there is no trap here");
        }
        /*if (desiredTile.hasTrap()) {
            return desiredTile.getTrap();
        }*/
        return false;
    }

}
