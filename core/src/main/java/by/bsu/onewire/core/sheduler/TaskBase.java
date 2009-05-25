package by.bsu.onewire.core.sheduler;

import by.bsu.onewire.core.network.NetworkManager;

public abstract class TaskBase implements Task{

    protected NetworkManager networkManager;

    public TaskBase() {
        super();
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }

}