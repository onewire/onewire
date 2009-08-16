package by.bsu.onewire.webui.client.controller;

import by.bsu.onewire.webui.client.PanelManager;
import by.bsu.onewire.webui.client.PanelManager.PanelKey;
import by.bsu.onewire.webui.client.model.SignalingModel;
import by.bsu.onewire.webui.client.view.SignalingView;
import by.bsu.onewire.webui.client.view.ViewKeys;

import com.google.gwt.user.client.ui.Panel;
import com.googlecode.gwtmvc.client.BrowserEvent;
import com.googlecode.gwtmvc.client.Event;
import com.googlecode.gwtmvc.client.IView;
import com.googlecode.gwtmvc.client.ModelForView;

@SuppressWarnings("unchecked")
public class SignalingController extends TabControllerBase {

    protected SignalingModel model;
    protected SignalingView signalingView;

    public enum SignalingAction {
        SHOW_SIGNALING, HIDE_SIGNALING
    }

    public SignalingController(PanelKey panelKey) {
        super(panelKey);
    }

    @Override
    protected Enum[] getActionEnumValues() {
        return SignalingAction.values();
    }

    @Override
    public void init() {
        super.init();
        model = new SignalingModel();
        initModel(model);
        final ModelForView[] models = { model };
        signalingView = new SignalingView(ViewKeys.SIGNALING_VIEW_KEY, this, models);
        renderView(signalingView);
    }

    @Override
    protected void renderView(IView iview) {
        if (iview instanceof SignalingView) {
            final SignalingView view = (SignalingView) iview;
            final Panel panel = PanelManager.getPanel(panelKey);
            panel.clear();
            panel.add(view);
        }
    }

    @Override
    protected Event tryConvertBrowserEventToControllerEvent(BrowserEvent browserEvent) throws IllegalArgumentException {
        SignalingAction action = Enum.valueOf(SignalingAction.class, browserEvent.getHistoryToken());
        return new Event<String, SignalingAction>(action);
    }

    @Override
    protected void hideTab() {
        model.stopUpdate();

    }

    @Override
    protected void showTab() {
        model.startUpdate();
    }

}
