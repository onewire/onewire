package by.bsu.onewire.core.network;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bsu.onewire.core.network.extensions.SearchExtension;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.utils.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
public class SearchExtensionIntegrationTest{
    
    @Resource
    SearchExtension searchExtension;

    private static final String[] allDevices = { "7D00000029419881", "F000000AC6CD0301", "1B0000001EDAF205",
            "26000800C6773D10", "11000800C63FE210" };

    @Test
    public void testDevicesSearch() throws OneWireIOException, OneWireException {

        searchExtension.search();
        Set<String> expected = new HashSet<String>(Arrays.asList(allDevices));
        Set<String> actual = new HashSet<String>();

        final List<Long> devices = searchExtension.getDevicesAddresses();
        for (Long address : devices) {
            String addressString = Address.toString(address);
            actual.add(addressString);
        }
        assertEquals(expected, actual);

        final List<OneWireContainer> containers = searchExtension.getDevices();
        actual = new HashSet<String>();
        for (OneWireContainer container : containers) {
            actual.add(container.getAddressAsString());
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testDevicePresent() throws OneWireIOException, OneWireException {
        searchExtension.search();
        long address = Address.toLong("F000000AC6CD0301");
        assertTrue(searchExtension.isDevicePresent(address));
    }
}
