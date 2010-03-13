package by.bsu.onewire.core.modules.monitoring.sensor;

import com.dalsemi.onewire.OneWireException;

/**
 * Common interface for components that can read value from sensor device.
 * Readers provide unified interface to work with different sensors and device
 * types. Specific implementation of reader should be initialized with
 * <code>OneWireContainer</code> instance and addition properties.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface SensorReader {

    /**
     * Read value from hardware sensor device.
     * 
     * @throws OneWireException
     *             in case of transport errors
     */
    double readValue() throws OneWireException;
}
