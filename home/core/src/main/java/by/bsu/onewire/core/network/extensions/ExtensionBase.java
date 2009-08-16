package by.bsu.onewire.core.network.extensions;

import com.dalsemi.onewire.adapter.DSPortAdapter;

/**
 * Base class for all extentions.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class ExtensionBase implements Extension {
    protected DSPortAdapter adapter;

    public DSPortAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DSPortAdapter adapter) {
        this.adapter = adapter;
    }
}
