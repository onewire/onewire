package by.bsu.onewire.core.sheduler;

/**
 * Utility class retrieve current time in milliseconds.
 * @author Aliaksandr Zlobich
 *
 */
public class TimeManagerImpl implements TimeManager {

    /**
     * Return current time in millisecond, use <code>System.currentTimeMillis()</code>
     * @see System.currentTimeMillis
     */
    @Override
    public long getCurremtTime() {
        return System.currentTimeMillis();
    }

}
