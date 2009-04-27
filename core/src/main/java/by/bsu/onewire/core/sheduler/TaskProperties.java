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
    
    public TaskProperties(){
        
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

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof TaskProperties) {
            TaskProperties anotherProperties = (TaskProperties) anObject;
            boolean result = true;
            result &= (this.repeat == anotherProperties.repeat);
            return result;
        }
        return false;
    }

}
