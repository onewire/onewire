package by.bsu.onewire.core.modules.monitoring;

import java.util.List;

import by.bsu.onewire.core.modules.monitoring.sensor.Sensor;

/**
 * Life factors monitoring module interface.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface MonitoringModule {

    /**
     * Returns list of sensors that has been registered in this module.
     */
    List<Sensor> getAvailableSensors();
}
