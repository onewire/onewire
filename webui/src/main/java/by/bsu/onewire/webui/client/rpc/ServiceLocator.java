package by.bsu.onewire.webui.client.rpc;


import by.bsu.onewire.webui.client.rpc.mock.SignalingServiceMockImpl;

import com.google.gwt.core.client.GWT;

/**
 * Class provides endpoint for getting service instancies.
 */
public abstract class ServiceLocator
{
    /**
     * Service locator instance.
     */
    private static ServiceLocator instance;

    static
    {
        // @todo switch between Mock and REST

        String url = GWT.getHostPageBaseURL();
        if (url.startsWith("http://localhost:8888") || // GWT debug
            url.startsWith("file://") // loading from local file
            )
        {
            instance = new MockServiceLocator();
        }
        else
        {
            instance = new ServerServiceLocator();
//            instance = new MockServiceLocator();
        }
    }

    /**
     * Returns serviceLocator instance.
     *
     * @return ServiceLocator initialized instance value.
     */
    public static synchronized ServiceLocator instance()
    {
        return instance;
    }
    
    public abstract SignalingServiceAsync getSignalingService();

    /**
     * Class for mock service implementations.
     */
    private static class MockServiceLocator extends ServiceLocator
    {
        @Override
        public SignalingServiceAsync getSignalingService() {
            return new SignalingServiceMockImpl();
        }
    }

    /**
     * Class for RPC service implementations.
     */
    private static class ServerServiceLocator extends ServiceLocator
    {
        private SignalingServiceAsync signalingService;
        
        private ServerServiceLocator(){
            signalingService = GWT.create(SignalingService.class);
        }
        
        @Override
        public SignalingServiceAsync getSignalingService() {
            return signalingService;
        }
    }
}

