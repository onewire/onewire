package by.bsu.onewire.core.sheduler;

/**
 * Processor determine should task execute now or not.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface TaskTimeProcessor {
    /**
     * Calculate task start time.
     */
    void updateTaskStartTime(TaskContainer taskContainer);

    /**
     * Check if task should execute now.
     */
    boolean isTaskTime(TaskContainer taskContainer);
}
