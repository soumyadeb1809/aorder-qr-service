package in.aorder.qr.util.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import in.aorder.qr.constant.PropertyKey;
import org.springframework.beans.factory.annotation.Value;

import java.awt.image.BufferedImage;


public class ZXingQrCodeGenerator implements QrCodeGenerator {

    @Value(PropertyKey.Qr.WIDTH)
    private String width;

    @Value(PropertyKey.Qr.HEIGHT)
    private String height;

    public BufferedImage generate(String text) throws WriterException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(text, BarcodeFormat.QR_CODE, Integer.parseInt(width), Integer.parseInt(height));

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
