package utilities;

import java.util.HashMap;

/**
 * Created by aseber on 2/18/16.
 */
public class InputMapping extends HashMap<Integer, TaskWrapper> {

    private static Task emptyTask = new Task() {

        @Override
        public void run() {}

        @Override
        public void stop() {}

    };

    public void inputKey(Integer integer) {
        TaskWrapper task = super.getOrDefault(integer, new TaskWrapper(emptyTask, "empty task"));
        task.getTask().run();
    }

    public void keyReleased(Integer integer) {
        TaskWrapper task = super.getOrDefault(integer, new TaskWrapper(emptyTask, "empty task"));
        task.getTask().stop();
    }

}
