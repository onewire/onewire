package by.bsu.onewire.webui.client.controller;

import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.Event;

public abstract class ControllerBase extends Controller {

    protected boolean initialized;

    @Override
    public void init() {
        initialized = true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void call(Event event) {
        if(!initialized){
            init();
        }
        super.call(event);
    }
    
    

}
