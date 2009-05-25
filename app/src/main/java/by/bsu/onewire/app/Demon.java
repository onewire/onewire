package by.bsu.onewire.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.bsu.onewire.core.network.extensions.SearchDevicesTask;
import by.bsu.onewire.core.sheduler.SimpleSchedulerImpl;
import by.bsu.onewire.core.sheduler.TaskProperties;
import by.bsu.onewire.core.sheduler.thread.ExecuteTasksThread;
import by.bsu.onewire.core.sheduler.thread.RepeatTasksThread;

public class Demon {

    public static void main(String[] args) throws InterruptedException {
        final AbstractApplicationContext context = new ClassPathXmlApplicationContext(new String []{"app-context.xml", "signaling-module-context.xml"});
        context.registerShutdownHook();

        SimpleSchedulerImpl scheduler = (SimpleSchedulerImpl) context.getBean("scheduler");
        scheduler.addTask(new SearchDevicesTask(), new TaskProperties(true, 200));
        
        RepeatTasksThread repeatThread = new RepeatTasksThread(scheduler);
        ExecuteTasksThread executeThread = new ExecuteTasksThread(scheduler);
        
        new Thread(repeatThread).start();
        
        final Thread execute = new Thread(executeThread);
        execute.start();
        execute.join();
    }

}
