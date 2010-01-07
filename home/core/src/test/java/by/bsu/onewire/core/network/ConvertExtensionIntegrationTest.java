package by.bsu.onewire.core.network;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void doConvertion() throws Throwable {
        DSPortAdapter adapter = adapterProvider.getAdapter();
        OneWireContainer10 device1 = (OneWireContainer10) adapter.getDeviceContainer("11000800C63FE210");
        OneWireContainer10 device2 = (OneWireContainer10) adapter.getDeviceContainer("26000800C6773D10");
        convertExtension.doTemperatureConversion();
        double diff = readTemperature(device2) - readTemperature(device1);
        assertThat("Difference between two temperatures should be less that 2 celsius", ((-ALLOW_DIFF < diff) && (diff < ALLOW_DIFF)), is(true));
    }

    private double readTemperature(OneWireContainer10 device) throws OneWireIOException, OneWireException {
        byte[] state = device.readDevice();
        return device.getTemperature(state);
    }
}
