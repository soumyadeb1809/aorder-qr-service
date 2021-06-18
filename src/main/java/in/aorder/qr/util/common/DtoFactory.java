package in.aorder.qr.util.common;

import in.aorder.qr.dto.common.QrCodeDto;
import in.aorder.qr.entity.QrCode;

public class DtoFactory {

    /**
     * Utility method to create QrCodeDto from QrCode entity.
     * 
     * @param qrCode entity
     * @return dto
     */
    public static QrCodeDto createQrCodeDto(QrCode qrCode) {
        QrCodeDto qrCodeDto = new QrCodeDto();
        qrCodeDto.setId(qrCode.getId());
        qrCodeDto.setImageUrl(qrCode.getImageUrl());
        return qrCodeDto;
    }

}
