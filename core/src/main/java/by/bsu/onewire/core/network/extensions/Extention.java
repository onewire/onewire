package by.bsu.onewire.core.network.extensions;

import com.dalsemi.onewire.adapter.DSPortAdapter;

public interface Extention {

    public DSPortAdapter getAdapter();

    public void setAdapter(DSPortAdapter adapter);
    
}
