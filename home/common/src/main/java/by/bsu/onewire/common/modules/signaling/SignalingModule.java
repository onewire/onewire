package by.bsu.onewire.common.modules.signaling;

import java.util.List;

import by.bsu.onewire.common.modules.signaling.SignalingElement;

public interface SignalingModule {
	List<SignalingElement> getElements();

	boolean isAlarm();
}
