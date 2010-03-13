package by.bsu.onewire.core.modules.monitoring.sensor;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.TemperatureContainer;

/**
 * Reader for temperature sensor. This reader can skip conversion.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class TemperatureSensorReader implements SensorReader {

    private TemperatureContainer temperatureContainer;
    private boolean forceConvertion = true;

    public void setContainer(TemperatureContainer container) {
        this.temperatureContainer = container;
    }

    /**
     * Initiate or skip conversion and read temperature from device.
     */
    public double readValue() throws OneWireException {
        byte[] state = temperatureContainer.readDevice();
        // If conversion enabled initiate it, otherwise skip it and just read value from device.
        // This option is useful if conversion has been performed yearly one time for all devices.
        if (forceConvertion) {
            temperatureContainer.doTemperatureConvert(state);
            state = temperatureContainer.readDevice();
        }
        return temperatureContainer.getTemperature(state);
    }

    public boolean isForceConvertion() {
        return forceConvertion;
    }

    public void setForceConvertion(boolean forceConvertion) {
        this.forceConvertion = forceConvertion;
    }
}
