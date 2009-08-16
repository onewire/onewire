package by.bsu.onewire.core.sheduler;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Test TaskTimeProcessor implementation.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class TaskTimeProcessorImplTest {

    @Test
    public void testUpdateTime() {
        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurremtTime()).thenReturn(10L);

        TaskProperties properties = mock(TaskProperties.class);
        when(properties.getDelay()).thenReturn(20L);

        TaskContainer container = mock(TaskContainer.class);
        when(container.getProperties()).thenReturn(properties);

        TaskTimeProcessorImpl timeProcessor = new TaskTimeProcessorImpl();
        timeProcessor.setTimeManager(timeManager);
        timeProcessor.updateTaskStartTime(container);

        verify(container).setStartTime(30);
    }

    @Test
    public void testTaskTime() {
        TimeManager timeManager = mock(TimeManager.class);
        when(timeManager.getCurremtTime()).thenReturn(10L, 20L, 40L, 60L);

        TaskProperties properties = mock(TaskProperties.class);
        when(properties.getDelay()).thenReturn(20L);

        TaskContainer container = mock(TaskContainer.class);
        when(container.getProperties()).thenReturn(properties);
        when(container.getStartTime()).thenReturn(40L);
        
        TaskTimeProcessorImpl timeProcessor = new TaskTimeProcessorImpl();
        timeProcessor.setTimeManager(timeManager);
        
        assertFalse("Early task execution",timeProcessor.isTaskTime(container));
        assertFalse("Early task execution",timeProcessor.isTaskTime(container));
        assertTrue("Late task execution",timeProcessor.isTaskTime(container));
        assertTrue("Late task execution",timeProcessor.isTaskTime(container));
    }
}
