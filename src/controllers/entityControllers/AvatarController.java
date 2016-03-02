package controllers.entityControllers;

import controllers.GameViewController;
import controllers.NPCInteractionController;
import controllers.TestViewController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.map.Map;
import models.occupation.Smasher;
import models.skills.CommonSkills.BindWoundsSkill;
import models.skills.Skill;
import models.skills.SneakSkills.CreepSkill;
import models.skills.SneakSkills.DetectRemoveTrapSkill;
import models.skills.SneakSkills.PickPocketSkill;
import models.skills.SneakSkills.TileDetection;
import models.skills.SummonerSkills.EnchantmentSkill;
import utilities.InputMapping;
import utilities.SubState;
import utilities.Task;
import views.GameView;
import views.NPCActionView;
import views.ToastView;

import java.awt.*;
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
        Task moveNorth = new Task() {
            @Override
            public void run() {
                moveAndDetect(Map.Direction.NORTH);
            }

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveNorthWest = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.NORTH_WEST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveSouthWest = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.SOUTH_WEST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveSouth = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.SOUTH);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveSouthEast = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.SOUTH_EAST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };
        Task moveNorthEast = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.NORTH_EAST);}

            @Override
            public void stop() { avatar.stopMoving(); }
        };

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
        Task secondSkill = new Task(){
            @Override
            public void run() {
                //if smasher, get first skill
                if(avatar.getOccupation().contains("Smasher")){
                    //Technically the Smasher class has no actives

                }else if(avatar.getOccupation().contains("Summoner")){
                    //first skill should be enchantment here
                    Skill secondSkill = avatar.getSpecificSkill(Skill.SkillDictionary.STAFF);
                    System.out.println(secondSkill);
                    EnchantmentSkill staffSkill = (EnchantmentSkill) secondSkill;
                    staffSkill.onActivate(avatar);

                }else if(avatar.getOccupation().contains("Sneak")){
                    //first skill should be something..
                    Skill secondSkill = avatar.getSpecificSkill(Skill.SkillDictionary.DETECT_REMOVE_TRAP);
                    System.out.println(secondSkill);
                    DetectRemoveTrapSkill detectSkill = (DetectRemoveTrapSkill) secondSkill;
                    detectSkill.onActivate(avatar);

                }else{
                    System.out.println("What are you");
                }

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


                }else if(avatar.getOccupation().contains("Sneak")){
                    //first skill should be something..
                    //first skill should be something..
                    Skill thirdSkill = avatar.getSpecificSkill(Skill.SkillDictionary.PICK_POCKET);
                    System.out.println(thirdSkill);
                    PickPocketSkill pickPocketSkill = (PickPocketSkill) secondSkill;
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
                GameView gameView = (GameView)gameViewController.getView();
                ToastView toast = new ToastView(gameView.getScreenWidth(), gameView.getScreenWidth(), gameView.getDisplay(), "Press 'I' to dismiss this toast");
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
                        AvatarController.this.addKeyPressMapping(openToast, KeyEvent.VK_I);
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
        Task clearSubStates= new Task() {
            @Override
            public void run() {
                GameView gameView = (GameView) gameViewController.getView();
                gameView.clearSubStates();
            }
            @Override
            public void stop() {}
        };

        addKeyPressMapping(moveNorth, KeyEvent.VK_W);
        addKeyPressMapping(moveNorthWest, KeyEvent.VK_Q);
        addKeyPressMapping(moveSouthWest, KeyEvent.VK_Z);
        addKeyPressMapping(moveSouth, KeyEvent.VK_S);
        addKeyPressMapping(moveSouthEast, KeyEvent.VK_C);
        addKeyPressMapping(moveNorthEast, KeyEvent.VK_E);

        //skills keymapping for avatars
        addKeyPressMapping(bindWoundSkill,KeyEvent.VK_1);
        addKeyPressMapping(firstSkill,KeyEvent.VK_2);
//        addKeyPressMapping(secondSkill,KeyEvent.VK_3);
//        addKeyPressMapping(thirdSkill,KeyEvent.VK_4);
//        addKeyPressMapping(fourthSkill,KeyEvent.VK_5);

        addKeyPressMapping(moveNorthWest, KeyEvent.VK_NUMPAD7);
        addKeyPressMapping(moveNorth, KeyEvent.VK_NUMPAD8);
        addKeyPressMapping(moveNorthEast, KeyEvent.VK_NUMPAD9);
        addKeyPressMapping(moveSouthEast, KeyEvent.VK_NUMPAD3);
        addKeyPressMapping(moveSouth, KeyEvent.VK_NUMPAD2);
        addKeyPressMapping(moveSouthWest, KeyEvent.VK_NUMPAD1);

        // TODO: Testing opening a random overlay toast view
        addKeyPressMapping(openToastTestView, KeyEvent.VK_I);
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
            NPCActionView npcView = new NPCActionView(gameView.getScreenWidth(), gameView.getScreenHeight(), gameView.getDisplay(), td.getNpc());
            NPCInteractionController npcIC = new NPCInteractionController(npcView, gameViewController.getStateManager(), npc);
            gameViewController.setSubController(npcIC);
            gameView.initNPCActionView(npcView);
            gameView.renderNPCAction(true);
            System.out.println(td.getNpc());
            avatar.startInteraction(npc);
        }else {
//            System.out.println("Action is false");
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

}
