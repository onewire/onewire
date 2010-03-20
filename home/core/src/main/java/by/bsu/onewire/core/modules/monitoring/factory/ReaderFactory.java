package by.bsu.onewire.core.modules.monitoring.factory;

import com.dalsemi.onewire.container.OneWireContainer;

import by.bsu.onewire.common.device.sensor.SensorType;
import by.bsu.onewire.core.modules.monitoring.sensor.SensorReader;

/**
 * Factory can create value reader for sensor.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface ReaderFactory {

    /**
     * Creates reader object. Correct type is determined by sensor type and
     * container class.
     * 
     * @param sensorType
     *            type of sensor that should be associated with container
     * @param container
     *            real device representation
     */
    SensorReader createReader(SensorType sensorType, OneWireContainer container);
}
