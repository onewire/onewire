package by.bsu.onewire.core.modules.monitoring.factory;

import org.junit.Before;
import org.junit.Test;

import com.dalsemi.onewire.container.OneWireContainer;

import by.bsu.onewire.common.device.sensor.SensorType;
import by.bsu.onewire.core.modules.monitoring.sensor.SensorReader;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CompositeReaderFactoryTest {

    private TypedReaderFactory firstFactory;
    private TypedReaderFactory secondFactory;
    private CompositeReaderFactory factory;

    @Before
    public void setUp() throws Exception {
        firstFactory = mock(TypedReaderFactory.class);
        secondFactory = mock(TypedReaderFactory.class);
        factory = new CompositeReaderFactory();
        factory.addFactory(SensorType.HumiditySensor, firstFactory);
        factory.addFactory(SensorType.HumiditySensor, secondFactory);

    }

    @Test
    public void createReader() {
        final OneWireContainer container = mock(OneWireContainer.class);
        final SensorReader reader = mock(SensorReader.class);
        when(firstFactory.createReader(container)).thenReturn(null);
        when(secondFactory.createReader(container)).thenReturn(reader);

        final SensorReader actual = factory.createReader(SensorType.HumiditySensor, container);
        assertThat("Reader instance should be correct", actual == reader, is(true));
    }

    @Test
    public void createReaderFirstFactory() {
        final OneWireContainer container = mock(OneWireContainer.class);
        final SensorReader reader = mock(SensorReader.class);
        when(firstFactory.createReader(container)).thenReturn(reader);

        final SensorReader actual = factory.createReader(SensorType.HumiditySensor, container);
        assertThat("Reader instance should be correct", actual == reader, is(true));
        verify(secondFactory, never()).createReader(container);
    }

    @Test
    public void unsupportedContainer() {
        final OneWireContainer container = mock(OneWireContainer.class);
        when(firstFactory.createReader(container)).thenReturn(null);
        when(secondFactory.createReader(container)).thenReturn(null);

        final SensorReader actual = factory.createReader(SensorType.HumiditySensor, container);
        assertThat("Reader instance should be correct", actual, is(nullValue()));
    }

    @Test
    public void unsupportedType() {
        final OneWireContainer container = mock(OneWireContainer.class);
        final SensorReader actual = factory.createReader(SensorType.TemperatureSensor, container);
        assertThat("Reader instance should be correct", actual, is(nullValue()));
    }
}
