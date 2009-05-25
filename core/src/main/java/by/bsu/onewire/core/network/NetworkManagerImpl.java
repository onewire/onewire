package by.bsu.onewire.core.network;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.OneWireIOException;

import by.bsu.onewire.core.network.extensions.ConvertExtension;
import by.bsu.onewire.core.network.extensions.SearchExtension;

public class NetworkManagerImpl implements NetworkManager {

    private AdapterProvider adapterProvider;

    private SearchExtension searchExtension;

    private ConvertExtension convertExtension;

    public AdapterProvider getAdapterProvider() {
        return adapterProvider;
    }

    public void setAdapterProvider(AdapterProvider adapterProvider) {
        this.adapterProvider = adapterProvider;
    }

    public SearchExtension getSearchExtension() {
        return searchExtension;
    }

    public void setSearchExtension(SearchExtension searchExtension) {
        this.searchExtension = searchExtension;
    }

    public ConvertExtension getConvertExtension() {
        return convertExtension;
    }

    public void setConvertExtention(ConvertExtension convertExtention) {
        this.convertExtension = convertExtention;
    }

    @Override
    public void endSession() throws OneWireIOException, OneWireException {
        adapterProvider.getAdapter().endExclusive();
    }

    @Override
    public void startSession() throws OneWireIOException, OneWireException {
        adapterProvider.getAdapter().beginExclusive(true);
    }

}
