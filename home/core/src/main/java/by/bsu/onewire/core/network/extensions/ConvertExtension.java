package by.bsu.onewire.core.network.extensions;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 * Extension provide functionality to initiate temperature conversion.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface ConvertExtension {

    /**
     * Initiate temperature conversion and wait for it.
     */
    void doTemperatureConversion() throws OneWireIOException, OneWireException;
}
