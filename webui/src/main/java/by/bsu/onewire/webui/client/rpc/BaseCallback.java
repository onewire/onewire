package by.bsu.onewire.webui.client.rpc;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class BaseCallback<T> implements AsyncCallback<T> {

    public void onFailure(Throwable caught) {
        
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Remote call error");
//        dialogBox.setAnimationEnabled(true);
        
        final Button closeButton = new Button("Close");
        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
        
        final VerticalPanel panel = new VerticalPanel();
        panel.add(new HTML(caught.toString()));
        panel.add(closeButton);
        dialogBox.setWidget(panel);
        
        dialogBox.setModal(true);
        dialogBox.center();
        
    }
}
