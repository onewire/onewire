package by.bsu.onewire.webui.client.model;

import java.util.List;

import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;
import by.bsu.onewire.webui.client.rpc.BaseCallback;
import by.bsu.onewire.webui.client.rpc.ServiceLocator;
import by.bsu.onewire.webui.client.rpc.SignalingServiceAsync;

import com.google.gwt.user.client.Timer;
import com.googlecode.gwtmvc.client.Model;

public class SignalingModel extends Model<List<SignalingElement>> {

    private static final int UPDATE_INTERVAL = 500;

    private SignalingServiceAsync service;

    private Timer updateModelTimer;
    
    public SignalingModel() {
        updateModelTimer = new Timer(){
            @Override
            public void run() {
                updateModelFromServer();
            }
        };
        
        init();
    }

    @Override
    protected void init() {
        service = ServiceLocator.instance().getSignalingService();
        updateModelFromServer();
    }

    protected void updateModelFromServer() {
        service.getElements(new BaseCallback<List<SignalingElement>>() {
            public void onSuccess(List<SignalingElement> result) {
                value = result;
                onChange();
                updateModelTimer.schedule(UPDATE_INTERVAL);
            }
        });
    }
}
