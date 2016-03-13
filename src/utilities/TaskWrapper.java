package utilities;

/**
 * Created by sergiopuleri on 3/13/16.
 */
public class TaskWrapper {
    Task task;
    String descriptor;

    public TaskWrapper(Task task, String descriptor) {
        this.task = task;
        this.descriptor = descriptor;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
