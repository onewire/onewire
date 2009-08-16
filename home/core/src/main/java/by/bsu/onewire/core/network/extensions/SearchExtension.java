package by.bsu.onewire.core.network.extensions;

import java.util.List;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;

/**
 * Network manager extension, search devices int 1-Wire network.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface SearchExtension extends Extension {
    /**
     * Returns <code>OneWireContainer</code> representation of device with
     * network address <code>address</code>.
     * 
     * @return <code>OneWireContainer</code> instance, or <code>null</code> if
     *         device not present.
     */
    OneWireContainer getDevice(long address);

    /**
     * Check if device with given address present int network.
     */
    boolean isDevicePresent(long address);

    /**
     * Returns list of all devices in network.
     */
    List<OneWireContainer> getDevices();

    /**
     * Do network search.
     */
    void search() throws OneWireIOException, OneWireException;

    /**
     * Returns a list of all devices addresses.
     */
    public List<Long> getDevicesAddresses();
}
