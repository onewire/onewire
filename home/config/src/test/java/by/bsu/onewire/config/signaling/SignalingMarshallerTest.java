package by.bsu.onewire.config.signaling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bsu.onewire.common.modules.signaling.SignalingElement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml"})
public class SignalingMarshallerTest {

    @Resource
    Marshaller marshaller;
    
    @Autowired
    Unmarshaller unmarshaller;
    
    private static final String xmlString = 
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
        "<signaling-element id=\"1\" enabled=\"true\">\n" + 
        "    <title>title</title>\n" + 
        "    <keyAddress>0000000000000016</keyAddress>\n"+ 
        "    <labelAddress>0000000000000017</labelAddress>\n" + 
        "</signaling-element>\n";
    
    @Test
    public void marshalingTest() throws XmlMappingException, IOException
    {
        SignalingElement element = createElement();
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        marshaller.marshal(element, result);
        String resultString = writer.toString();
        assertEquals("Object should be converted to xml correctly",xmlString, resultString);
    }
    
    @Test
    public void unmarshalingTest() throws XmlMappingException, IOException
    {
        StreamSource source = new StreamSource(new StringReader(xmlString));
        Object result = unmarshaller.unmarshal(source);
        checkElement((SignalingElement) result);
    }

    private SignalingElement createElement() {
        SignalingElement element = new SignalingElement();
        element.setEnabled(true);
        element.setId(1);
        element.setKeyAddress(22);
        element.setLabelAddress(23);
        element.setTitle("title");
        return element;
    }
    
    private void checkElement(SignalingElement element)
    {
        assertNotNull(element);
        assertEquals("Enabled status should be correct", true, element.isEnabled());
        assertEquals("Element Id should be correct", 1, element.getId());
        assertEquals("Element key address should be correct", 22, element.getKeyAddress());
        assertEquals("Element label address should be correct", 23, element.getLabelAddress());
        assertEquals("Element Ititle should be correct", "title", element.getTitle());
    }
}
