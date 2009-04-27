package by.bsu.onewire.core.sheduler;

public class TaskContainer {
    private Task task;

    private TaskProperties properties;

    public TaskContainer(Task task, TaskProperties properties) {
        this.properties = properties;
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskProperties getProperties() {
        return properties;
    }

    public void setProperties(TaskProperties properties) {
        this.properties = properties;
    }

}
