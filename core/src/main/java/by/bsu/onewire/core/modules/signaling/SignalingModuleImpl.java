package by.bsu.onewire.core.modules.signaling;

import java.util.ArrayList;
import java.util.List;

import by.bsu.onewire.core.modules.ModuleBase;
import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;
import by.bsu.onewire.core.sheduler.Task;
import by.bsu.onewire.core.sheduler.TaskProperties;

public class SignalingModuleImpl extends ModuleBase implements SignalingModule {

    private List<SignalingElement> elements;
    
    public SignalingModuleImpl() {
        elements = new ArrayList<SignalingElement>();
    }
    
    public void initUpdateTasks(){
        for (SignalingElement element : elements) {
            final Task task = new UpdateSignalingTask(element);
            scheduler.addTask(task, new TaskProperties(true, 1000));
        }
    }

    public void setElements(List<SignalingElement> elements) {
        this.elements = elements;
    }

    @Override
	public List<SignalingElement> getElements() {
		return elements;
	}

	@Override
	public boolean isAlarm() {
	    boolean result = false;
	    for (SignalingElement element : elements) {
            result |= element.isAlarm();
        }
		return result;
	}

}
