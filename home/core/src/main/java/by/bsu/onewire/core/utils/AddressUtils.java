package by.bsu.onewire.core.utils;

public class AddressUtils {

    public static String toString(long address) {
        String shortString = Long.toHexString(address).toUpperCase();
        String string = "0000000000000000" + shortString;
        return string.substring(string.length() - 16);
    }

    public static long toLong(String address) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            byte address_byte = (byte) ((Character.digit((address.charAt(i * 2)), 16) << 4) | (Character.digit(address
                    .charAt(i * 2 + 1), 16)));
            result <<= 8;
            result |= (long) (address_byte & 0xFF);
        }
        return result;
    }
}
