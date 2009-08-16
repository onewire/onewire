package by.bsu.onewire.core.sheduler;

/**
 * Common interface for scheduler object. Scheduler should collect task, and run
 * them in correct order in 1-Wire context.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface Scheduler {

    /**
     * Add task in queue. This task will be executed later in 1-Wire context.
     */
    void addTask(Task task);

    /**
     * Add task in queue. This task will be executed later in 1-Wire context.
     * 
     * @param properties
     *            bean object that should contain task execution properties
     */
    void addTask(Task task, TaskProperties properties);

}
