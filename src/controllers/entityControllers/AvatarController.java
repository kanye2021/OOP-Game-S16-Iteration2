package controllers.entityControllers;

import controllers.*;
import controllers.GameViewController;
import controllers.InventoryViewController;
import models.Equipment;
import models.attack.LinearAttack;
import models.attack.Projectile;
import models.attack.StatusEffects;
import models.entities.Avatar;
import models.entities.Entity;
import models.entities.npc.NPC;
import models.items.takeable.equippable.EquippableItem;
import models.map.Map;
import models.skills.CommonSkills.BindWoundsSkill;
import models.skills.Skill;

import models.skills.SmasherSkills.BrawlingSkill;
import models.skills.SmasherSkills.OneHandedWeaponSkill;
import models.skills.SmasherSkills.TwoHandedWeaponSkill;

import models.skills.SkillList;

import models.skills.SneakSkills.CreepSkill;
import models.skills.SneakSkills.DetectRemoveTrapSkill;
import models.skills.SneakSkills.PickPocketSkill;
import models.skills.SneakSkills.RangedAttackSkill;
import models.skills.SummonerSkills.*;
import models.stats.Stats;
import utilities.TileDetection;
import utilities.InputMapping;
import utilities.SubState;
import utilities.Task;
import views.*;
import views.GameView;
import views.InventoryView;
import views.ToastView;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Bradley on 2/26/2016.
 */
public class AvatarController {
    private InputMapping keyPressMapping;
    private Avatar avatar;
    // Required to manage SubStates. i.e: Inventory, EquippedItems, Entity Interactions.
    private GameViewController gameViewController;
    private GameView gameView;
    private Map.Direction movementDirection;

    //Will hold A SkillList array, which the game VC will look at and assign keybinds to
    private SkillList avatarsSkills;

    public AvatarController(Avatar avatar, GameViewController gameViewController){
        //TODO: Add gameview
        this.avatar = avatar;
        this.gameViewController = gameViewController;
        keyPressMapping = new InputMapping();
        this.gameView = (GameView)gameViewController.getView();
        this.avatarsSkills = avatar.getSkills();
        initKeyPressMapping();
        movementDirection = null;
    }

    public void handleKeyPress(KeyEvent e) {

        keyPressMapping.inputKey(getKeyIntMapping(e));
    }

    public void handleKeyRelease(KeyEvent e){

        keyPressMapping.keyReleased(getKeyIntMapping(e));
    }

    public void useBindWounds(){
        Skill firstSkill = avatar.getSkills().get(1);
        BindWoundsSkill bindWoundsSkill = (BindWoundsSkill) firstSkill;
        bindWoundsSkill.onActivate(avatar);
        //System.out.println("Checkpoint 1");
    }


    public void useFirstSkill(){
        //if smasher, get first skill
        if(avatar.getOccupation().contains("Smasher")){
            //Technically the Smasher class has no actives

        }else if(avatar.getOccupation().contains("Summoner")){
            //first skill should be enchantment here
            Skill firstSkill = avatar.getSpecificSkill(Skill.SkillDictionary.ENCHANTMENT);
            System.out.println(firstSkill);
            EnchantmentSkill enchantmentSkill = (EnchantmentSkill) firstSkill;
            enchantmentSkill.onActivate(avatar);

        }else if(avatar.getOccupation().contains("Sneak")){
            //first skill should be enchantment here
            Skill firstSkill = avatar.getSpecificSkill(Skill.SkillDictionary.CREEP);
            System.out.println(firstSkill);
            CreepSkill creepSkill = (CreepSkill) firstSkill;
            creepSkill.onActivate(avatar);
        }else{
            System.out.println("What are you");
        }
    }

    public void useSecondSkill(){
        //if smasher, get first skill
        if (avatar.getOccupation().contains("Smasher")) {
            //Technically the Smasher class has no actives

        } else if (avatar.getOccupation().contains("Summoner")) {
            //Skill firstSkill = avatar.getSpecificSkill(Skill.SkillDictionary.CREEP);
            //System.out.println(firstSkill);
            //CreepSkill creepSkill = (CreepSkill) firstSkill;
            //creepSkill.onActivate(avatar);
            //first skill should be enchantment here
            Skill secondSkill = avatar.getSpecificSkill(Skill.SkillDictionary.FIREBALL);
            System.out.println(secondSkill);
            FireBallSkill fireBallSkill = (FireBallSkill) secondSkill;
            //Entity entity = (Entity) avatar;
            fireBallSkill.onActivate(avatar);

        } else if (avatar.getOccupation().contains("Sneak")) {
            //first skill should be something..
            Skill secondSkill = avatar.getSpecificSkill(Skill.SkillDictionary.DETECT_REMOVE_TRAP);
            System.out.println(secondSkill);
            DetectRemoveTrapSkill detectSkill = (DetectRemoveTrapSkill) secondSkill;
            detectSkill.onActivate(avatar);
            System.out.println("Hello World!");

        } else {
            System.out.println("What are you");
        }
    }

