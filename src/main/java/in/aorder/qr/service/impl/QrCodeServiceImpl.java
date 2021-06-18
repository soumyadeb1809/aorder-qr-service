package in.aorder.qr.service.impl;

import in.aorder.qr.dto.common.QrCodeDto;
import in.aorder.qr.dto.rest.request.CreateQrRequest;
import in.aorder.qr.entity.QrCode;
import in.aorder.qr.repository.QrCodeRepository;
import in.aorder.qr.service.QrCodeService;
import in.aorder.qr.util.common.CommonUtil;
import in.aorder.qr.util.fileupload.FileUploadProvider;
import in.aorder.qr.util.qrcode.QrCodeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static final Logger LOG = LogManager.getLogger(QrCodeServiceImpl.class);

    @Autowired
    private QrCodeGenerator qrCodeGenerator;

    @Autowired
    private FileUploadProvider fileUploader;

    @Autowired
    private QrCodeRepository qrCodeRepo;

    @Override
    public Integer createQrCode(CreateQrRequest request) {

        QrCode qrCode = new QrCode();

        try {
            BufferedImage image = generateQrCode(request.getMetaData());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            byte [] data = bos.toByteArray();
            String downloadUrl = fileUploader.upload(data, CommonUtil.generateUUID("qr_"));

            qrCode.setMetadata(request.getMetaData());
            qrCode.setImagePath(downloadUrl);

            qrCodeRepo.save(qrCode);
            LOG.info("Created QrCode Id: " + qrCode.getId());
        }
        catch (IOException e) {
            LOG.error("Failed to create QR code", e);
        }

        return qrCode.getId();
    }

    /**
     * Helper method to generate a QR code for the provided text.
     *
     * @param text to generate QR code
     * @return image
     */
    public BufferedImage generateQrCode(String text) {
        BufferedImage image = null;

        try {
            image = qrCodeGenerator.generate(text);
        }
        catch (Exception e) {
            LOG.error("Failed to generate QR code for text:" + text, e);
        }
        return image;
    }

    @Override
    public QrCodeDto getQrCode(Integer id) {

        QrCodeDto qrCodeDto = null;

        return qrCodeDto;
    }
}
