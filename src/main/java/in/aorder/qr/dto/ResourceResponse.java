package in.aorder.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceResponse<T> extends BaseResponse {
    private T data;
}