    public void useThirdSkill() {
        //if smasher, get first skill
        if (avatar.getOccupation().contains("Smasher")) {
            //Technically the Smasher class has no actives

        } else if (avatar.getOccupation().contains("Summoner")) {
            //first skill should be enchantment here
            Skill thirdSkill = avatar.getSpecificSkill(Skill.SkillDictionary.BOON);
            System.out.println(thirdSkill);
            BoonSkill boonSkill = (BoonSkill) thirdSkill;
            boonSkill.onActivate(avatar);
        } else if (avatar.getOccupation().contains("Sneak")) {
            //first skill should be something..
            //first skill should be something..
            Skill thirdSkill = avatar.getSpecificSkill(Skill.SkillDictionary.PICK_POCKET);
            System.out.println(thirdSkill);
            PickPocketSkill pickPocketSkill = (PickPocketSkill) thirdSkill;
            pickPocketSkill.onActivate(avatar);

        } else {
            System.out.println("What are you");
        }
    }

    public void useFourthSkill(){
        //if smasher, get first skill
        if(avatar.getOccupation().contains("Smasher")){
            //Technically the Smasher class has no actives

        }else if(avatar.getOccupation().contains("Summoner")){
            //No more skills
            Skill fourthSkill = avatar.getSpecificSkill(Skill.SkillDictionary.INDIGNATION);
            System.out.println(fourthSkill);
            IndignationSkill indignationSkill = (IndignationSkill) fourthSkill;
            indignationSkill.onActivate(avatar);
        }else if(avatar.getOccupation().contains("Sneak")){
            //first skill should be something..
            //first skill should be something..
            Skill fourthSkill = avatar.getSpecificSkill(Skill.SkillDictionary.DETECT_REMOVE_TRAP);
            System.out.println(fourthSkill);
            DetectRemoveTrapSkill detectRemoveTrapSkill = (DetectRemoveTrapSkill) fourthSkill;
            detectRemoveTrapSkill.onActivate(avatar);

        }else{
            System.out.println("What are you");
        }
    }

