package by.bsu.onewire.common.device.sensor;

import by.bsu.onewire.common.device.DeviceType;

/**
 * Plain sensor implementation, just bean class. It doesn't do any actions like
 * read device.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class PlainSensor implements Sensor {

    private SensorType sensorType;

    private double value;

    private String label;

    private String description;

    @Override
    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Sensor;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

}
