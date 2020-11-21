package ro.fmi.HeathTracker.model.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResponseModel {

    private Boolean success;
    private String entityId;
    private List<ErrorModel> errors;
}
