package utilities;

import controllers.TalkViewController;
import views.Display;
import views.TalkView;

import java.util.Stack;

/**
 * Created by Bradley on 2/17/16.
 */
public class StateManager {
    private Display display;
    private InputManager inputManager;
    private Stack<State> stateStack;
    private SubState activeTalkState;

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
            setActiveState(stateStack.peek());
        }
    }

    public State top(){
        return this.stateStack.peek();
    }

    public void refreshState() {
        display.repaint();
    }

    public void setActiveTalkState(SubState talkState){
        activeTalkState = talkState;
    }

    public SubState getActiveTalkState(){
        return activeTalkState;
    }

}


