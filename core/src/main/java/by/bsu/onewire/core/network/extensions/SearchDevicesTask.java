package by.bsu.onewire.core.network.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import by.bsu.onewire.core.sheduler.TaskBase;

import com.dalsemi.onewire.OneWireException;

/**
 * Task updates devices list that store in <code>SearchExtention</code>. This
 * task should be always repeat to maintain actual devices list.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class SearchDevicesTask extends TaskBase {
    private Log log = LogFactory.getLog(SearchDevicesTask.class);

    @Override
    public void execute() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Search devices task started.");
            }
            networkManager.getSearchExtension().search();
        } catch (OneWireException e) {
            log.error("Search devices task failed: ", e);
        }
    }

}
