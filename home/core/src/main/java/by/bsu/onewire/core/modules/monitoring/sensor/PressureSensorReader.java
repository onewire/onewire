package by.bsu.onewire.core.modules.monitoring.sensor;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.OneWireContainer20;

/**
 * Reader for pressure sensor. In hardware implementation this sensor is AD
 * converter with connected analog pressure sensor.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class PressureSensorReader implements SensorReader {

    private int channel;
    private double coeff;
    private double diff;

    OneWireContainer20 container;

    public PressureSensorReader() {
        coeff = 1;
        channel = 0;
        diff = 0;
    }

    /**
     * Read value from AD converter and convert this value to pressure. For
     * conversion use rule:
     * 
     * <pre> pressure = ADresult * coeff + diff</pre>
     */
    public double readValue() throws OneWireException {
        byte[] state = container.readDevice();
        // Check if specific channel is enabled
        boolean channelEnabled = container.getOutputState(channel, state);
        // Enable channel if needed
        if (channelEnabled) {
            container.setOutput(channel, true, true, state);
            container.writeDevice(state);
            state = container.readDevice();
        }
        container.doADConvert(channel, state);
        // Calculate pressure value based on configured coefficient
        double result = container.getADVoltage(channel, state);
        result = result * coeff + diff;
        return result;
    }

    public void setContainer(OneWireContainer20 container) {
        this.container = container;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }
}
