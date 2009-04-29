package by.bsu.onewire.core.modules.monitoring.sensor;

import by.bsu.onewire.core.device.Device;

public interface Sensor extends Device {

    public double getValue();

    public SensorType getSensorType();
}

