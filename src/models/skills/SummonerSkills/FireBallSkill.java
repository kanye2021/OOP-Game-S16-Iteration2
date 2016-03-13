package models.skills.SummonerSkills;

import models.attack.LinearAttack;
import models.attack.Projectile;
import models.entities.Avatar;
import models.entities.Entity;
import models.skills.ActiveSkill;
import models.stats.Stats;
import utilities.Animator;
import utilities.IOUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ben on 3/8/16.
 */
//From Tales of symphonia is a linear path of a fire ball
    //Kinda like fireball jutsu if you want to think of it like that
public class FireBallSkill extends ActiveSkill{
    private int damage;
    private int range;
    private int speed;

    public FireBallSkill(){
        damage = 5;
        range = 3;
        speed = 200;
        this.animator = new Animator(initSprite());
        animator.setSpeed(speed);
    }

    @Override
    public SkillDictionary initID() {

        return SkillDictionary.FIREBALL;

    }

    @Override
    public String getName() {
        return "Fireball";
    }


    @Override
    public void onActivate(Entity entity) {
        System.out.println("Can you take this? Fireball!");
        Projectile projectile = new Projectile(damage,range,5,entity.getMap(),initSprite());
        new LinearAttack(entity,projectile);//This is the attack


    }



    @Override
    public KeyEvent[] initActivatorKeys() {

        // This initializes the key to activate this skill! It should not be null if we intend to actually
        // use the skill
        return null;

    }

    @Override
    public ArrayList<Image> initSprite() {
        String imageBasePath = IOUtilities.getFileSystemDependentPath("./src/res/skills/summoner-fireball.png");


        ArrayList<Image> imagePaths = new ArrayList<>();

        imagePaths.add(new ImageIcon(imageBasePath).getImage());

        return imagePaths;
    }
}
