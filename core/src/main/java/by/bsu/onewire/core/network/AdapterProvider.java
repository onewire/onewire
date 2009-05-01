package by.bsu.onewire.core.network;

import com.dalsemi.onewire.adapter.DSPortAdapter;

/**
 * Adapter provider interface. Provide access to correct 1-Wire adapter.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public interface AdapterProvider {
    /**
     * Returns current 1-Wire adapter.
     */
    DSPortAdapter getadapter();
}
