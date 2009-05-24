package by.bsu.onewire.core.network.extensions;

import java.util.List;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.container.OneWireContainer;

public interface SearchExtension extends Extention {
    OneWireContainer getDevice(long address);
    
    boolean isDevicePresent(long address);

    List<OneWireContainer> getDevices();

    void search() throws OneWireIOException, OneWireException;

    public List<Long> getDevicesAddresses();
}
