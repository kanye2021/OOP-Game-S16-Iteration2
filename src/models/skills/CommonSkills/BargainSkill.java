package models.skills.CommonSkills;

import models.entities.Entity;
import models.skills.PassiveSkill;
import models.skills.SkillList;
import views.sprites.Sprite;

/**
 * Created by aseber on 2/24/16.
 */
//TODO:Figure out if level is connected to entity
// It is^^^ level is in stats
public class BargainSkill extends PassiveSkill {
    private final double constant = 2.0;//Constant used for discounts
    private int bargainLv;
    private double percentDiscount;

    public BargainSkill() {
        bargainLv = 1;//Gets level from skill
        percentDiscount = 0.0;
    }

    @Override
    public SkillDictionary initID() {
        return SkillDictionary.BARGAIN;
    }

    @Override
    public String getName() {
        return "Bargain";
    }

    @Override
    public void onUpdate(Entity entity) {
        SkillList skillList = entity.getSkills();
        //skillList.
        bargainLv = getLevel();//Gets the newly updated level
        percentDiscount = (constant * (bargainLv - 1)) / 100.0;

    }

    @Override
    public Sprite initSprite() {
        return new Sprite(SKILL_ROOT_FILE_PATH + "common-bargain.png");
    }

    public double getPercentDiscount() {//used for when you access the shop.
        bargainLv = getLevel();//Gets the newly updated level
        percentDiscount = (constant * (bargainLv - 1)) / 100.0;
        return percentDiscount;
    }

}
