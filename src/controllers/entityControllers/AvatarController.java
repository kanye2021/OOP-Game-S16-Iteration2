package controllers.entityControllers;

import controllers.*;
import controllers.GameViewController;
import controllers.InventoryViewController;
import models.attack.Attack;
import models.attack.RangedAttack;
import controllers.NPCInteractions.NPCMenuController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.map.Map;
import models.map.Tile;
import models.skills.CommonSkills.BindWoundsSkill;
import models.skills.Skill;
import models.skills.SneakSkills.CreepSkill;
import models.skills.SneakSkills.DetectRemoveTrapSkill;
import models.skills.SneakSkills.PickPocketSkill;
import models.skills.SneakSkills.TileDetection;
import models.skills.SummonerSkills.BoonSkill;
import models.skills.SummonerSkills.EnchantmentSkill;
import models.skills.SummonerSkills.StaffSkill;
import utilities.InputMapping;
import utilities.SubState;
import utilities.Task;
import views.*;
import views.GameView;
import views.InventoryView;
import views.NPCMenuView;
import views.ToastView;

import java.awt.event.KeyEvent;

/**
 * Created by Bradley on 2/26/2016.
 */
public class AvatarController extends EntityController {
    private InputMapping keyPressMapping;
    private Avatar avatar;
    // Required to manage SubStates. i.e: Inventory, EquippedItems, Entity Interactions.
    private GameViewController gameViewController;
    private GameView gameView;

    public AvatarController(Avatar avatar, GameViewController gameViewController){
        //TODO: Add gameview
        this.avatar = avatar;
        this.gameViewController = gameViewController;
        keyPressMapping = new InputMapping();
        this.gameView = (GameView)gameViewController.getView();
        initKeyPressMapping();
    }

    public void handleKeyPress(KeyEvent e) {

        keyPressMapping.inputKey(getKeyIntMapping(e));
    }

    public void handleKeyRelease(KeyEvent e){

        keyPressMapping.keyReleased(getKeyIntMapping(e));
    }

