package in.aorder.qr.controller;

import in.aorder.qr.dto.common.QrCodeDto;
import in.aorder.qr.dto.rest.request.CreateQrCodeRequest;
import in.aorder.qr.dto.rest.response.CreateResourceResponse;
import in.aorder.qr.dto.rest.response.ResourceResponse;
import in.aorder.qr.model.ResponseMessage;
import in.aorder.qr.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcodes")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping
    public CreateResourceResponse createQrCode(CreateQrCodeRequest request) {
        CreateResourceResponse response = new CreateResourceResponse();

        Integer id = qrCodeService.createQrCode(request);
        response.setId(id);

        if(id != null) {
            response.setMessage(ResponseMessage.SUCCESS);
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResourceResponse<QrCodeDto> getQrCode(
            @PathVariable("id") Integer id
    ) {
        ResourceResponse<QrCodeDto> response = new ResourceResponse<>();

        QrCodeDto qrCode = qrCodeService.getQrCode(id);
        response.setData(qrCode);

        if(qrCode != null) {
            response.setMessage(ResponseMessage.SUCCESS);
        }

        return response;
    }
}
