package by.bsu.onewire.core.sheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleShedulerImpl implements Sheduler {

    protected BlockingQueue<Task> queue;

    public static final int MAX_QUEUE_CAPACITY = 20;

    /**
     * Public constructor, create queue.
     */
    public SimpleShedulerImpl() {
        queue = new ArrayBlockingQueue<Task>(MAX_QUEUE_CAPACITY);
    }

    @Override
    public void addTask(Task task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeNextTask() {
        try {
            Task task = queue.take();
            task.execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
