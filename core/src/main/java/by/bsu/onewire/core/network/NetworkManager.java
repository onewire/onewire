package by.bsu.onewire.core.network;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;

import by.bsu.onewire.core.network.extensions.ConvertExtension;
import by.bsu.onewire.core.network.extensions.SearchExtension;

/**
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface NetworkManager {

    void startSession() throws OneWireIOException, OneWireException;

    void endSession() throws OneWireIOException, OneWireException;

    SearchExtension getSearchExtension();

    ConvertExtension getConvertExtension();

}
