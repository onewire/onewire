package by.bsu.onewire.webui.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;

public class PanelManager {
    public static enum PanelKey {
        SIGNALING_PANEL, MONITORING_PANEL
    }

    protected static Map<PanelKey, Panel> panels = new HashMap<PanelKey, Panel>();

    public static Panel registerPanel(PanelKey key) {
        final FlowPanel panel = new FlowPanel();
        panels.put(key, panel);
        return panel;
    }

    public static Panel getPanel(PanelKey key) {
        return panels.get(key);
    }
}
