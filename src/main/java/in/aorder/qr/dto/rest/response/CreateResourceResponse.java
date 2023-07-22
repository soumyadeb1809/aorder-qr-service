package in.aorder.qr.dto.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateResourceResponse<T> extends BaseResponse {
    private Integer id;
    private T data;
}