    public void useFifthSkill(){
        //if smasher, get first skill
        if(avatar.getOccupation().contains("Smasher")){
            //Technically the Smasher class has no actives

        }else if(avatar.getOccupation().contains("Summoner")){
            //No more skills
            //Skill fourthSkill = avatar.getSpecificSkill(Skill.SkillDictionary.INDIGNATION);
            //System.out.println(fourthSkill);
            //IndignationSkill indignationSkill = (IndignationSkill) fourthSkill;
            //indignationSkill.onActivate(avatar);
            Skill fifthSkill = avatar.getSpecificSkill(Skill.SkillDictionary.GROUND_DASHER);
            System.out.println(fifthSkill);
            GroundDasherSkill groundDasherSkill = (GroundDasherSkill) fifthSkill;
            groundDasherSkill.onActivate(avatar);

        }else if(avatar.getOccupation().contains("Sneak")){
            //first skill should be something..
            //first skill should be something..
            Skill fifthSkill = avatar.getSpecificSkill(Skill.SkillDictionary.CREEP);
            System.out.println(fifthSkill);
            CreepSkill creepSkill = (CreepSkill) fifthSkill;
            creepSkill.sneakBehind(avatar);
        }else{
            System.out.println("What are you");
        }
    }
    public void useBasicAttack(){

        if(avatar.getOccupation().contains("Smasher")){
            EquippableItem item = avatar.getEquipment().getEquipmentLocation(Equipment.Location.RIGHT_ARM);
            if(item==null){
                Skill basicSkill = avatar.getSpecificSkill(Skill.SkillDictionary.BRAWLING);
                System.out.println("Brawling Skill");
                BrawlingSkill brawlingSkill = (BrawlingSkill) basicSkill;
                brawlingSkill.onActivate(avatar);
                return;
            }

            Equipment.Component component= item.getComponent();
            if(component== Equipment.Component.ONE_HANDED_WEAPON){
                Skill basicSkill = avatar.getSpecificSkill(Skill.SkillDictionary.ONE_HANDED_WEAPON);
                System.out.println("1h Skill");
                OneHandedWeaponSkill OneHandSkill = (OneHandedWeaponSkill) basicSkill;
                OneHandSkill.onActivate(avatar);
                //call active skill one handed weapon
            }else if(component == Equipment.Component.TWO_HANDED_WEAPON){
                Skill basicSkill = avatar.getSpecificSkill(Skill.SkillDictionary.TWO_HANDED_WEAPON);
                System.out.println("2h Skill");
                TwoHandedWeaponSkill TwoHandSkill = (TwoHandedWeaponSkill) basicSkill;
                TwoHandSkill.onActivate(avatar);
                //call active skill one handed weapon
            }else{
                System.out.println("Wat");
            }
                //avatar.basicAttack(avatar,component);



        }else if(avatar.getOccupation().contains("Summoner")){
            //No more skills
            EquippableItem item = avatar.getEquipment().getEquipmentLocation(Equipment.Location.RIGHT_ARM);
            if(item==null){
                //TODO:Add cooldown somehow.  I feel like I need to make this a function?
                Projectile projectile = new Projectile(10*avatar.getStats().getStat(Stats.Type.LEVEL)+1,1, StatusEffects.StatusEffect.NONE);
                new LinearAttack(avatar,projectile);
                return;
            }
            Skill basicSkill = avatar.getSpecificSkill(Skill.SkillDictionary.STAFF);
            //System.out.println("Staff Skill");
            StaffSkill staffSkill = (StaffSkill) basicSkill;
            staffSkill.onActivate(avatar);


            //Equipment.Component component= item.getComponent();
            /*EquippableItem item = avatar.getEquipment().getEquipmentLocation(Equipment.Location.RIGHT_ARM);
            Equipment.Component component= item.getComponent();
                avatar.basicAttack(avatar,component);*/


        }else if(avatar.getOccupation().contains("Sneak")){
            EquippableItem item = avatar.getEquipment().getEquipmentLocation(Equipment.Location.RIGHT_ARM);
           // Equipment.Component component= item.getComponent();
            if(item==null){
                //TODO:Add cooldown somehow.  I feel like I need to make this a function?
                Projectile projectile = new Projectile(10*avatar.getStats().getStat(Stats.Type.LEVEL)+1,1, StatusEffects.StatusEffect.NONE);
                new LinearAttack(avatar,projectile);
                return;
            }
            Equipment.Component component= item.getComponent();
            if(component == Equipment.Component.RANGED_WEAPON){
                //avatar.basicAttack(avatar,component);
                 Skill skill = avatar.getSpecificSkill(Skill.SkillDictionary.RANGED_ATTACK);
                RangedAttackSkill rangedAttackSkill = (RangedAttackSkill)skill;
                rangedAttackSkill.onActivate(avatar);//yay!

            }else{
                //TODO:Add cooldown somehow.  I feel like I need to make this a function?
                Projectile projectile = new Projectile(10*avatar.getStats().getStat(Stats.Type.LEVEL)+1,1, StatusEffects.StatusEffect.NONE);
                new LinearAttack(avatar,projectile);

            }

        }else{
            System.out.println("What are you");
        }
    }


    protected void initKeyPressMapping(){}

    private final int getKeyIntMapping(KeyEvent e) {

        /*
         * All of the modifiers are dumped into the e.getModifiers() int. This number is then multiplied by 1000
         * in order to ensure that there is no crossing paths with standard keys.
         */

        return e.getKeyCode() + 1000*e.getModifiers();

    }

    private final int getKeyIntMapping(int... key) {

        /*
         * The first key (key[0]) is the key we desire to be pressed.
         * All subsequent keys are considered masks and multiplied by 1000 as stated above.
         */

        int number = key[0];

        for (int i = 1; i < key.length; i++) {

            number += 1000*key[i];

        }

        return number;
    }
    
    public TileDetection move(){
        if(movementDirection == null){
            return null;
        }
        
        return avatar.move(movementDirection);
    }

    public boolean avatarIsAlive(){
        return avatar.getLives() > 0;
    }
    
    public void setMovementDirection(Map.Direction direction){
        this.movementDirection = direction;
    }

    public void stopMoving(){
        this.movementDirection = null;
    }

    public void startInteraction(NPC npc){
        avatar.startInteraction(npc);
    }

    //I am so sorry for doing this...
    public Avatar getAvatar(){
        return avatar;
    }

    public void unMount(){
        avatar.removeMount();
    }

    public SkillList getAvatarsSkills() {
        return avatarsSkills;
    }
}
