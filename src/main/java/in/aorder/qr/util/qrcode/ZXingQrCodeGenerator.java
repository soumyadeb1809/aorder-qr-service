package in.aorder.qr.util.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;

import java.awt.image.BufferedImage;


public class ZXingQrCodeGenerator implements QrCodeGenerator {

    @Value("${qr.width}")
    private static String WIDTH = "200";

    @Value("${qr.height}")
    private static String HEIGHT = "200";

    public BufferedImage generate(String text) throws WriterException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(text, BarcodeFormat.QR_CODE, Integer.parseInt(WIDTH), Integer.parseInt(HEIGHT));

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
