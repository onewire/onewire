package by.bsu.onewire.webui.client.view;

import java.util.List;

import by.bsu.onewire.common.modules.signaling.SignalingElement;
import by.bsu.onewire.webui.client.StyleConstants;
import by.bsu.onewire.i18n.SignalingConstants;
import by.bsu.onewire.webui.client.model.SignalingModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelForView;
import com.googlecode.gwtmvc.client.View;

public class SignalingView extends View {

    private static final int CONSTANT_ROWS_COUNT = 1;
    private static final int HEADER_ROW_INDEX = 0;

    private static final int COLUMNS_COUNT = 4;
    private static final int ID_COLUMN_INDEX = 0;
    private static final int TITLE_COLUMN_INDEX = 1;
    private static final int STATUS_COLUMN_INDEX = 2;
    private static final int STATE_COLUMN_INDEX = 3;

    private SignalingConstants constants = (SignalingConstants) GWT.create(SignalingConstants.class);

    private Grid content;

    @SuppressWarnings("unchecked")
    public SignalingView(String key, Controller controller, ModelForView[] models) {
        super(key, controller, models);
        init();
    }

    @Override
    public void init() {
        FlowPanel panel = new FlowPanel();
        content = new Grid(CONSTANT_ROWS_COUNT, COLUMNS_COUNT);
        content.setWidth("700px");
        content.setCellSpacing(0);
        content.setCellPadding(0);
        content.addStyleName(StyleConstants.SIGNALING_LIST);
        content.setVisible(false);
        
        content.getColumnFormatter().setWidth(ID_COLUMN_INDEX, "50");
        content.getColumnFormatter().setWidth(TITLE_COLUMN_INDEX, "300");
        content.getColumnFormatter().setWidth(STATUS_COLUMN_INDEX, "200");
        content.getColumnFormatter().setWidth(STATE_COLUMN_INDEX, "150");
        
        content.setHTML(HEADER_ROW_INDEX, ID_COLUMN_INDEX, constants.idColumn());
        content.setHTML(HEADER_ROW_INDEX, TITLE_COLUMN_INDEX, constants.titleColumn());
        content.setHTML(HEADER_ROW_INDEX, STATUS_COLUMN_INDEX, constants.statusColumn());
        content.setHTML(HEADER_ROW_INDEX, STATE_COLUMN_INDEX, constants.stateColumn());
        content.getRowFormatter().setStyleName(0, StyleConstants.SIGNALING_LIST_HEADER);
        panel.add(content);
        initWidget(panel);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onModelChange(ModelForView model) {
        if (model instanceof SignalingModel) {
            final SignalingModel signalingModel = (SignalingModel) model;

            final List<SignalingElement> elements = signalingModel.getValue();
            content.resizeRows(elements.size() + CONSTANT_ROWS_COUNT);
            int row = CONSTANT_ROWS_COUNT;
            for (SignalingElement element : elements) {
                content.setHTML(row, ID_COLUMN_INDEX, Long.toString(element.getId()));
                content.setHTML(row, TITLE_COLUMN_INDEX, element.getTitle());
                final String status = element.isEnabled() ? constants.enabledStatus() : constants.disabledStatus();
                content.setHTML(row, STATUS_COLUMN_INDEX, status);
                final String state = element.isAlarm() ? constants.alarmState() : constants.normalState();
                content.setHTML(row, STATE_COLUMN_INDEX, state);
                ++row;
            }
            content.setVisible(true);
        }
    }

}
