package controllers;

import controllers.NPCInteractions.NPCMenuController;
import controllers.entityControllers.AvatarController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.map.Map;

import models.skills.ActiveSkill;
import models.skills.Skill;
import models.skills.SkillList;

import utilities.*;
import views.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class GameViewController extends ViewController{

    //TODO: Map avatar movement to the correct numpad keys

    private ArrayList<NPC> npcList;
    private AvatarController avatarController;


    // Both of these are used to handle dragging the viewport around.
    private boolean mousePressed;
    private Point mouseStartLocation;
    private Point offset;
    private Point lastOffset;

    //Must be able to load and save from the GVC
    private GameLoader gameLoader;
    private GameSaver gameSaver;

    public GameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        npcList = new ArrayList<>();
        mouseStartLocation = new Point(0, 0);
        mousePressed = false;
        offset = new Point(0, 0);
        lastOffset = new Point(0, 0);
    }

    public void setAvatarController(AvatarController controller){
        avatarController = controller;
    }
    public void initViewports(Map map, Avatar avatar, ArrayList<NPC> npcList){
        ((GameView)view).initAreaViewport(map, avatar);
        ((GameView)view).initStatusViewport(avatar.getStats());
        ((GameView)view).initSkillViewport(avatar);

    }


    public void addSubState(SubState s) {
        ((GameView)view).addSubState(s);
    }
    public void popTopSubState() {
        ((GameView)view).popTopSubState();
    }
    public void addToastWithDefaultCloseKeyBindOfX(SubState s) {
        addSubState(s);
        // Dismiss the toast with "X" Toast
        Task dismissTask = new Task() {
            @Override
            public void run() {
                Toast.popToast();
            }

            @Override
            public void stop() {}
        };
        // Default to close a Toast is "X"
        this.addKeyPressMapping(dismissTask, KeyEvent.VK_X);
    }

