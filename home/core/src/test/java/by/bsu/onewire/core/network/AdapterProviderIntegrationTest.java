package by.bsu.onewire.core.network;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
public class AdapterProviderIntegrationTest{

    @Resource
    AdapterProvider adapterProvider;
    
    @Test
    public void testAdapterProviderInit() throws OneWireIOException, OneWireException
    {
        DSPortAdapter adapter = adapterProvider.getAdapter();
        final String adapterName = adapter.getAdapterName();
        final String portName = adapter.getPortName();
        assertEquals("{DS9490}", adapterName);
        assertEquals("USB1", portName);
    }
}
