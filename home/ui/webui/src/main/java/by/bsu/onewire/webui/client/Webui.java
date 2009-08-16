package by.bsu.onewire.webui.client;

import by.bsu.onewire.webui.client.controller.MainController;

import com.googlecode.gwtmvc.client.MvcEntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Webui extends MvcEntryPoint {

    public Webui() {
        super(new MainController());
    }
}
