package by.bsu.onewire.core.network.extensions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import by.bsu.onewire.core.sheduler.TaskBase;

import com.dalsemi.onewire.OneWireException;

public class SearchDevicesTask extends TaskBase {
    private Log log = LogFactory.getLog(SearchDevicesTask.class);    
    @Override
    public void execute() {
        try {
            if(log.isDebugEnabled()){
                log.debug("Serch devices task started.");
            }
            networkManager.getSearchExtension().search();
        } catch (OneWireException e) {
            log.error("Search devices task failed: ", e);
        }
    }

}
