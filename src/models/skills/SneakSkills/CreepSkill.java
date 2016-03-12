package models.skills.SneakSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Entity;
import models.map.Map;
import models.map.Tile;
import models.skills.ActiveSkill;
import models.stats.Stats;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/25/16.
 */
public class CreepSkill extends ActiveSkill {
    private javax.swing.Timer debuffTimer;
    private int debuffTimerDelay;
    private boolean isNotRunning;
    private final double constant = 0.5;//reduces speed by half
    private int damage;
    public CreepSkill(){
        cooldown = false;
        cooldownTime = 5*SECONDS;
        damage = 10;
    }
    @Override
    public SkillDictionary initID() {

        return SkillDictionary.CREEP;

    }

    @Override
    public String getName() {
        return "Creep";
    }

    @Override
    public void onActivate(Entity entity) {
        if(cooldown){
            System.out.println("Calm Down and Cool Down m8");
            return;
        }
        cooldown=true;
        cost = 10;
    //need to use alphacomposite on entity here
        int mana = entity.getStats().getStat(Stats.Type.MANA);
        if(mana > cost){
            Stats stats = entity.getStats();
            entity.setStatusEffect(StatusEffects.StatusEffect.INVISIBLE);
            //int originalSpeed = stats.getMovement();
            //double entityFinalSpeed = stats.getMovement() * constant;
            //need a timer here
            int delta = 3;
            stats.modifyStat(Stats.Type.MOVEMENT, -delta);//decreases speed by a constant
            int init =stats.getStat(Stats.Type.MOVEMENT);
                    //TODO:show that the avatar looks invisible

            //TODO:implement back attack to cause extra damaage



            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            stats.modifyStat(Stats.Type.MOVEMENT, delta);
                            //TODO:make avatar look visible again
                            System.out.println("IT WORKED!");
                            int fina = stats.getStat(Stats.Type.MOVEMENT);
                            System.out.println(fina);
                            cooldown=false;
                            entity.setStatusEffect(StatusEffects.StatusEffect.NONE);
                        }
                    },
                    cooldownTime
            );
        }

    }

    @Override
    public KeyEvent[] initActivatorKeys() {


        return null;

    }

    public void sneakBehind(Entity entity){
        Map.Direction entityOrientation = entity.getOrientation();
        //Map.Direction targetOrientation = target.getOrientation();
        //Map.Direction targetOrientation = entity.getOrientation();
        Point entityLoc = entity.getLocation();

        //Point targetLoc = target.getLocation();
        //need to consider offset

        /*if(entityOrientation!=targetOrientation){
            return false;
        }*/

        //offSet is assuming the same direction as the cartesian plane posted on slack

        Point offset = new Point();

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



        Point targetPoint = new Point(entityLoc.x+offset.x,entityLoc.y+offset.y);

        Tile targetTile = entity.getMap().getTileAt(targetPoint);

        if(!targetTile.hasEntity()){
            System.out.println("nothing here");
            return;
        }
        Entity target = targetTile.getEntity();

        if(entity.getStatusEffect() == StatusEffects.StatusEffect.INVISIBLE&&entityOrientation==target.getOrientation()){
            Projectile projectile = new Projectile(2*damage,1, StatusEffects.StatusEffect.NONE);
            new LinearAttack(entity,projectile);
            System.out.println("Double Trouble!");
            entity.setStatusEffect(StatusEffects.StatusEffect.NONE);
        }else if(targetTile.hasEntity()){
            Projectile projectile = new Projectile(damage,1, StatusEffects.StatusEffect.NONE);
            new LinearAttack(entity,projectile);
            entity.setStatusEffect(StatusEffects.StatusEffect.NONE);
        }
        else{
            System.out.println("How did you get here?");
        }


    }
}
