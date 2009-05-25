package by.bsu.onewire.core.network;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import by.bsu.onewire.core.BaseIntegrationTest;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

public class AdapterProviderIntegrationTest extends BaseIntegrationTest{

    @Test
    public void testAdapterProviderInit() throws OneWireIOException, OneWireException
    {
        AdapterProvider adapterProvider = (AdapterProvider) factory.getBean("adapterProvider");
        DSPortAdapter adapter = adapterProvider.getAdapter();
        final String adapterName = adapter.getAdapterName();
        final String portName = adapter.getPortName();
        assertEquals("{DS9490}", adapterName);
        assertEquals("USB1", portName);
    }
}
