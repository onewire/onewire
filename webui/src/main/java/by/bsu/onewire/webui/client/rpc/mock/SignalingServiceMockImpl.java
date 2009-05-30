package by.bsu.onewire.webui.client.rpc.mock;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;
import by.bsu.onewire.webui.client.rpc.SignalingServiceAsync;

public class SignalingServiceMockImpl implements SignalingServiceAsync {

    @Override
    public void getElements(final AsyncCallback<List<SignalingElement>> callback) {
        final List<SignalingElement> result = new ArrayList<SignalingElement>(3);
        SignalingElement element = new SignalingElement();
        element.setEnabled(true);
        element.setAlarm(false);
        element.setId(1);
        element.setLabelAddress(238925926L);
        element.setKeyAddress(3457383838L);
        result.add(element);
        
        element = new SignalingElement();
        element.setEnabled(true);
        element.setAlarm(false);
        element.setId(2);
        element.setLabelAddress(2381325926L);
        element.setKeyAddress(34573483838L);
        result.add(element);
        
        final Timer timer = new Timer(){
            @Override
            public void run() {
                callback.onSuccess(result);
//                callback.onFailure(new Throwable());
            }
            
        };
        timer.schedule(1000);
    }

    @Override
    public void isAlarm(final AsyncCallback<Boolean> callback) {
        final Timer timer = new Timer(){
            @Override
            public void run() {
                callback.onSuccess(false);
            }
            
        };
        timer.schedule(1000);
       
    }

}
