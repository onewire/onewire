package by.bsu.onewire.core.sheduler.thread;

import by.bsu.onewire.core.sheduler.SimpleSchedulerImpl;

public class RepeatSchedulerThreadBase {

    protected boolean continueExecution;
    protected SimpleSchedulerImpl scheduler;

    public RepeatSchedulerThreadBase(SimpleSchedulerImpl scheduler) {
        continueExecution = true;
        this.scheduler = scheduler;
    }

    public boolean isContinueExecution() {
        return continueExecution;
    }

    public void setContinueExecution(boolean continueExecution) {
        this.continueExecution = continueExecution;
    }

}