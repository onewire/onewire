package by.bsu.onewire.webui.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import by.bsu.onewire.common.modules.signaling.SignalingElement;

public interface SignalingServiceAsync {
    void getElements(AsyncCallback<List<SignalingElement>> callback);

    void isAlarm( AsyncCallback<Boolean> callback);
}
