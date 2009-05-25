package by.bsu.onewire.core;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;

public class BaseIntegrationTest {

    protected static ApplicationContext factory;

    @BeforeClass
    public static void initContext() throws OneWireIOException, OneWireException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(new String []{"test-app-context.xml"});
        ctx.registerShutdownHook();
        factory = ctx;
    }

}