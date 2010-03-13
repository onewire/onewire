package by.bsu.onewire.core.network;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bsu.onewire.core.modules.monitoring.sensor.TemperatureSensorReader;
import by.bsu.onewire.core.network.extensions.ConvertExtension;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer10;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
public class ConvertExtensionIntegrationTest {

    @Resource
    AdapterProvider adapterProvider;

    @Resource
    ConvertExtension convertExtension;

    private static final double ALLOW_DIFF = 2;

    private DSPortAdapter adapter;

    private OneWireContainer10 device1;

    private OneWireContainer10 device2;

    private TemperatureSensorReader reader;

    @Before
    public void setUp() throws Throwable {
        adapter = adapterProvider.getAdapter();
        device1 = (OneWireContainer10) adapter.getDeviceContainer("11000800C63FE210");
        device2 = (OneWireContainer10) adapter.getDeviceContainer("26000800C6773D10");
        reader = new TemperatureSensorReader();
        reader.setContainer(device1);
    }

    @Test
    public void doConvertion() throws Throwable {

        double beforeConversion = reader.readValue();
        
        convertExtension.doTemperatureConversion();
        double afterConvertion = readTemperature(device1);
        
        double readTemperature = readTemperature(device2);
        double diff = readTemperature - afterConvertion;
        assertThat("Difference between two temperatures should be less that 2 celsius", Math.abs(diff) < ALLOW_DIFF, is(true));
        
        diff = beforeConversion - afterConvertion;
        assertThat("Difference between reader and extension should be less that 2 celsius", Math.abs(diff) < ALLOW_DIFF, is(true));
        
        reader.setForceConvertion(false);
        diff = reader.readValue() - afterConvertion;
        assertThat("There should be not difference", diff, is(0.0));
    }

    private double readTemperature(OneWireContainer10 device) throws OneWireIOException, OneWireException {
        byte[] state = device.readDevice();
        return device.getTemperature(state);
    }
}
