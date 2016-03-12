package utilities;

import controllers.GameViewController;
import views.ToastView;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sergiopuleri on 3/7/16.
 */
public final class Toast {
    private static GameViewController gameViewController;
    private static Stack<SubState> toastStack;

    // Can only set ONCE
    public static void initWithGameViewController(GameViewController gameVC) {
        if (gameViewController == null) {
            gameViewController = gameVC;
        }

        // init the toast stack, i guess this func is almost like a constructor for a static class
        if (toastStack == null) {
            toastStack = new Stack<SubState>();
        }
    }

    public static void createToast(String message) {
        ToastView toastView = new ToastView(gameViewController.getScreenWidth(), gameViewController.getScreenHeight(), gameViewController.getDisplay(), message);
        // Pass null so that GameVC handles input.
        SubState toastSubState = new SubState(null, toastView);
        toastStack.add(toastSubState);
        gameViewController.addToastWithDefaultCloseKeyBindOfX(toastSubState);
    }

    public static void createToastWithTimer(String message, int time) {
        ToastView toastView = new ToastView(gameViewController.getScreenWidth(), gameViewController.getScreenHeight(), gameViewController.getDisplay(), message);
        // Pass null so that GameVC handles input.
        SubState toastSubState = new SubState(null, toastView);
        gameViewController.addSubState(toastSubState);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toastSubState.dismiss();
            }
        }, time);
    }

    public static void popToast() {
        if (!toastStack.isEmpty()) {
            SubState topToast = toastStack.pop();
            topToast.dismiss();
        }

    }

}
