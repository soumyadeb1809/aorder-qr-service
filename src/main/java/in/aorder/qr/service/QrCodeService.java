package in.aorder.qr.service;

import in.aorder.qr.dto.common.QrCodeDto;
import in.aorder.qr.dto.rest.request.CreateQrCodeRequest;

public interface QrCodeService {

    /**
     * Service to create a new QrCode.
     *
     * @param request dto
     * @return {@link QrCodeDto} of the created QrCode
     */
    QrCodeDto createQrCode(CreateQrCodeRequest request);

    /**
     * Service to get an existing QrCode.
     *
     * @param id of the QrCode
     * @return dto
     */
    QrCodeDto getQrCode(Integer id);
}
