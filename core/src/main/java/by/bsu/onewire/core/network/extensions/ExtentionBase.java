package by.bsu.onewire.core.network.extensions;

import com.dalsemi.onewire.adapter.DSPortAdapter;

public class ExtentionBase implements Extention {
    protected DSPortAdapter adapter;

    public DSPortAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DSPortAdapter adapter) {
        this.adapter = adapter;
    }
}
