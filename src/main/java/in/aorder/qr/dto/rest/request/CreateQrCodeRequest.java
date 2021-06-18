package in.aorder.qr.dto.rest.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateQrCodeRequest {
    private String metadata;
}
