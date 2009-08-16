package by.bsu.onewire.webui.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsu.onewire.core.modules.signaling.SignalingModule;
import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;
import by.bsu.onewire.webui.client.rpc.SignalingService;

@SuppressWarnings("serial")
public class SignalingServiceImpl extends SpringAwareServletBase implements SignalingService {

    @Autowired
    SignalingModule signalingModule;

    @Override
    public boolean isAlarm() {
        return signalingModule.isAlarm();
    }

    @Override
    public List<SignalingElement> getElements() {
        return signalingModule.getElements();
    }

}
