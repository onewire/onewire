package by.bsu.onewire.webui.client.controller;

import com.googlecode.gwtmvc.client.BrowserEvent;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.Event;
import com.googlecode.gwtmvc.client.IView;

public class SignalingController extends Controller {

    public enum SignalingAction {
        SHOW_SIGNALING
    }
    @Override
    protected Enum[] getActionEnumValues() {
        return SignalingAction.values();
    }

    @Override
    protected void handleEvent(Event event) {
        SignalingAction action = (SignalingAction) event.getAction();
        switch (action) {
        case SHOW_SIGNALING:
            
            break;
        default:
            break;
        }
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void renderView(IView view) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showHomeView() {
        // TODO Auto-generated method stub

    }

    @Override
    protected Event tryConvertBrowserEventToControllerEvent(BrowserEvent browserEvent) throws IllegalArgumentException {
        SignalingAction action = Enum.valueOf(SignalingAction.class, browserEvent.getHistoryToken());
        return new Event<String, SignalingAction>(action);
    }

}
