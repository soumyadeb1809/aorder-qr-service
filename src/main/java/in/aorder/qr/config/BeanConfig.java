package in.aorder.qr.config;

import in.aorder.qr.util.fileupload.FileUploadProvider;
import in.aorder.qr.util.fileupload.FirebaseFileUploadProvider;
import in.aorder.qr.util.qrcode.QrCodeGenerator;
import in.aorder.qr.util.qrcode.ZXingQrCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public QrCodeGenerator getQrCodeGenerator() {
        return new ZXingQrCodeGenerator();
    }

    @Bean
    public FileUploadProvider getFileUploadProvider() {
        return new FirebaseFileUploadProvider();
    }

}
