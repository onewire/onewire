package by.bsu.onewire.webui.client.controller;

import java.util.HashMap;
import java.util.Map;

import by.bsu.onewire.webui.client.PanelManager.PanelKey;
import by.bsu.onewire.webui.client.controller.TabControllerBase.TabControllerAction;
import by.bsu.onewire.webui.client.view.MainView;

import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtmvc.client.BrowserEvent;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.Event;
import com.googlecode.gwtmvc.client.IView;

@SuppressWarnings("unchecked")
public class MainController extends Controller {

    private MainView mainView;
    private Map<PanelKey, TabControllerBase> controllers;

    public static enum MainControllerAction {
        SHOW_WELCOME, TAB_SELECTED
    }

    @Override
    public void init() {
        controllers = new HashMap<PanelKey, TabControllerBase>();
        final SignalingController signalingController = new SignalingController(PanelKey.SIGNALING_PANEL);
        controllers.put(PanelKey.SIGNALING_PANEL, signalingController);
        mainView = new MainView(this);

    }

    @Override
    protected Enum[] getActionEnumValues() {
        return MainControllerAction.values();
    }

    @Override
    protected void handleEvent(Event event) {
        final Enum actionEnum = event.getAction();
        if (!(actionEnum instanceof MainControllerAction)) {
            return;
        }
        MainControllerAction action = (MainControllerAction) actionEnum;
        switch (action) {
        case TAB_SELECTED:
            selectTab((PanelKey) event.getValue());
            break;
        }
    }

    protected void selectTab(PanelKey key) {
        final TabControllerBase tabController = controllers.get(key);
        if (tabController != null) {
            tabController.call(new Event<Void, TabControllerAction>(TabControllerAction.SHOW_TAB));
        }
        for (TabControllerBase controller : controllers.values()) {
            if (controller != tabController) {
                controller.call(new Event<Void, TabControllerAction>(TabControllerAction.HIDE_TAB));
            }
        }
    }

    @Override
    protected void renderView(IView iview) {
        if (iview instanceof MainView) {
            final MainView view = (MainView) iview;
            RootPanel.get().add(view);
        }
    }

    @Override
    public void showHomeView() {
        renderView(mainView);
    }

    @Override
    protected Event tryConvertBrowserEventToControllerEvent(BrowserEvent browserEvent) throws IllegalArgumentException {
        return null;
    }

}
