package by.bsu.onewire.core.sheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleSchedulerImpl implements Scheduler {

    protected BlockingQueue<TaskContainer> queue;

    public static final int MAX_QUEUE_CAPACITY = 20;

    /**
     * Public constructor, create queue.
     */
    public SimpleSchedulerImpl() {
        queue = new ArrayBlockingQueue<TaskContainer>(MAX_QUEUE_CAPACITY);
    }

    @Override
    public void addTask(Task task) {
        addTask(task, getDefaultTaskProperties());
    }

    @Override
    public void executeNextTask() {
        try {
            TaskContainer container = queue.take();
            Task task = container.getTask();
            task.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(Task task, TaskProperties properties) {
        try {
            queue.put(new TaskContainer(task, properties));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get default <code>TaskProperties</code> object for this
     * <code>Scheduler</code> implementation.
     */
    public static final TaskProperties getDefaultTaskProperties() {
        TaskProperties properties = new TaskProperties();
        properties.setRepeat(false);
        return properties;
    }
}
