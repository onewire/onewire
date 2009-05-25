package by.bsu.onewire.core.sheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import by.bsu.onewire.core.network.NetworkManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SimpleSchedulerImplTest {

    protected SimpleSchedulerImpl scheduler;

    @Before
    public void initSheduler() {
        scheduler = new SimpleSchedulerImpl();
        TaskTimeProcessor processor = mock(TaskTimeProcessor.class);
        NetworkManager networkManager = mock(NetworkManager.class);
        scheduler.setNetworkManager(networkManager);
        when(processor.isTaskTime(any(TaskContainer.class))).thenReturn(true);
        scheduler.setTimeProcessor(processor);

    }

    /**
     * Add task into scheduler and check if this task execute.
     */
    @Test
    public void singleTaskExecution() {
        Task task = mock(Task.class);
        scheduler.addTask(task);
        scheduler.executeNextTask();

        verify(task).execute();
    }

    /**
     * Add a few tasks into scheduler and check if this tasks execute in correct
     * order.
     */
    @Test
    public void multiplyTasksExecution() {
        Task one = mock(Task.class);
        Task two = mock(Task.class);
        Task three = mock(Task.class);

        scheduler.addTask(one);
        scheduler.addTask(two);
        scheduler.addTask(three);

        scheduler.executeNextTask();
        scheduler.executeNextTask();
        scheduler.executeNextTask();

        InOrder inOrder = inOrder(one, two, three);
        inOrder.verify(one).execute();
        inOrder.verify(two).execute();
        inOrder.verify(three).execute();

    }

    /**
     * Check if single task with repeat option, execute correct number of times.
     */
    @Test
    public void singleTaskRepeatExecution() {
        Task task = mock(Task.class);
        scheduler.addTask(task, new TaskProperties(true));
        scheduler.executeNextTask();
        scheduler.processRepeatTasks();
        scheduler.executeNextTask();
        scheduler.processRepeatTasks();
        scheduler.executeNextTask();

        verify(task, times(3)).execute();
    }

    /**
     * Check if two task execute in correct sequence, if repeat option has been
     * turned on for first task.
     */
    @Test
    public void twoTasksRepeatSequence() {
        Task first = mock(Task.class);
        Task second = mock(Task.class);

        scheduler.addTask(first, new TaskProperties(true));
        scheduler.addTask(second);

        scheduler.executeNextTask();
        scheduler.processRepeatTasks();
        scheduler.executeNextTask();
        scheduler.processRepeatTasks();
        scheduler.executeNextTask();

        InOrder order = inOrder(first, second);
        order.verify(first).execute();
        order.verify(second).execute();
        order.verify(first).execute();
    }

    @Test
    public void multiThreadTest() throws InterruptedException {
        Task first = mock(Task.class);
        Task second = mock(Task.class);

        Thread executionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    scheduler.executeNextTask();
                }
            }
        });
        executionThread.start();

        scheduler.addTask(first, new TaskProperties(true));
        scheduler.addTask(second);
        Thread.sleep(100);
        scheduler.processRepeatTasks();
        Thread.sleep(100);
        scheduler.processRepeatTasks();

        executionThread.join(5000);
        InOrder order = inOrder(first, second);
        order.verify(first).execute();
        order.verify(second).execute();
        order.verify(first).execute();
    }

    /**
     * Check if two task execute in correct sequence, if repeat option has been
     * turned on for first task.
     */
    @Test
    public void twoTasksDelayRepeatSequence() throws InterruptedException {
        Task first = mock(Task.class);
        Task second = mock(Task.class);

        TaskTimeProcessorImpl processor = new TaskTimeProcessorImpl();
        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurremtTime()).thenReturn(0L, 1L, 103L, 103L, 105L, 203L, 203L);
        processor.setTimeManager(timeManager);
        scheduler.setTimeProcessor(processor);

        scheduler.addTask(first, new TaskProperties(true, 200L));
        scheduler.addTask(second, new TaskProperties(true, 100L));
        scheduler.processRepeatTasks();
        scheduler.processRepeatTasks();

        List<Task> expected = new ArrayList<Task>(4);
        expected.add(first);
        expected.add(second);
        expected.add(second);
        expected.add(first);

        List<Task> actual = new ArrayList<Task>(4);
        List<TaskContainer> list = queueToList(scheduler.queue);
        for (TaskContainer taskContainer : list) {
            actual.add(taskContainer.getTask());
        }
        assertEquals(expected, actual);

    }

    /**
     * Check if scheduler return correct default <code>TaskProperties</code>
     */
    @Test
    public void defaultProperties() {
        TaskProperties expected = new TaskProperties();
        expected.setRepeat(false);
        TaskProperties actual = SimpleSchedulerImpl.getDefaultTaskProperties();
        Assert.assertEquals(expected, actual);
    }

    public Thread createExecutionThread() {
        Thread executionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                scheduler.executeNextTask();
            }
        });
        return executionThread;
    }

    public List<TaskContainer> queueToList(Queue<TaskContainer> queue) {
        List<TaskContainer> list = new ArrayList<TaskContainer>(queue.size());
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        return list;
    }
}
