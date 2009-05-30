package by.bsu.onewire.webui.client.view;

import java.util.HashMap;
import java.util.Map;

import by.bsu.onewire.webui.client.PanelManager;
import by.bsu.onewire.webui.client.PanelManager.PanelKey;
import by.bsu.onewire.webui.client.controller.MainController;
import by.bsu.onewire.webui.client.controller.MainController.MainControllerAction;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Panel;
import com.googlecode.gwtmvc.client.Event;
import com.googlecode.gwtmvc.client.ModelForView;
import com.googlecode.gwtmvc.client.View;

public class MainView extends View {

    private DecoratedTabPanel tabPanel;
    private Map<Integer, PanelKey> tabsIndexes;
    public MainView(MainController controller) {
        super(ViewKeys.MAIN_VIEW_KEY, controller);
        init();
    }

    @Override
    public void init() {
        tabsIndexes = new HashMap<Integer, PanelKey>();
        
        tabPanel = new DecoratedTabPanel();
        final Panel signalingPanel = PanelManager.registerPanel(PanelKey.SIGNALING_PANEL);
        tabPanel.add(signalingPanel, "Signaling module");
        tabsIndexes.put(0, PanelKey.SIGNALING_PANEL);
        final Panel monitoringPanel = PanelManager.registerPanel(PanelKey.MONITORING_PANEL);
        tabPanel.add(monitoringPanel, "Monitoring module");
        tabsIndexes.put(1, PanelKey.MONITORING_PANEL);
        
        tabPanel.addSelectionHandler(new SelectionHandler<Integer>(){
            public void onSelection(SelectionEvent<Integer> event) {
                final Integer item = event.getSelectedItem();
                final PanelKey key = tabsIndexes.get(item);
                controller.call(new Event<PanelKey, MainControllerAction>(MainControllerAction.TAB_SELECTED, key));
            }
        });
        tabPanel.selectTab(0);
        initWidget(tabPanel);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onModelChange(ModelForView model) {

    }

}
