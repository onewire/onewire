package by.bsu.onewire.core.network.extensions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.application.monitor.AbstractDeviceMonitor;
import com.dalsemi.onewire.application.monitor.NetworkDeviceMonitor;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.utils.OWPath;

/**
 * <code>SearchExtension</code> implementation, use OWAPI network monitor.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class SearchExtensionImpl extends ExtensionBase implements SearchExtension {

    protected AbstractDeviceMonitor monitor;
    protected Map<Long, OWPath> devices;

    public SearchExtensionImpl() {
        devices = new HashMap<Long, OWPath>();
    }

    public void initExtension() throws OneWireIOException, OneWireException {
        search();
    }

    @Override
    public OneWireContainer getDevice(long address) {
        final OneWireContainer container = AbstractDeviceMonitor.getDeviceContainer(adapter, address);
        return container;
    }

    @Override
    public List<OneWireContainer> getDevices() {
        final List<OneWireContainer> result = new LinkedList<OneWireContainer>();
        for (Long address : devices.keySet()) {
            final OneWireContainer container = AbstractDeviceMonitor.getDeviceContainer(adapter, address);
            result.add(container);
        }
        return result;
    }

    @Override
    public List<Long> getDevicesAddresses() {
        final List<Long> result = new LinkedList<Long>(devices.keySet());
        return result;
    }

    /**
     * Run network monitor, save results in inner data store.
     */
    @Override
    public void search() throws OneWireIOException, OneWireException {
        final Vector<Long> arrivals = new Vector<Long>();
        final Vector<Long> departures = new Vector<Long>();
        monitor.search(arrivals, departures);
        for (Long address : arrivals) {
            final OWPath path = monitor.getDevicePath(address);
            devices.put(address, path);
        }
        for (Long address : departures) {
            devices.remove(address);
        }
    }

    /**
     * Set network adapter, create network monitor for this adapter.
     */
    @Override
    public void setAdapter(DSPortAdapter adapter) {
        super.setAdapter(adapter);
        final NetworkDeviceMonitor networkDeviceMonitor = new NetworkDeviceMonitor(adapter);
        networkDeviceMonitor.setBranchAutoSearching(false);
        monitor = networkDeviceMonitor;
        monitor.setMaxErrorCount(1);
    }

    /**
     * Check if device present in iner data store.
     */
    @Override
    public boolean isDevicePresent(long address) {
        return devices.containsKey(address);
    }

}
