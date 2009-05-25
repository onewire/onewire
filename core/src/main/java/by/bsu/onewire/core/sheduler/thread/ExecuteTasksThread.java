package by.bsu.onewire.core.sheduler.thread;

import by.bsu.onewire.core.sheduler.SimpleSchedulerImpl;

public class ExecuteTasksThread extends RepeatSchedulerThreadBase implements Runnable{

    public ExecuteTasksThread(SimpleSchedulerImpl scheduler) {
        super(scheduler);
    }

    @Override
    public void run() {
        while(continueExecution){
            scheduler.executeNextTask();
        }
    }

}
