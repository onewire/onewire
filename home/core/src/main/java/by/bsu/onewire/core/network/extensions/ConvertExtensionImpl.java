package by.bsu.onewire.core.network.extensions;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 * Implementation of conversion extension.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class ConvertExtensionImpl extends ExtensionBase implements ConvertExtension {

    private static final int START_CONVERTION_COMMAND = 0x44;
    private static final int SKIP_ROM_COMMAND = 0xCC;

    public static final long DEFAULT_CONVERTION_TIME = 800;

    private long conversionTime = DEFAULT_CONVERTION_TIME;

    /**
     * Initiate temperature conversion for all available sensors. All sensors do
     * conversion parallel, it's more efficient that work with every sensor
     * separately.
     */
    @Override
    public void doTemperatureConversion() throws OneWireIOException, OneWireException {
        startConversion();
        waitConversion();
        stopConvertion();
    }

    /**
     * Interrupt power delivery and check if temperature conversion has
     * completed.
     */
    public void stopConvertion() throws OneWireIOException, OneWireException {
        adapter.setPowerNormal();
        if (adapter.getByte() != 0x0FF)
            throw new OneWireIOException("Temperature conversion not complete");
    }

    /**
     * Initiate temperature conversion for all sensors. This method send reset
     * pulse, then send 0x44 command and start power delivery.
     */
    public void startConversion() throws OneWireIOException, OneWireException {
        adapter.setSpeed(DSPortAdapter.SPEED_REGULAR);
        adapter.reset();
        adapter.putByte(SKIP_ROM_COMMAND);
        adapter.setPowerDuration(DSPortAdapter.DELIVERY_INFINITE);
        adapter.startPowerDelivery(DSPortAdapter.CONDITION_AFTER_BYTE);
        adapter.putByte(START_CONVERTION_COMMAND);
    }

    private void waitConversion() {
        try {
            Thread.sleep(conversionTime);
        } catch (InterruptedException e) {
        }
    }

    public long getConversionTime() {
        return conversionTime;
    }

    /**
     * Change time of conversion.
     * 
     * @param convertionTime
     *            time that system wait
     */
    public void setConversionTime(long convertionTime) {
        this.conversionTime = convertionTime;
    }
}
