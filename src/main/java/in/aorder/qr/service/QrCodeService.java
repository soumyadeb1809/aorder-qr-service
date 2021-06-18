package in.aorder.qr.service;

import in.aorder.qr.dto.common.QrCodeDto;
import in.aorder.qr.dto.rest.request.CreateQrRequest;

public interface QrCodeService {

    /**
     * Service to create a new QrCode.
     *
     * @param request dto
     * @return Id of the created QrCode
     */
    Integer createQrCode(CreateQrRequest request);

    /**
     * Service to get an existing QrCode.
     *
     * @param id of the QrCode
     * @return dto
     */
    QrCodeDto getQrCode(Integer id);
}
