package models;

import models.conditions.MapCondition;
import models.entities.Entity;
import models.entities.Entity;
import models.map.Map;
import models.skills.Skill;
import models.skills.SkillList;
import models.stats.Stats;

import java.awt.*;

/**
 * Created by ben on 3/8/16.
 */
public abstract class Attack {
    protected Entity entity;
    protected Map map;
    protected int damage;//Total damage not including target defense
    protected int range;
    protected Map.Direction orientation;
    public abstract void calculateDamage();
    protected Point origin;
    //protected Map map;
    //protected MapCondition.Location location;
    /*Entity entity;
    Map.Direction orientation;
    //int cooldown;//Correlates with weight
    //Cooldown should be done in skills
    int attackSpeed;
    SkillList skillList;
    Skill skill;
    public Attack(Entity entity, SkillList skillList, Skill.SkillDictionary ID){
        this.entity=entity;//Gets the entity that starts a new attack
        this.orientation=entity.getOrientation();
        this.attackSpeed = entity.getStats().getStat(Stats.Type.TOTAL_WEIGHT);

        this.skillList = entity.getSkills();
        findSkill(skillList,ID);
        findAttackType(skill);//Need to find out what attack type is being used.
    }
    public enum AttackType{
        LINEAR,
        RADIAL,
        AREA
    }
    public void findAttackType(){

    }
    public void findSkill(SkillList skillList,Skill.SkillDictionary ID){
        /*for(Skill s:skillList){
            //s.initID();
            if(ID==s.initID()){//found the same skill based on ID
                //I need to get the actual skill using Skill Dictionary probably using skillList
                entity.getSpecificSkill(Skill.SkillDictionary.ID)
            }
        }
        switch(ID){
            case BIND_WOUNDS:
                skill = skillList.get(0);

                break;
            case BARGAIN:
                break;
            case OBSERVATION:
                break;
            case ONE_HANDED_WEAPON:
                break;
            case TWO_HANDED_WEAPON:
                break;
            case BRAWLING:
                break;
            case ENCHANTMENT:
                break;
            case BOON:
                break;
            case BANE:
                break;
            case STAFF:
                break;
            case FIREBALL:
                break;
            case GROUND_DASHER:
                break;
            case INDIGNATION:
                break;
            case PICK_POCKET:
                break;
            case DETECT_REMOVE_TRAP:
                break;
            case CREEP:
                break;
            case RANGED_ATTACK:
                break;


        }
    }*/
}
