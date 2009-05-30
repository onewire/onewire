package by.bsu.onewire.webui.client.view;

import java.util.List;

import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;
import by.bsu.onewire.webui.client.model.SignalingModel;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelForView;
import com.googlecode.gwtmvc.client.View;

public class SignalingView extends View {

    private Grid content;

    @SuppressWarnings("unchecked")
    public SignalingView(String key, Controller controller, ModelForView[] models) {
        super(key, controller, models);
        FlowPanel panel = new FlowPanel();
        content = new Grid(1, 3);
        content.setWidth("700px");
        content.setCellSpacing(0);
        content.setCellPadding(0); 
        content.addStyleName("signaling-List");
        content.setVisible(false);
        panel.add(content);
        initWidget(panel);
    }

    @Override
    public void init() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onModelChange(ModelForView model) {
        if (model instanceof SignalingModel) {
            final SignalingModel signalingModel = (SignalingModel) model;
            content.clear();
            final List<SignalingElement> elements = signalingModel.getValue();
            content.resizeRows(elements.size()+1);
            content.setHTML(0, 0, "Id");
            content.setHTML(0, 1, "Enabled");
            content.setHTML(0, 2, "Value");
            content.getRowFormatter().setStyleName(0, "signaling-ListHeader");
            int row = 1;
            for (SignalingElement element : elements) {
                content.setHTML(row, 0, Long.toString(element.getId()));
                content.setHTML(row, 1, Boolean.toString(element.isEnabled()));
                content.setHTML(row, 2, Boolean.toString(element.isAlarm()));
                ++row;
            }
            content.setVisible(true);
        }
    }

}
