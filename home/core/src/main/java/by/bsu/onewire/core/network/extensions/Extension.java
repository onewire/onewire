package by.bsu.onewire.core.network.extensions;

import com.dalsemi.onewire.adapter.DSPortAdapter;

/**
 * Base extension interface. All extension should implement this interface to get access
 * @author Aliaksandr Zlobich
 *
 */
public interface Extension {

    public DSPortAdapter getAdapter();

    public void setAdapter(DSPortAdapter adapter);
    
}
