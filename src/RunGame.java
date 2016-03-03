/**
 * Created by Bradley on 2/17/16.
 */

import controllers.StartMenuViewController;
import utilities.InputManager;
import utilities.State;
import utilities.StateManager;
import views.Display;
import views.StartMenuView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class RunGame extends JFrame {

    private Display display;
    private InputManager inputManager;
    private final int INITIAL_WIDTH = 1200;
    private final int INITIAL_HEIGHT = 800;

    @Override
    public void setExtendedState(int state) {
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public RunGame() {
        init();
    }

    private void init() {

        // Create the display and input manager
        display = new Display(INITIAL_WIDTH, INITIAL_HEIGHT);
        inputManager = new InputManager();

        // Create the state manager.
        StateManager stateManager = new StateManager(display, inputManager);

        // Setup the first state (Start Menu).
        StartMenuView startMenuView = new StartMenuView(INITIAL_WIDTH, INITIAL_HEIGHT, display);
        StartMenuViewController startMenuViewController = new StartMenuViewController(startMenuView, stateManager);
        State startState = new State(startMenuViewController, startMenuView);

        // Initialize the JFrame
        add(display);
        pack(); // May or may not need this.

        // Setup the keyboard, resize, and mouse listener
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(inputManager);
        addComponentListener(inputManager);
        addMouseListener(inputManager);
        addMouseMotionListener(inputManager);

        setTitle("RPG GAME");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the default state.
        stateManager.setActiveState(startState);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RunGame ex = new RunGame();
                ex.setVisible(true);
            }
        });
    }
}