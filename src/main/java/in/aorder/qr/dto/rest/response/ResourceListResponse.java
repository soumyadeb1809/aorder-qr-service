package in.aorder.qr.dto.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResourceListResponse<T> extends PageResponse {
    private List<T> data;
}
