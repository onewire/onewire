package by.bsu.onewire.core.sheduler;

/**
 * Simple implementation of <code>TaskTimeProcessor</code>, use external
 * <code>TimeManager</code> to determine current time.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class TaskTimeProcessorImpl implements TaskTimeProcessor {

    protected TimeManager timeManager;

    /**
     * Check if current time is greater or equal than task start time.
     */
    @Override
    public boolean isTaskTime(TaskContainer taskContainer) {
        long currentTime = timeManager.getCurremtTime();
        return currentTime >= taskContainer.getStartTime();
    }

    /**
     * Update task start time. Use simple algorithm, add delay to current time.
     */
    @Override
    public void updateTaskStartTime(TaskContainer taskContainer) {
        long currentTime = timeManager.getCurremtTime();
        long delay = taskContainer.getProperties().getDelay();
        long startTime = currentTime + delay;
        taskContainer.setStartTime(startTime);
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }
}
