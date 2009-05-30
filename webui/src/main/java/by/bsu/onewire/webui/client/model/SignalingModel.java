package by.bsu.onewire.webui.client.model;

import java.util.List;

import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;
import by.bsu.onewire.webui.client.rpc.BaseCallback;
import by.bsu.onewire.webui.client.rpc.ServiceLocator;
import by.bsu.onewire.webui.client.rpc.SignalingServiceAsync;

import com.google.gwt.user.client.Timer;
import com.googlecode.gwtmvc.client.Model;

public class SignalingModel extends Model<List<SignalingElement>> {

    private static final int UPDATE_INTERVAL = 1000;

    private SignalingServiceAsync service;
    private boolean update;

    private Timer updateModelTimer;

    public SignalingModel() {
        updateModelTimer = new Timer() {
            @Override
            public void run() {
                updateModelFromServer();
            }
        };
        update = true;
    }

    @Override
    protected void init() {
        service = ServiceLocator.instance().getSignalingService();
    }

    public void updateModelFromServer() {
        service.getElements(new BaseCallback<List<SignalingElement>>() {
            public void onSuccess(List<SignalingElement> result) {
                value = result;
                onChange();
                if (update) {
                    updateModelTimer.schedule(UPDATE_INTERVAL);
                }
            }
        });
    }

    public void stopUpdate() {
        updateModelTimer.cancel();
        update = false;
    }
    
    public void startUpdate() {
        update = true;
        updateModelTimer.schedule(UPDATE_INTERVAL);
    }

}
