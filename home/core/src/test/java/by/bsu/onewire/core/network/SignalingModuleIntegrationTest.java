package by.bsu.onewire.core.network;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bsu.onewire.common.modules.signaling.SignalingElement;
import by.bsu.onewire.common.modules.signaling.SignalingModule;
import by.bsu.onewire.core.modules.signaling.UpdateSignalingTask;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-context.xml", "classpath:signaling-module-context.xml" })
public class SignalingModuleIntegrationTest{
    
    @Resource
    NetworkManager networkManager;
    
    @Resource
    AdapterProvider adapterProvider;
    
    @Resource
    SignalingElement signalingElement;
    
    @Resource
    SignalingModule signalingModule;

    @Test
    public void testUpdateTask() throws OneWireIOException, OneWireException {
        final DSPortAdapter adapter = adapterProvider.getAdapter();
        String portName = adapter.getPortName();
        adapter.selectPort(portName);
        networkManager.getSearchExtension().search();
        signalingElement.setAlarm(false);
        UpdateSignalingTask task = new UpdateSignalingTask(signalingElement);
        task.setNetworkManager(networkManager);
        task.execute();
        assertFalse(signalingElement.isAlarm());
    }

    @Test
    public void testModuleCreation() {
        assertNotNull(signalingModule);
    }
}
