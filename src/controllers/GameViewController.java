package controllers;

import controllers.NPCInteractions.NPCMenuController;
import controllers.entityControllers.AvatarController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.map.Map;
import models.skills.SneakSkills.TileDetection;
import utilities.StateManager;
import utilities.SubState;
import utilities.Task;
import views.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 2/18/16.
 */
public class GameViewController extends ViewController{

    //TODO: Map avatar movement to the correct numpad keys

    private ArrayList<NPC> npcList;
    private AvatarController avatarController;
    private MenuController menuController;
    private ViewController activeSubController;

    // Both of these are used to handle dragging the viewport around.
    private boolean mousePressed;
    private Point mouseStartLocation;
    private Point offset;
    private Point lastOffset;

    public GameViewController(View view, StateManager stateManager){
        super(view, stateManager);
        npcList = new ArrayList<>();
        activeSubController = null;
        menuController = new MenuController(null, stateManager, this);
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
    }


    public void addSubState(SubState s) {
        ((GameView)view).addSubState(s);
    }
    public void addToastWithDefaultCloseKeyBindOfX(SubState s) {
        addSubState(s);
        // Dismiss the toast with "X" Toast
        Task dismissTask = new Task() {
            @Override
            public void run() {
                s.dismiss();
            }

            @Override
            public void stop() {}
        };
        // Default to close a Toast is "X"
        this.addKeyPressMapping(dismissTask, KeyEvent.VK_X);
    }
    public void insertSubState(SubState s, int index) {
        ((GameView)view).insertSubState(s, index);
    }
    public void setSubController(ViewController vc){
        activeSubController = vc;
    }
    @Override
    public final void handleKeyPress(KeyEvent e) {

        //try to pass input to GameView's substate:
        ((GameView)view).passInputToSubstate(e);

        if(activeSubController == null){
            System.out.println("GameView Handle");
            super.handleKeyPress(e);
        }
        else{
            System.out.println("SubController Handle");
            activeSubController.handleKeyPress(e);
        }
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
                moveAndDetect(Map.Direction.NORTH);
            }

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_W);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD8);

        task = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.NORTH_WEST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_Q);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD7);

        task = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.SOUTH_WEST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_Z);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD1);

        task = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.SOUTH);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_S);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD2);

        task = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.SOUTH_EAST);}

            @Override
            public void stop() { avatarController.stopMoving(); }
        };

        addKeyPressMapping(task, KeyEvent.VK_C);
        addKeyPressMapping(task, KeyEvent.VK_NUMPAD3);

        task = new Task() {
            @Override
            public void run() { moveAndDetect(Map.Direction.NORTH_EAST);}

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
            @Override
            public void run() {
                menuController.openPauseMenu();
            }

            @Override
            public void stop() {}
        };

        //BINDINGS:
        //--------

        //Bind Wounds
        addKeyPressMapping(bindWounds, KeyEvent.VK_1);

        //1st Skill
        addKeyPressMapping(firstSkill, KeyEvent.VK_2);

        //2nd Skill
        addKeyPressMapping(secondSkill, KeyEvent.VK_3);

        //3rd Skill
        addKeyPressMapping(thirdSkill, KeyEvent.VK_4);

        //4th Skill
        addKeyPressMapping(fourthSkill, KeyEvent.VK_5);

        //EquipmentView
        addKeyPressMapping(openEquipment, KeyEvent.VK_Y);

        //Pause
        addKeyPressMapping(openPause,KeyEvent.VK_P);

    }
    @Override
    public void handleMouseDragged(java.awt.event.MouseEvent e){
        if(!mousePressed){
            mousePressed = true;
            mouseStartLocation = new Point(e.getXOnScreen(), e.getYOnScreen());
        }

        System.out.println("MouseDragged");
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

    //Method is called whenever entity moves. Basically checks what is in the tile through
    //Tile detection and then whether an NPC is detected, it'll paint the interaction
    public void moveAndDetect(Map.Direction direction){

        // Return the viepowrt back to center
        mousePressed = false;
        lastOffset.setLocation(0, 0);
        ((GameView)view).setAreaViewportOffset(new Point(0, 0));

        TileDetection td;
        td = avatarController.move(direction);
        NPC npc = td.getNpc();

        if (td.npcDetected()){
            System.out.println("Action is true");
            //Changes the AvatarController in gameview controller to NPCInteractionController
            NPCMenuView npcView = new NPCMenuView(view.getScreenWidth(), view.getScreenHeight(), view.getDisplay(), td.getNpc());
            NPCMenuController npcIC = new NPCMenuController(npcView, getStateManager(), this, npc, avatarController);
            setSubController(npcIC);
            ((GameView)view).initNPCActionView(npcView);
            ((GameView)view).renderNPCAction(true);
            avatarController.startInteraction(npc);
        }else {
            turnOffSubState();
        }
    }

    public void turnOffSubState(){//Turns off the view and controller (used from other controllers)
            ((GameView)view).renderNPCAction(false);
            setSubController(null);
        }

}

