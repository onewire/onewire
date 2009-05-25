package by.bsu.onewire.core.sheduler.thread;

import by.bsu.onewire.core.sheduler.SimpleSchedulerImpl;

public class RepeatTasksThread extends RepeatSchedulerThreadBase implements Runnable {

    public RepeatTasksThread(SimpleSchedulerImpl scheduler) {
        super(scheduler);
    }

    @Override
    public void run() {
        while(continueExecution){
            scheduler.processRepeatTasks();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
