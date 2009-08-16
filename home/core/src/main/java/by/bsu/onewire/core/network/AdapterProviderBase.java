package by.bsu.onewire.core.network;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 * Base implementation of adapter provide.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public abstract class AdapterProviderBase implements AdapterProvider {

    protected DSPortAdapter adapter;

    /**
     * Returns 1-Wire network adapter, use <code>retrieveAdapter()</code>
     * function to get adapter..
     */
    @Override
    public DSPortAdapter getAdapter() throws OneWireIOException, OneWireException {
        if (adapter == null) {
            adapter = retrieveAdapter();
        }
        return adapter;
    }

    /**
     * Returns <code>DSPortAdapter</code> implementation.
     */
    protected abstract DSPortAdapter retrieveAdapter() throws OneWireIOException, OneWireException;

}