    protected void initKeyPressMapping(){

        //task for bindWoundSkill
        Task bindWoundSkill = new Task(){

            Skill firstSkill = avatar.getSkills().get(1);
            BindWoundsSkill bindWoundsSkill = (BindWoundsSkill) firstSkill;


            @Override
            public void run() {
                bindWoundsSkill.onActivate(avatar);
            }

            @Override
            public void stop() {

            }
        };

        Task genericAttack = new Task() {
            @Override
            public void run() {
                RangedAttack rangedAttack = new RangedAttack(avatar, 10,avatar.getMap());
                rangedAttack.hitEntity();
            }

            @Override
            public void stop() {

            }
        };


        //Task for the first specific skill
        Task firstSkill = new Task(){
            @Override
            public void run() {
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

            @Override
            public void stop() {

            }
        };

        //Task for the first specific skill
        Task secondSkill = new Task() {
            @Override
            public void run() {
                //if smasher, get first skill
                if (avatar.getOccupation().contains("Smasher")) {
                    //Technically the Smasher class has no actives

                } else if (avatar.getOccupation().contains("Summoner")) {
                    //first skill should be enchantment here
                    Skill secondSkill = avatar.getSpecificSkill(Skill.SkillDictionary.STAFF);
                    System.out.println(secondSkill);
                    StaffSkill staffSkill = (StaffSkill) secondSkill;
                    staffSkill.onActivate(avatar);

                } else if (avatar.getOccupation().contains("Sneak")) {
                    //first skill should be something..
                    Skill secondSkill = avatar.getSpecificSkill(Skill.SkillDictionary.DETECT_REMOVE_TRAP);
                    System.out.println(secondSkill);
                    DetectRemoveTrapSkill detectSkill = (DetectRemoveTrapSkill) secondSkill;
                    detectSkill.onActivate(avatar);

                } else {
                    System.out.println("What are you");
                }
            }

            @Override
            public void stop() {

            }
        };

        Task openInventory = new Task() {
            @Override
            public void run() {
                InventoryView inventoryView = new InventoryView(gameView.getScreenWidth(), gameView.getScreenHeight(), gameView.getDisplay());
                InventoryViewController inventoryViewController = new InventoryViewController(inventoryView, gameViewController.getStateManager(), avatar);
                SubState inventorySubState = new SubState(inventoryViewController, inventoryView);
                // Add closing task.
                inventoryViewController.setCloseInventory(new Task() {
                    @Override
                    public void run() { inventorySubState.dismiss(); }

                    @Override
                    public void stop() { }
                });
                // Add the substate
                gameViewController.addSubState(inventorySubState);

            }

            @Override
            public void stop() {

            }
        };

        Task openEquipment = new Task() {
            @Override
            public void run() {
                EquipmentView equipmentView = new EquipmentView(gameView.getScreenWidth(), gameView.getScreenHeight(), gameView.getDisplay());
                EquipmentViewController equipmentViewController = new EquipmentViewController(equipmentView, gameViewController.getStateManager(), avatar);
                SubState equipmentSubState = new SubState(equipmentViewController, equipmentView);
                // Add closing task.
                equipmentViewController.setCloseEquipmentTask(new Task() {
                    @Override
                    public void run() { equipmentSubState.dismiss(); }

                    @Override
                    public void stop() { }
                });
                // Add the substate
                gameViewController.addSubState(equipmentSubState);
            }

            @Override
            public void stop() {

            }
        };


        //Task for the first specific skill
        Task thirdSkill = new Task(){
            @Override
            public void run() {
                //if smasher, get first skill
                if(avatar.getOccupation().contains("Smasher")){
                    //Technically the Smasher class has no actives

                }else if(avatar.getOccupation().contains("Summoner")){
                    //first skill should be enchantment here
                    Skill thirdSkill= avatar.getSpecificSkill(Skill.SkillDictionary.BOON);
                    System.out.println(thirdSkill);
                    BoonSkill boonSkill = (BoonSkill) thirdSkill;
                    boonSkill.onActivate(avatar);
                }else if(avatar.getOccupation().contains("Sneak")){
                    //first skill should be something..
                    //first skill should be something..
                    Skill thirdSkill = avatar.getSpecificSkill(Skill.SkillDictionary.PICK_POCKET);
                    System.out.println(thirdSkill);
                    PickPocketSkill pickPocketSkill = (PickPocketSkill) thirdSkill;
                    pickPocketSkill.onActivate(avatar);

                }else{
                    System.out.println("What are you");
                }

            }

            @Override
            public void stop() {

            }
        };





        Task openToastTestView = new Task() {
            @Override
            public void run() {
                ToastView toast = new ToastView(gameView.getScreenWidth(), gameView.getScreenWidth(), gameView.getDisplay(), "Press 'L' to dismiss this toast");
                // For a "Toast Message" the Game View controller will still be handling input, so pass in null.
                SubState toastSubState = new SubState(null, toast);
                // Pass a new inputMapping to the current VC, to handle our interaction within this new SubState:
                // In this cass the current VC is the GameVC, which passes input to the AvatarVC, so i'm adding this
                // input mapping to the Avatar Controller.
                // These input mappings for the new SubState dont need to be created here, if the new substate is the inventory
                // for example. the inventory VC would handle the new input appings
                Task openToast = this;
                AvatarController.this.addKeyPressMapping(new Task() {
                    @Override
                    public void run() {
                        toastSubState.dismiss();
                        // Re-map the "I" key to open the toast view again
                        AvatarController.this.addKeyPressMapping(openToast, KeyEvent.VK_L);
                    }
                    @Override
                    public void stop() {}
                }, KeyEvent.VK_I);
                // Add the substate
                gameViewController.addSubState(toastSubState);
            }
            @Override
            public void stop() {}
        };

        Task openPause = new Task() {
            @Override
            public void run() {
                PauseView pauseView = new PauseView(gameView.getScreenWidth(), gameView.getScreenHeight(), gameView.getDisplay());
                PauseViewController pauseViewController = new PauseViewController(pauseView, gameViewController.getStateManager());
                SubState pauseSubstate = new SubState(pauseViewController, pauseView);
                // Add closing task.
                pauseViewController.setClosePause(new Task() {
                    @Override
                    public void run() { pauseSubstate.dismiss(); }

                    @Override
                    public void stop() { }
                });
                // Add the substate
                gameViewController.addSubState(pauseSubstate);
            }

            @Override
            public void stop() {

            }
        };
        Task clearSubStates= new Task() {
            @Override
            public void run() {
                gameView.clearSubStates();
            }
            @Override
            public void stop() {}
        };

        //skills keymapping for avatars
        addKeyPressMapping(bindWoundSkill,KeyEvent.VK_1);
        addKeyPressMapping(firstSkill,KeyEvent.VK_2);
        addKeyPressMapping(secondSkill,KeyEvent.VK_3);
        addKeyPressMapping(thirdSkill,KeyEvent.VK_4);
        addKeyPressMapping(genericAttack,KeyEvent.VK_5);
//        addKeyPressMapping(fourthSkill,KeyEvent.VK_5);

        // TODO: Testing opening a random overlay toast view
        addKeyPressMapping(openToastTestView, KeyEvent.VK_L);

        // Open Inventory
        addKeyPressMapping(openInventory, KeyEvent.VK_I);

        // Open Equipment
        addKeyPressMapping(openEquipment, KeyEvent.VK_Y);

        //Open Pause Menu
        addKeyPressMapping(openPause, KeyEvent.VK_P);
    }
    //Method is called whenever entity moves. Basically checks what is in the tile through
    //Tile detection and then whether an NPC is detected, it'll paint the interaction
    public void moveAndDetect(Map.Direction direction){
        TileDetection td;
        td = avatar.move(direction);
        NPC npc = td.getNpc();

        if (td.npcDetected()){
            System.out.println("Action is true");

            //Changes the AvatarController in gameview controller to NPCInteractionController
            NPCMenuView npcView = new NPCMenuView(gameView.getScreenWidth(), gameView.getScreenHeight(), gameView.getDisplay(), td.getNpc());
            NPCInteractionController npcIC = new NPCInteractionController(npcView, gameViewController.getStateManager(), npc);

            gameViewController.setSubController(npcIC);
            gameView.initNPCActionView(npcView);
            gameView.renderNPCAction(true);
            System.out.println(td.getNpc());



        }else {
            gameView.renderNPCAction(false);
            gameViewController.setSubController(null);
        }
    }

    protected final void addKeyPressMapping(Task task, int... key) {

        keyPressMapping.put(getKeyIntMapping(key), task);
    }

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

    public void stopMoving(){
        avatar.stopMoving();
    }

    public TileDetection move(Map.Direction direction){
        return avatar.move(direction);
    }

    public void startInteraction(NPC npc){
        System.out.println("Please refactor me");
    }

    //I am so sorry for doing this...
    public Avatar getAvatar(){
        return avatar;
    }

}
