package by.bsu.onewire.webui.client;

import by.bsu.onewire.webui.client.controller.SignalingController;
import by.bsu.onewire.webui.client.model.SignalingModel;
import by.bsu.onewire.webui.client.view.SignalingView;
import by.bsu.onewire.webui.client.view.ViewKeys;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtmvc.client.ModelForView;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Webui implements EntryPoint  {

    @SuppressWarnings("unchecked")
    public void onModuleLoad() {
        final SignalingModel model = new SignalingModel();
        final ModelForView[] models = {model};
        final SignalingView signalingView = new SignalingView(ViewKeys.SIGNALING_VIEW_KEY, new SignalingController(), models );
        final DecoratedTabPanel tabs = new DecoratedTabPanel();
        tabs.setWidth("100%");
        tabs.add(signalingView, "Signaling Module");
        tabs.selectTab(0);
        RootPanel.get().add(tabs);
//        FlexTable table 
        
    }

    
    
}
