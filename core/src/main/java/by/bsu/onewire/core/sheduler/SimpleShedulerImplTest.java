package by.bsu.onewire.core.sheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class SimpleShedulerImplTest {

    protected SimpleShedulerImpl sheduler;

    @Before
    public void initSheduler() {
        sheduler = new SimpleShedulerImpl();
    }

    /**
     * Add task into scheduler and check if this task execute.
     */
    @Test
    public void singleTaskExecution() {
        Task task = mock(Task.class);
        sheduler.addTask(task);
        sheduler.executeNextTask();

        verify(task).execute();
    }

    /**
     * Add a few tasks into scheduler and check if this tasks execute in correct
     * order.
     */
    public void multipluTasksExecution() {
        Task one = mock(Task.class);
        Task two = mock(Task.class);
        Task three = mock(Task.class);
        
        sheduler.addTask(one);
        sheduler.addTask(two);
        sheduler.addTask(three);
        
        sheduler.executeNextTask();
        sheduler.executeNextTask();
        sheduler.executeNextTask();
        
        InOrder inOrder = inOrder(one, two, three);
        inOrder.verify(one).execute();
        inOrder.verify(two).execute();
        inOrder.verify(three).execute();
        
    }
}
