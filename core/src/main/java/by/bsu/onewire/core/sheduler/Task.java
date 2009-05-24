package by.bsu.onewire.core.sheduler;

import by.bsu.onewire.core.network.NetworkManager;

/**
 * Common interface for tasks that can be executed by scheduler in 1-Wire
 * context.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface Task {

	/**
	 * Action that should be executed in 1-Wire context.
	 */
	void execute();
	
	NetworkManager getNetworkManager();
	
	void setNetworkManager(NetworkManager networkManager);
}
