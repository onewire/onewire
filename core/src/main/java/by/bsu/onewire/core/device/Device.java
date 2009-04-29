package by.bsu.onewire.core.device;


/**
 * This interface provide representation of OneWire device.
 * @author Aliaksandr_Zlobich
 */
public interface Device {

    /**
     * Return device label. 
     * Device label is a string that associate with this device.
     * @return value of device label
     */
    public String getLabel();

    /**
     * Set device label
     * @param label a new string that should be associated with this device.
     */
    public void setLabel(String label);

    /**
     * Get text description of this device
     * @return the text description of device
     */
    public String getDescription();

    /**
     * Set text description of this device
     * @param description a new text description of this device
     */
    public void setDescription(String description);

    /**
     * Get type of this device.
     * @return object of <code>DeviceType</code> class that represent type of this device
     */
    public DeviceType getDeviceType();
    
}

