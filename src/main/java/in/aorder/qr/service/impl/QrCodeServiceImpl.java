package in.aorder.qr.service.impl;

import in.aorder.qr.constant.PropertyKey;
import in.aorder.qr.dto.common.QrCodeDto;
import in.aorder.qr.dto.rest.request.CreateQrCodeRequest;
import in.aorder.qr.entity.QrCode;
import in.aorder.qr.exception.ResourceNotFoundException;
import in.aorder.qr.repository.QrCodeRepository;
import in.aorder.qr.service.QrCodeService;
import in.aorder.qr.util.common.CommonUtil;
import in.aorder.qr.util.common.DtoFactory;
import in.aorder.qr.util.common.EntityBuilder;
import in.aorder.qr.util.fileupload.FileUploadProvider;
import in.aorder.qr.util.qrcode.QrCodeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;


@Service
public class QrCodeServiceImpl implements QrCodeService {

    private static final Logger LOG = LogManager.getLogger(QrCodeServiceImpl.class);

    @Autowired
    private QrCodeGenerator qrCodeGenerator;

    @Autowired
    private FileUploadProvider fileUploader;

    @Autowired
    private QrCodeRepository qrCodeRepo;

    @Value(PropertyKey.Firebase.STORAGE_QR_DIRECTORY)
    private String qrCodesDirectory;

    @Override
    public Integer createQrCode(CreateQrCodeRequest request) {

        QrCode qrCode = new QrCode();

        try {
            BufferedImage image = generateQrCode(request.getMetadata());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            byte [] data = bos.toByteArray();
            String fileName = qrCodesDirectory + "/" + CommonUtil.generateUUID("qr_") + ".png";
            String downloadUrl = fileUploader.upload(data, fileName);

            EntityBuilder.build(qrCode, request.getMetadata(), downloadUrl);
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

        try {
            Optional<QrCode> qrCodeOp = qrCodeRepo.findById(id);

            if(!qrCodeOp.isPresent()) {
                throw new ResourceNotFoundException("Filed to find QrCode with Id: " + id);
            }

            qrCodeDto = DtoFactory.createQrCodeDto(qrCodeOp.get());
        }
        catch (ResourceNotFoundException e) {
            throw e;
        }
        catch (Exception e) {
            LOG.error("Failed to get QrCode Id: " + id, e);
        }

        return qrCodeDto;
    }
}
