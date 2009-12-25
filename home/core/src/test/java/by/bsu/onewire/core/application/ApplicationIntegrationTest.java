package by.bsu.onewire.core.application;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bsu.onewire.core.network.extensions.SearchDevicesTask;
import by.bsu.onewire.core.sheduler.SimpleSchedulerImpl;
import by.bsu.onewire.core.sheduler.TaskProperties;
import by.bsu.onewire.core.sheduler.thread.ExecuteTasksThread;
import by.bsu.onewire.core.sheduler.thread.RepeatTasksThread;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
public class ApplicationIntegrationTest {

    @Resource
    SimpleSchedulerImpl scheduler;

    @Test
    public void testThreadsExecution() throws InterruptedException {
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
