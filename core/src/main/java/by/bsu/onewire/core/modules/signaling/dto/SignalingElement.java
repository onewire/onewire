package by.bsu.onewire.core.modules.signaling.dto;

import java.io.Serializable;

import by.bsu.onewire.core.utils.AddressUtils;

public class SignalingElement implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean enabled;
    private boolean alarm;
    private long id;
    private long keyAddress;
    private long labelAddress;

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

    public long getLabelAddress() {
        return labelAddress;
    }

    public void setLabelAddressString(String address) {
        setLabelAddress(AddressUtils.toLong(address));
    }

    public void setLabelAddress(long labelAddress) {
        this.labelAddress = labelAddress;
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();

        buffer.append("((id=");
        buffer.append(id);
        buffer.append("), (key=");
        buffer.append(AddressUtils.toString(keyAddress));
        buffer.append("), (label=");
        buffer.append(AddressUtils.toString(labelAddress));
        buffer.append("), (enabled=");
        buffer.append(enabled);
        buffer.append("), (alarm=");
        buffer.append(alarm);
        buffer.append("))");
        return buffer.toString();
    }
}
