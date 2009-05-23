package by.bsu.onewire.core.network;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

public class AdapterProviderIntegrationTest {

    private BeanFactory factory;

    @Before
    public void initContext() {
        factory = new XmlBeanFactory(new ClassPathResource("test-app-context.xml"));
    }
    
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
