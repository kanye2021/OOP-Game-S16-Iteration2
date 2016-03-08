package utilities;

import controllers.GameViewController;
import views.ToastView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sergiopuleri on 3/7/16.
 */
public final class Toast {
    private static GameViewController gameViewController;


    // Can only set ONCE
    public static void setGameViewController(GameViewController gameVC) {
        if (gameViewController == null) {
            gameViewController = gameVC;
        }
    }

    public static void createToast(String message) {
        ToastView toastView = new ToastView(gameViewController.getScreenWidth(), gameViewController.getScreenWidth(), gameViewController.getDisplay(), message);
        // Pass null so that GameVC handles input.
        SubState toastSubState = new SubState(null, toastView);
        gameViewController.addToastWithDefaultCloseKeyBindOfX(toastSubState);
        //gameViewController.turnOffSubState();
    }

    public static void createToastWithTimer(String message, int time) {
        ToastView toastView = new ToastView(gameViewController.getScreenWidth(), gameViewController.getScreenWidth(), gameViewController.getDisplay(), message);
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
}
