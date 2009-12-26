package by.bsu.onewire.common.modules.signaling;

import java.io.Serializable;

import by.bsu.onewire.common.utils.AddressUtils;

public class SignalingElement implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private boolean alarm;
    private long id;
    private long keyAddress;
    private long labelAddress;
    private String title;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getKeyAddress() {
        return keyAddress;
    }

    public void setKeyAddress(long keyAddress) {
        this.keyAddress = keyAddress;
    }

    public void setKeyAddressString(String address) {
        setKeyAddress(AddressUtils.toLong(address));
    }
    
    public String getKeyAddressString() {
        return AddressUtils.toString(keyAddress);
    }

    public long getLabelAddress() {
        return labelAddress;
    }

    public void setLabelAddressString(String address) {
        setLabelAddress(AddressUtils.toLong(address));
    }
    
    public String getLabelAddressString() {
        return AddressUtils.toString(labelAddress);
    }

    public void setLabelAddress(long labelAddress) {
        this.labelAddress = labelAddress;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();

        buffer.append("SignalingElement: [id=");
        buffer.append(id);
        if (title != null) {
            buffer.append(", title=");
            buffer.append(title);
        }
        buffer.append(", key=");
        buffer.append(AddressUtils.toString(keyAddress));
        buffer.append(", label=");
        buffer.append(AddressUtils.toString(labelAddress));
        buffer.append(", enabled=");
        buffer.append(enabled);
        buffer.append(", alarm=");
        buffer.append(alarm);
        buffer.append("]");
        return buffer.toString();
    }
}
