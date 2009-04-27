package by.bsu.onewire.core.sheduler;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class SimpleSchedulerImplTest {

    protected SimpleSchedulerImpl scheduler;

    @Before
    public void initSheduler() {
        scheduler = new SimpleSchedulerImpl();
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
     * Check if scheduler return correct default <code>TaskProperties</code>
     */
    public void defaultProperties(){
        TaskProperties expected = new TaskProperties();
        expected.setRepeat(false);
        TaskProperties actual = SimpleSchedulerImpl.getDefaultTaskProperties();
        Assert.assertEquals(expected, actual);
    }
}
