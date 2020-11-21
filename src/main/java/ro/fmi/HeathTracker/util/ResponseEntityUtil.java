package ro.fmi.HeathTracker.util;

import org.springframework.http.ResponseEntity;
import ro.fmi.HeathTracker.model.responses.ErrorModel;
import ro.fmi.HeathTracker.model.responses.RestResponseModel;

import java.util.List;

public final class ResponseEntityUtil {
    private ResponseEntityUtil() {
    } // NO SONAR

    public static ResponseEntity<RestResponseModel> getRestResponseModelWithErrors(List<ErrorModel> allErrors) {
        return ResponseEntity.badRequest().body(RestResponseModel
                .builder()
                .success(Boolean.FALSE)
                .errors(allErrors)
                .build());
    }

    public static ResponseEntity<RestResponseModel> getRestResponseModel() {
        return ResponseEntity.ok(RestResponseModel
                .builder()
                .success(Boolean.TRUE)
                .build());
    }

    public static ResponseEntity<RestResponseModel> getRestResponseModelWithEntityId(final String entityId) {
        return ResponseEntity.ok(RestResponseModel
                .builder()
                .entityId(entityId)
                .success(Boolean.TRUE)
                .build());
    }
}
