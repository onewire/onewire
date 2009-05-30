package by.bsu.onewire.webui.client.controller;

import by.bsu.onewire.webui.client.PanelManager;
import by.bsu.onewire.webui.client.PanelManager.PanelKey;

import com.googlecode.gwtmvc.client.Event;

public abstract class TabControllerBase extends ControllerBase {

    public static enum TabControllerAction {
        SHOW_TAB, HIDE_TAB
    }

    protected PanelManager.PanelKey panelKey;

    public TabControllerBase(PanelKey panelKey) {
        this.panelKey = panelKey;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void handleEvent(Event event) {
        TabControllerAction action = (TabControllerAction) event.getAction();
        switch (action) {
        case SHOW_TAB:
            showTab();
            break;
        case HIDE_TAB:
            hideTab();
            break;
        }
    }

    @Override
    public void showHomeView() {
    }

    protected abstract void hideTab();

    protected abstract void showTab();

}
