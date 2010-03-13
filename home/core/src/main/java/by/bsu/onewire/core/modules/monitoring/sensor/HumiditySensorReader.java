package by.bsu.onewire.core.modules.monitoring.sensor;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.HumidityContainer;

/**
 * Reader for humidity devices.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class HumiditySensorReader implements SensorReader {

    private HumidityContainer container;

    /**
     * Initiate humidity conversion and read result.
     */
    public double readValue() throws OneWireException {
        // Read device state.
        byte[] state = container.readDevice();
        // Perform humidity conversion, supply power for conversion.
        container.doHumidityConvert(state);
        // Read device state
        state = container.readDevice();
        // Calculate humidity value based on device state.
        return container.getHumidity(state);
    }

    public void setContainer(HumidityContainer container) {
        this.container = container;
    }
}
