package ro.fmi.HeathTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ro.fmi.HeathTracker.model.responses.ErrorModel;
import ro.fmi.HeathTracker.model.responses.ErrorParameterModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    public List<ErrorModel> getAllErrors(final Errors errors) {
        List<ErrorModel> allErrors = getFieldErrors(errors);

        if (errors.hasGlobalErrors()) {
            allErrors.addAll(getGlobalErrors(errors));
        }

        return allErrors;
    }

    private List<ErrorModel> getGlobalErrors(Errors errors) {
        return errors.getGlobalErrors().stream()
                .map(error -> {
                    List<ErrorParameterModel> errorParameterModels = error.getArguments() != null
                            ? Arrays.stream(error.getArguments())
                            .filter(arg -> arg instanceof ErrorParameterModel)
                            .map(arg -> (ErrorParameterModel) arg).collect(Collectors.toList())
                            : null;
                    return new ErrorModel(error.getCode(), errorParameterModels);
                })
                .collect(Collectors.toList());
    }

    private List<ErrorModel> getFieldErrors(Errors errors) {
        if (errors.hasFieldErrors()) {
            return errors.getFieldErrors().stream().map(fieldError ->
                    ErrorModel.builder()
                            .message(fieldError.getField() + " " + fieldError.getDefaultMessage())
                            .build())
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
