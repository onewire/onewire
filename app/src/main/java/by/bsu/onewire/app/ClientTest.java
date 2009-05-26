package by.bsu.onewire.app;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.bsu.onewire.core.modules.signaling.SignalingModule;
import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;

public class ClientTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final AbstractApplicationContext context = new ClassPathXmlApplicationContext(new String []{"client-context.xml"});
        context.registerShutdownHook();
        SignalingModule module = (SignalingModule) context.getBean("signalingModule");
        List<SignalingElement> elements = module.getElements();
    }

}
