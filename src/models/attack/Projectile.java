package models.attack;

import utilities.IOUtilities;

import java.awt.*;

/**
 * Created by ben on 3/8/16.
 */
public class Projectile {
    //Things I would need:
    //Attack
    //
    protected int damage;
    protected int range;
    protected StatusEffects.StatusEffect statusEffect;
    protected Image image;
    private final String BASE_FILEPATH = "./src/res/skills/";

    //Put status effects here
    public Projectile(int damage,int range,StatusEffects.StatusEffect attackEffect){
        this.damage = damage;
        this.range = range;
        this.statusEffect = attackEffect;
        this.image = null;
    }

    public Projectile(int damage, int range, StatusEffects.StatusEffect attackEffect, String filepath){
        this.damage = damage;
        this.range = range;
        this.statusEffect = attackEffect;
        this.image = IOUtilities.getImageIcon(IOUtilities.getFileSystemDependentPath(BASE_FILEPATH + filepath)).getImage();
    }

    public Image getImage(){
        return image;
    }
}
