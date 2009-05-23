package by.bsu.onewire.core.network;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;

/**
 * Simple implementation of adapter provider, provide access to default 1-wire
 * network adapter.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class AdapterProviderDefaultImpl extends AdapterProviderBase {

	public static final String DEFAULT_ADAPTER_PROPERTY = "onewire.adapter.default";
	public static final String DEFAULT_PORT_PROPERTY = "onewire.port.default";

	/**
	 * Returns default 1-Wire network adapter.
	 */
	protected DSPortAdapter retrieveAdapter() throws OneWireIOException, OneWireException{
		return OneWireAccessProvider.getDefaultAdapter();
	}

}
