package by.bsu.onewire.core.modules.signaling;

import java.util.List;

public interface SignalingModule {
	List<SignalingElement> getElements();

	boolean isAlarm();
}
