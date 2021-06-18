package in.aorder.qr.util.common;

import java.util.UUID;

public class CommonUtil {

    /**
     * Utility method to generate a random UUID with the prefix, if provided
     *
     * @param prefix if needed
     * @return uuid
     */
    public static String generateUUID(String prefix) {
        String uuid = UUID.randomUUID().toString();;

        if(prefix == null) {
            return uuid;
        }
        else {
            return prefix + uuid;
        }
    }

}
