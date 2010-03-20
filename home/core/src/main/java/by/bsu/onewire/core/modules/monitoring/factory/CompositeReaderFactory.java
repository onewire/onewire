package by.bsu.onewire.core.modules.monitoring.factory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import by.bsu.onewire.common.device.sensor.SensorType;
import by.bsu.onewire.core.modules.monitoring.sensor.SensorReader;

import com.dalsemi.onewire.container.OneWireContainer;

/**
 * Reader factory implementation use collection of typed reader factories to
 * correctly determine right type of reader and create it.
 * 
 * @author Aliaksandr Zlobich
 * 
 */
public class CompositeReaderFactory implements ReaderFactory {

    private Map<SensorType, List<TypedReaderFactory>> factories;

    public CompositeReaderFactory() {
        factories = new HashMap<SensorType, List<TypedReaderFactory>>();
        // Create factories lists for all sensor types.
        for (SensorType type : SensorType.values()) {
            final List<TypedReaderFactory> factoriesList = new LinkedList<TypedReaderFactory>();
            factories.put(type, factoriesList);
        }
    }

    /**
     * Register typed factory, add this factory to list associated with sensor
     * type.
     */
    public void addFactory(SensorType type, TypedReaderFactory factory) {
        final List<TypedReaderFactory> list = factories.get(type);
        list.add(factory);
    }

    /**
     * Create sensor reader. Try to use registered for given type child
     * factories to create correct reader. IIf there is no factories that
     * support this container returns <code>null</code>.
     */
    @Override
    public SensorReader createReader(SensorType sensorType, OneWireContainer container) {
        SensorReader result = null;
        final List<TypedReaderFactory> list = factories.get(sensorType);
        // Iterate over a list of reader factories associated with given type
        for (TypedReaderFactory factory : list) {
            // Try to create reader with this factory
            result = factory.createReader(container);
            // If reader successfully created break loop
            if (result != null) {
                break;
            }
        }
        return result;
    }

}
