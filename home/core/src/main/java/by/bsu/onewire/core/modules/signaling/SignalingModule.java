package by.bsu.onewire.core.modules.signaling;

import java.util.List;

import by.bsu.onewire.core.modules.signaling.dto.SignalingElement;

public interface SignalingModule {
	List<SignalingElement> getElements();

	boolean isAlarm();
}
