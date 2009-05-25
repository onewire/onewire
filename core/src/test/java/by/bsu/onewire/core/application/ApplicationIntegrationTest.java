package by.bsu.onewire.core.application;

import org.junit.Test;

import by.bsu.onewire.core.BaseIntegrationTest;
import by.bsu.onewire.core.network.extensions.SearchDevicesTask;
import by.bsu.onewire.core.sheduler.SimpleSchedulerImpl;
import by.bsu.onewire.core.sheduler.TaskProperties;
import by.bsu.onewire.core.sheduler.thread.ExecuteTasksThread;
import by.bsu.onewire.core.sheduler.thread.RepeatTasksThread;

public class ApplicationIntegrationTest extends BaseIntegrationTest {

    @Test
    public void testThreadsExecution() throws InterruptedException {
        SimpleSchedulerImpl scheduler = (SimpleSchedulerImpl) factory.getBean("scheduler");
        scheduler.addTask(new SearchDevicesTask(), new TaskProperties(true, 200));
        RepeatTasksThread repeatThread = new RepeatTasksThread(scheduler);
        ExecuteTasksThread executeThread = new ExecuteTasksThread(scheduler);
        new Thread(repeatThread).start();
        final Thread execute = new Thread(executeThread);
        execute.start();
        Thread.sleep(3000);
        executeThread.setContinueExecution(false);
        repeatThread.setContinueExecution(false);
        execute.join(1000);
    }
}
