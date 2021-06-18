package in.aorder.qr.controller;

import in.aorder.qr.dto.rest.response.CreateResourceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcodes")
public class QrCodeController {

    public CreateResourceResponse createQrCode() {
        CreateResourceResponse response = new CreateResourceResponse();

        return response;
    }
}
