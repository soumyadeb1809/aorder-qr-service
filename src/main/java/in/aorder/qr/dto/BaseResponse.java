package in.aorder.qr.dto;

import in.aorder.qr.model.ResponseMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {
    private String message = ResponseMessage.FAILED;
}
