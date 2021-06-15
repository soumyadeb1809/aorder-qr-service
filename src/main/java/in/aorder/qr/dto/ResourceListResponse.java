package in.aorder.qr.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResourceListResponse<T> extends BaseResponse {
    private List<T> data;
}
