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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class RunGame extends JFrame implements ActionListener{

    private Display display;
    private InputManager inputManager;
    private StateManager stateManager;
    private final int INITIAL_WIDTH = 1200;
    private final int INITIAL_HEIGHT = 800;
    private Timer gameTimer;
    private final int TIMER_DELAY = 100;

    @Override
    public void setExtendedState(int state) {
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public RunGame() {
        init();
    }

    private void init() {

        // Create the display, input manager, StateManager, and timer.
        display = new Display(INITIAL_WIDTH, INITIAL_HEIGHT);
        inputManager = new InputManager();
        stateManager = new StateManager(display, inputManager);
        gameTimer = new Timer(TIMER_DELAY, this);


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

        // Start the timer.
        gameTimer.start();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.tick();
    }
}