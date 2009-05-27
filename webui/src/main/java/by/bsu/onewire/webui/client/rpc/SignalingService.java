package by.bsu.onewire.webui.client.rpc;

import java.util.List;

import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("signaling")
public interface SignalingService extends RemoteService {

    List<SignalingElement> getElements();

    boolean isAlarm();
}
