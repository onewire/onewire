package by.bsu.onewire.core.sheduler;

/**
 * Container class, use by scheduler to keep task, task properties and other
 * task information that uses for correct task processing.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class TaskContainer {
    private Task task;

    private TaskProperties properties;

    private long startTime;

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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

}
