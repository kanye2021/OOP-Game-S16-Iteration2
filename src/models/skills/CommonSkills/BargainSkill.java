package models.skills.CommonSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.skills.SkillList;

import java.awt.event.KeyEvent;

/**
 * Created by aseber on 2/24/16.
 */
//TODO:Figure out if level is connected to entity
public class BargainSkill extends PassiveSkill {
    private int bargainLv;
    private final double constant = 2.0;//Constant used for discounts
    private double percentDiscount;

    public BargainSkill(){
        bargainLv = 1;//Gets level from skill
        percentDiscount = 0.0;
    }
    @Override
    public SkillDictionary initID() {
        return SkillDictionary.BARGAIN;
    }

    @Override
    public void onUpdate(Entity entity) {
        SkillList skillList = entity.getSkills();
        //skillList.
        bargainLv = getLevel();//Gets the newly updated level
        percentDiscount = (constant*(bargainLv-1))/100.0;

    }

    public double getPercentDiscount() {//used for when you access the shop.
        return percentDiscount;
    }

}
