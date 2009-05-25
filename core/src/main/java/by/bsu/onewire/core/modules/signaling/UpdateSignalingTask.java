package by.bsu.onewire.core.modules.signaling;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import by.bsu.onewire.core.network.extensions.SearchExtension;
import by.bsu.onewire.core.sheduler.TaskBase;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.SwitchContainer;

public class UpdateSignalingTask extends TaskBase {
    private Log log = LogFactory.getLog(UpdateSignalingTask.class);

    private SignalingElement element;

    public UpdateSignalingTask(SignalingElement element) {
        this.element = element;
    }

    public SignalingElement getElement() {
        return element;
    }

    public void setElement(SignalingElement element) {
        this.element = element;
    }

    @Override
    public void execute() {
        final SearchExtension search = networkManager.getSearchExtension();
        if (log.isDebugEnabled()) {
            log.debug("update signaling element: " + element.toString());
        }
        if (!element.isEnabled()) {
            return;
        }
        element.setAlarm(true);
        try {
            final OneWireContainer container = search.getDevice(element.getKeyAddress());
            if (container instanceof SwitchContainer) {
                final SwitchContainer switchContainer = (SwitchContainer) container;
                byte[] state = switchContainer.readDevice();
                if (!switchContainer.getLatchState(0, state)) {
                    switchContainer.setLatchState(0, true, false, state);
                    switchContainer.writeDevice(state);
                }
            }
            final boolean labelPresent = search.isDevicePresent(element.getLabelAddress());
            element.setAlarm(!labelPresent);
            if (!labelPresent && log.isInfoEnabled()) {
                log.info("Security module activate alarm: " + element);
            }
        } catch (OneWireIOException e) {
            log.error("Check signaling crash.", e);
        } catch (OneWireException e) {
            log.error("Check signaling crash.", e);
        }
    }

}
