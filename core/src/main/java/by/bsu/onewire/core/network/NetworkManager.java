package by.bsu.onewire.core.network;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;

import by.bsu.onewire.core.network.extensions.ConvertExtension;
import by.bsu.onewire.core.network.extensions.SearchExtension;

/**
 * 1-Wire context, provide other modules access to network throught extentions.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface NetworkManager {

    /**
     * Begin exclusive usage of 1-Wire network. 
     */
    void startSession() throws OneWireIOException, OneWireException;

    /**
     * End exclusive usage of 1-Wire network. 
     */
    void endSession() throws OneWireIOException, OneWireException;

    /**
     * Returns <code>SearchExtention</code>.
     */
    SearchExtension getSearchExtension();

    /**
     * Returns <code>ConvertExtension</code>. 
     */
    ConvertExtension getConvertExtension();

}
