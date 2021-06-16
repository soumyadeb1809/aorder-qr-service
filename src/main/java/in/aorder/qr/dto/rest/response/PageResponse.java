package in.aorder.qr.dto.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageResponse extends BaseResponse {
    private Integer start;
    private Integer limit;
    private Integer size;
}
