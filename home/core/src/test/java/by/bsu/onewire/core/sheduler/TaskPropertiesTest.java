package by.bsu.onewire.core.sheduler;

import org.junit.Test;
import org.junit.Assert;

/**
 * Test <code>equals</code> method of <code>TaskProperties</code> class.
 * @author Aliaksandr Zlobich
 *
 */
public class TaskPropertiesTest {

    @Test
    public void testNullEquals(){
        TaskProperties properties = new TaskProperties();
        Assert.assertFalse(properties.equals(null));
    }
    
    @Test
    public void testSameObject(){
        TaskProperties properties = new TaskProperties();
        Assert.assertTrue(properties.equals(properties));
    }
    
    @Test
    public void testEqualObjectObject(){
        TaskProperties first = new TaskProperties();
        final int interval = 10;
        first.setRepeat(false);
        first.setDelay(interval);
        TaskProperties second = new TaskProperties();
        second.setRepeat(false);
        second.setDelay(interval);
        Assert.assertTrue(first.equals(second));
        
        final int secondInterval = 10;
        first = new TaskProperties();
        first.setRepeat(true);
        first.setDelay(secondInterval);
        second = new TaskProperties();
        second.setRepeat(true);
        second.setDelay(secondInterval);
        Assert.assertTrue(first.equals(second));
    }
    
    @Test
    public void testNotEqualObjectObject(){
        TaskProperties first = new TaskProperties();
        first.setRepeat(true);
        TaskProperties second = new TaskProperties();
        second.setRepeat(false);
        Assert.assertFalse(first.equals(second));
        
        first = new TaskProperties();
        first.setDelay(10);
        second = new TaskProperties();
        second.setDelay(20);
        Assert.assertFalse(first.equals(second));
    }
}
