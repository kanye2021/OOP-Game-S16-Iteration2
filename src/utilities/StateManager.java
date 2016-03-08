package utilities;

import views.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Created by Bradley on 2/17/16.
 */
public class StateManager implements ActionListener{
    private Display display;
    private InputManager inputManager;
    private Stack<State> stateStack;
    public Timer gameTimer;

    public StateManager(Display display, InputManager inputManager, int frameRate){
        this.display = display;
        this.inputManager = inputManager;
        this.stateStack = new Stack<>();

        gameTimer = new Timer(frameRate, this);
        gameTimer.start();
    }

    public void setActiveState(State state){
        stateStack.push(state);
        state.activate(inputManager, display);
    }

    public void goToPreviousState(){
        if(stateStack.size() >= 2){
            stateStack.pop();
            setActiveState(stateStack.peek());
        }
    }



    public void refreshState() {
        display.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        stateStack.peek().update();
        display.repaint();
    }
}
