package by.bsu.onewire.core.modules;

import by.bsu.onewire.core.sheduler.Scheduler;

/**
 * Base class for all smart house modules.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class ModuleBase {
    private Scheduler scheduler;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
