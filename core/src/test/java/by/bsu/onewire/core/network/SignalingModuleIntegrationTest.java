package by.bsu.onewire.core.network;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

import by.bsu.onewire.core.BaseIntegrationTest;
import by.bsu.onewire.core.modules.signaling.SignalingElement;
import by.bsu.onewire.core.modules.signaling.SignalingModule;
import by.bsu.onewire.core.modules.signaling.UpdateSignalingTask;

public class SignalingModuleIntegrationTest extends BaseIntegrationTest {
    @Test
    public void testUpdateTask() throws OneWireIOException, OneWireException {
        NetworkManager manager = (NetworkManager) factory.getBean("networkManager");
        AdapterProvider adapterProvider = (AdapterProvider) factory.getBean("adapterProvider");
        final DSPortAdapter adapter = adapterProvider.getAdapter();
        String portName = adapter.getPortName();
        adapter.selectPort(portName);
        manager.getSearchExtension().search();
        SignalingElement element = (SignalingElement) factory.getBean("signalingElement");
        element.setAlarm(false);
        UpdateSignalingTask task = new UpdateSignalingTask(element);
        task.setNetworkManager(manager);
        task.execute();
        assertFalse(element.isAlarm());
    }
    
    @Test
    public void testModuleCreation(){
       SignalingModule module = (SignalingModule) factory.getBean("signalingModule");
       assertNotNull(module);
    }
}
