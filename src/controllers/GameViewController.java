package controllers;

import controllers.NPCInteractions.NPCMenuController;
import controllers.entityControllers.AvatarController;
import models.entities.Avatar;
import models.entities.npc.NPC;
import models.map.Map;
import utilities.TileDetection;
import utilities.StateManager;
import utilities.SubState;
import utilities.Task;
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

    //Checks if the view has a toast
    private boolean hasToast;

    // Both of these are used to handle dragging the viewport around.
    private boolean mousePressed;
    private Point mouseStartLocation;
    private Point offset;
    private Point lastOffset;

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
        pushToast();
        // Dismiss the toast with "X" Toast
        Task dismissTask = new Task() {
            @Override
            public void run() {
                s.dismiss();
                popToast();
            }

            @Override
            public void stop() {}
        };
        // Default to close a Toast is "X"
        this.addKeyPressMapping(dismissTask, KeyEvent.VK_X);
    }

    public void pushToast(){ hasToast = true; }
    public void popToast(){ hasToast = false; }

    public void insertSubState(SubState s, int index) {
        ((GameView)view).insertSubState(s, index);
    }

    @Override
    public final void handleKeyPress(KeyEvent e) {

        if(((GameView)view).hasSubState() == true && hasToast != true)
            ((GameView)view).passInputToSubstate(e);
        else
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
                PauseViewController pauseViewController = new PauseViewController(pauseView, getStateManager());
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

//        Task openToastTestView = new Task() {
//            @Override
//            public void run() {
//                ToastView toast = new ToastView(getScreenWidth(), getScreenWidth(), getDisplay(), "Press 'L' to dismiss this toast");
//                // For a "Toast Message" the Game View controller will still be handling input, so pass in null.
//                SubState toastSubState = new SubState(null, toast);
//                // Pass a new inputMapping to the current VC, to handle our interaction within this new SubState:
//                // In this cass the current VC is the GameVC, which passes input to the AvatarVC, so i'm adding this
//                // input mapping to the Avatar Controller.
//                // These input mappings for the new SubState dont need to be created here, if the new substate is the inventory
//                // for example. the inventory VC would handle the new input appings
//                Task openToast = this;
//                GameViewController.this.addKeyPressMapping(new Task() {
//                    @Override
//                    public void run() {
//                        toastSubState.dismiss();
//                        // Re-map the "I" key to open the toast view again
//                        GameViewController.this.addKeyPressMapping(openToast, KeyEvent.VK_L);
//                    }
//                    @Override
//                    public void stop() {}
//                }, KeyEvent.VK_I);
//                // Add the substate
//                addSubState(toastSubState);
//            }
//            @Override
//            public void stop() {}
//        };

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

        //5th Skill
        addKeyPressMapping(fifthSkill,KeyEvent.VK_6);

        //InventoryView
        addKeyPressMapping(openInventory, KeyEvent.VK_I);

        //EquipmentView
        addKeyPressMapping(openEquipment, KeyEvent.VK_Y);

        //PauseView
        addKeyPressMapping(openPause,KeyEvent.VK_P);

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

    public void moveAndDetect(){

        // Return the viewport back to center
        mousePressed = false;
        lastOffset.setLocation(0, 0);
        ((GameView)view).setAreaViewportOffset(new Point(0, 0));

        // Tell the avatar controller to move the avatar.
        TileDetection td;
        td = avatarController.move();

        if (td != null) {
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
//                ((GameView) view).initNPCActionView(npcView);
//                ((GameView) view).renderNPCAction(true);
                avatarController.startInteraction(npc);
            }
        }
    }


}

