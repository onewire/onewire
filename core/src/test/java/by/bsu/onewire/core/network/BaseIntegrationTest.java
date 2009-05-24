package by.bsu.onewire.core.network;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;

public class BaseIntegrationTest {

    protected static BeanFactory factory;
    private static AdapterProvider adapterProvider;

    @BeforeClass
    public static void initContext() throws OneWireIOException, OneWireException {
        factory = new XmlBeanFactory(new ClassPathResource("test-app-context.xml"));
        adapterProvider = (AdapterProvider) factory.getBean("adapterProvider");
        adapterProvider.getAdapter().beginExclusive(true);
    }

    @AfterClass
    public static void freeContext() throws OneWireIOException, OneWireException {
        adapterProvider.getAdapter().endExclusive();
    }

    public BaseIntegrationTest() {
        super();
    }

}