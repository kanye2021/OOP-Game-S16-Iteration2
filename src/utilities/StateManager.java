package utilities;

import views.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Created by Bradley on 2/17/16.
 */
public class StateManager{
    private Display display;
    private InputManager inputManager;
    private Stack<State> stateStack;

    public StateManager(Display display, InputManager inputManager){
        this.display = display;
        this.inputManager = inputManager;
        this.stateStack = new Stack<>();
    }

    public void setActiveState(State state){
        stateStack.push(state);
        state.activate(inputManager, display);
    }


    public void goToPreviousState() {
        if (stateStack.size() >= 2) {
            stateStack.pop();
            //setActiveState(stateStack.peek());
            stateStack.peek().activate(inputManager,display);
        }
    }

    public void tick() {
        stateStack.peek().update();
        display.repaint();

    }
    public State getTop(){
        return stateStack.peek();
    }
}


