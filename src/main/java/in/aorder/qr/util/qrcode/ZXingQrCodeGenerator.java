package in.aorder.qr.util.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;

import java.awt.image.BufferedImage;

import static in.aorder.qr.constant.PropertyKey.Qr;


public class ZXingQrCodeGenerator implements QrCodeGenerator {

    @Value(Qr.WIDTH)
    private String width;

    @Value(Qr.HEIGHT)
    private String height;

    public BufferedImage generate(String text) throws WriterException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(text, BarcodeFormat.QR_CODE, Integer.parseInt(width), Integer.parseInt(height));

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
