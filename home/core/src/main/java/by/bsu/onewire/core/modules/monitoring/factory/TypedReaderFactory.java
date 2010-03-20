package by.bsu.onewire.core.modules.monitoring.factory;

import com.dalsemi.onewire.container.OneWireContainer;

import by.bsu.onewire.core.modules.monitoring.sensor.SensorReader;

/**
 * Factory creates readers of specific type only.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface TypedReaderFactory {

    /**
     * Check if container is supported and creates reader.
     * 
     * @return reader object if container supported, <code>null</code> -
     *         otherwise.
     */
    SensorReader createReader(OneWireContainer container);
}
