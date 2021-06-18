package in.aorder.qr.util.qrcode;

import java.awt.image.BufferedImage;

public interface QrCodeGenerator {

    /**
     * Method to generate a QR Code from using the provided text.
     *
     * @param text to create the QR code
     * @return BufferedImage
     * @throws Exception if any
     */
    BufferedImage generate(String text) throws Exception;

}