//    public void popSubState() {
//        ((GameView)view).popTopSubState();
//    }
    public void insertSubState(SubState s, int index) {
        ((GameView)view).insertSubState(s, index);
    }

    @Override
    public final void handleKeyPress(KeyEvent e) {

        if (!((GameView)view).passInputToSubstate(e))
            super.handleKeyPress(e);

    }

    @Override
    public final void handleKeyRelease(KeyEvent e){
        super.handleKeyRelease(e);

        if(avatarController!=null){
            avatarController.handleKeyRelease(e);
        }
    }

    @Override

    protected void initKeyPressMapping() {
        Task task = new Task() {
            @Override
            public void run() {
                ((GameView)view).toggleDetailedStats();
            }

            @Override
            public void stop() {

            }
        };

        addKeyPressMapping(task, KeyEvent.VK_T);

        task = new Task() {
            @Override
            public void run() {
                ((GameView)view).toggleDebugInformation();
            }

            @Override
            public void stop() {

            }
        };

        addKeyPressMapping(task, KeyEvent.VK_M);

        task = new Task() {
            @Override
            public void run() {
                avatarController.setMovementDirection(Map.Direction.NORTH);
            }

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_W);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD8);

        task = new Task() {
            @Override
            public void run() { avatarController.setMovementDirection(Map.Direction.NORTH_WEST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_Q);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD7);

        task = new Task() {
            @Override
            public void run() { avatarController.setMovementDirection(Map.Direction.SOUTH_WEST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_Z);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD1);

        task = new Task() {
            @Override
            public void run() { avatarController.setMovementDirection(Map.Direction.SOUTH);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_S);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD2);

        task = new Task() {
            @Override
            public void run() { avatarController.setMovementDirection(Map.Direction.SOUTH_EAST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_C);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD3);

        task = new Task() {
            @Override
            public void run() { avatarController.setMovementDirection(Map.Direction.NORTH_EAST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_E);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD9);

        //BindWounds
        Task bindWounds = new Task() {
            @Override
            public void run() {
                avatarController.useBindWounds();
            }
            @Override
            public void stop() {}
        };

        //FirstSkill
        Task firstSkill = new Task() {
            @Override
            public void run(){
                avatarController.useFirstSkill();
            }
            @Override
            public void stop(){}
        };


        //Second Skill
        Task secondSkill = new Task() {
            @Override
            public void run(){
                avatarController.useSecondSkill();
            }
            @Override
            public void stop(){}
        };



        //Third Skill
        Task thirdSkill = new Task() {
            @Override
            public void run(){
                avatarController.useThirdSkill();
            }
            @Override
            public void stop(){}
        };


        //Fourth Skill
        Task fourthSkill = new Task() {
            @Override
            public void run(){
                avatarController.useFourthSkill();
            }
            @Override
            public void stop(){}
        };

        //Fifth Skill
        Task fifthSkill = new Task() {
            @Override
            public void run(){
                avatarController.useFifthSkill();
            }
            @Override
            public void stop(){}
        };


        //Open equip menu
        Task openEquipment = new Task() {
            @Override
            public void run() {
                EquipmentView equipmentView = new EquipmentView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
                EquipmentViewController equipmentViewController = new EquipmentViewController(equipmentView, getStateManager(), avatarController.getAvatar());
                SubState equipmentSubState = new SubState(equipmentViewController, equipmentView);
                // Add closing task.
                equipmentViewController.setCloseEquipmentTask(new Task() {
                    @Override
                    public void run() { equipmentSubState.dismiss(); }

                    @Override
                    public void stop() { }
                });
                // Add the substate
                addSubState(equipmentSubState);
            }
            @Override
            public void stop() {}
        };

        //Open Pause
        Task openPause = new Task() {
            public void run(){
                System.out.println("1:Am I in");
                PauseView pauseView = new PauseView(getScreenWidth(), getScreenHeight(), getDisplay());
                PauseViewController pauseViewController = new PauseViewController(pauseView, getStateManager(), avatarController.getAvatar(), GameViewController.this);

                SubState pauseSubstate = new SubState(pauseViewController, pauseView);
                // Add closing task.
                pauseViewController.setClosePause(new Task() {
                    @Override
                    public void run() {
                        System.out.println("2:Did I close ");
                        pauseSubstate.dismiss();
                    }

                    @Override
                    public void stop() {}
                });
                // Add the substate
                addSubState(pauseSubstate);
            }
            @Override
            public void stop(){}
        };
        //Open equip menu
        Task unMount = new Task() {
            @Override
            public void run() {
                avatarController.unMount();
            }
            @Override
            public void stop() {}
        };
        addKeyPressMapping(unMount, KeyEvent.VK_K);

        Task openInventory = new Task() {
            @Override
            public void run() {
                InventoryView inventoryView = new InventoryView(getScreenWidth(), getScreenHeight(), getDisplay());
                InventoryViewController inventoryViewController = new InventoryViewController(inventoryView, getStateManager(), avatarController.getAvatar());
                SubState inventorySubState = new SubState(inventoryViewController, inventoryView);
                // Add closing task.
                inventoryViewController.setCloseInventory(new Task() {
                    @Override
                    public void run() { inventorySubState.dismiss(); }

                    @Override
                    public void stop() { }
                });
                addSubState(inventorySubState);
            }
            @Override
            public void stop() {}
        };

        //InventoryView
        addKeyPressMapping(openInventory, KeyEvent.VK_I);

        //EquipmentView
        addKeyPressMapping(openEquipment, KeyEvent.VK_Y);

        //PauseView
        addKeyPressMapping(openPause,KeyEvent.VK_P);

    }

    public void initSkillKeyBindMappings() {
        // Get the avatar
        Avatar avatar = avatarController.getAvatar();

        // Get his skills
        SkillList skills = avatarController.getAvatarsSkills();

        // Available default key bindings.
        // Most skills an occupation has is 6.
        // We will assign each key to each skill incrementally, while looping over the avatar's skills.
        int skillKeys[] = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6 };

        int i = 0;
        for (Skill skill : skills) {

            // Only ActiveSkills need to have a Task.
            if (skill.isActive()) {
                ActiveSkill activeSkill = (ActiveSkill)skill;
                Task skillActivate = new Task() {
                    @Override
                    public void run() {
                        // Call on activate upon executing
                        activeSkill.onActivate(avatar);
                    }
                    @Override
                    public void stop() {}
                };
                // Set the default keybind on the skill
                activeSkill.setKeyBind( skillKeys[i] );
                // Add the mapping to game VC
                addKeyPressMapping(skillActivate, activeSkill.getKeyBind());
                i++;
            }

        }

    }
    @Override
    public void handleMouseDragged(java.awt.event.MouseEvent e){
        if(!mousePressed){
            mousePressed = true;
            mouseStartLocation = new Point(e.getXOnScreen(), e.getYOnScreen());
        }

        //System.out.println("MouseDragged");
        offset = new Point(e.getXOnScreen(), e.getYOnScreen());
        offset.translate((int)(-mouseStartLocation.getX()), (int)(-mouseStartLocation.getY()));
        offset.translate((int)lastOffset.getX(), (int)lastOffset.getY());
        ((GameView)view).setAreaViewportOffset(offset);
    }

    @Override
    public void handleMouseReleased(java.awt.event.MouseEvent e){
        mousePressed = false;
        lastOffset = offset;
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {
        ((GameView)view).handleMouseClick(e);
    }

    //Wrappers shits for things that have handles to GameVC and need screen dimensions + Display to create otha views
    public int getScreenWidth() {
        return view.getScreenWidth();
    }

    public int getScreenHeight() {
        return view.getScreenHeight();
    }

    public Display getDisplay() {
        return view.getDisplay();
    }

    //Method is called whenever avatar moves. Basically checks what is in the tile through
    //Tile detection and then whether an NPC is detected, it'll paint the interaction

    public void update(){
        moveAndDetect();
    }

    public boolean avatarDied(){
        return !avatarController.avatarIsAlive();
    }

    public void gameOver(){
        GameOverView gameOverView = new GameOverView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay());
        GameOverViewController gameOverViewController = new GameOverViewController(gameOverView, stateManager);
        stateManager.setActiveState(new State(gameOverViewController, gameOverView));
    }

    public void moveAndDetect(){

        // Tell the avatar controller to move the avatar.
        TileDetection td;
        td = avatarController.move();

        if (td != null) {

            // Return the viewport back to center
            mousePressed = false;
            lastOffset.setLocation(0, 0);
            ((GameView)view).setAreaViewportOffset(new Point(0, 0));

            if (td.npcDetected()) {
                NPC npc = (NPC) td.getEntity();

                //Changes the AvatarController in gameview controller to NPCInteractionController
                NPCMenuView npcView = new NPCMenuView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay(), npc);
                NPCMenuController npcIC = new NPCMenuController(npcView, getStateManager(), this, npc, avatarController);

                SubState npcActionSubState = new SubState(npcIC, npcView);
                // Add closing task.
                npcIC.setClose(new Task() {
                    @Override
                    public void run() { npcActionSubState.dismiss(); }

                    @Override
                    public void stop() { }
                });
                // Add the substate
                addSubState(npcActionSubState);
                avatarController.startInteraction(npc);
            }
        }
    }


    //I am so sorry for doing this...
    // lol i did it too :)) ^^
    public Avatar getAvatar() {
        return avatarController.getAvatar();
    }


}

