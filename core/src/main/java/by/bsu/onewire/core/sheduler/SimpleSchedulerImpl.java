package by.bsu.onewire.core.sheduler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleSchedulerImpl implements Scheduler {

    protected BlockingQueue<TaskContainer> queue;
    protected List<TaskContainer> repeatTasks;

    /**
     * Public constructor, create queue.
     */
    public SimpleSchedulerImpl() {
        queue = new LinkedBlockingQueue<TaskContainer>();
        repeatTasks = new LinkedList<TaskContainer>();
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
            executeTask(task);
            if (needRepeat(container)) {
                repeatTasks.add(container);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    protected void processRepeatTasks(){
        Iterator<TaskContainer> iterator  = repeatTasks.iterator();
        while(iterator.hasNext()){
            TaskContainer container = iterator.next();
            if(readyForExecution(container)){
                if(queue.offer(container)){
                iterator.remove();
                }
            }
        }
    }

    /**
     * Execute task in 1-Wire context.
     */
    protected void executeTask(Task task) {
        task.execute();
    }

    /**
     * Check if task should be executed one more time.
     */
    protected boolean needRepeat(TaskContainer taskContainer) {
        return taskContainer.getProperties().isRepeat();
    }

    /**
     * Check if task is ready for execution.
     */
    protected boolean readyForExecution(TaskContainer taskContainer) {
        return true;
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
