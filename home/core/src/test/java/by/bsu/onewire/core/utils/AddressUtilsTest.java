package by.bsu.onewire.core.utils;

import org.junit.Assert;
import org.junit.Test;

import by.bsu.onewire.common.utils.AddressUtils;

import com.dalsemi.onewire.utils.Address;

public class AddressUtilsTest {
    @Test
    public void testConvertion() {
        long address = -1152921458321849599L;
        String expected = Address.toString(address);
        String actuals = AddressUtils.toString(address);
        Assert.assertTrue(actuals.equalsIgnoreCase(expected));
        long test = AddressUtils.toLong(actuals);
        Assert.assertEquals(address, test);
    }

    @Test
    public void testLongConvertion() {
        long result = AddressUtils.toLong("F000000AC6CD0301");
        Assert.assertEquals(-1152921458321849599L, result);
    }
}
