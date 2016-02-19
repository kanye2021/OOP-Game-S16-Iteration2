package utilities;

import java.util.HashMap;

/**
 * Created by aseber on 2/18/16.
 */
public class InputMapping extends HashMap<Integer, Task> {

    private static Task emptyTask = new Task() {

        @Override
        public void run() {}

    };

    public void inputKey(Integer integer) {
        Task task = super.getOrDefault(integer, emptyTask);
        task.run();
    }

}
