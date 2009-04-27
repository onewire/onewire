package by.bsu.onewire.core.sheduler;

/**
 * Bean class contains task properties, this properties use for correct task
 * execution sequence.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class TaskProperties {

    private boolean repeat;

    private long delay;

    public TaskProperties() {

    }

    public TaskProperties(boolean repeat) {
        super();
        this.repeat = repeat;
    }

    /**
     * Check if task should be repeated.
     */
    public boolean isRepeat() {
        return repeat;
    }

    /**
     * Determine should task repeat or not.
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    /**
     * Get interval before task should be repeated.
     */
    public long getDelay() {
        return delay;
    }

    /**
     * Set time interval before two executions of the task
     * 
     * @param interval
     *            the interval time in millis
     */
    public void setDelay(long interval) {
        this.delay = interval;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof TaskProperties) {
            TaskProperties anotherProperties = (TaskProperties) anObject;
            if (this.repeat != anotherProperties.repeat) {
                return false;
            }
            if (this.delay != anotherProperties.delay) {
                return false;
            }
            return true;
        }
        return false;
    }

}
