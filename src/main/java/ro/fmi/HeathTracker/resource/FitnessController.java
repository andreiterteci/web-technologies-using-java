package ro.fmi.HeathTracker.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.fmi.HeathTracker.model.dto.FitnessModel;
import ro.fmi.HeathTracker.model.responses.RestResponseModel;
import ro.fmi.HeathTracker.service.FitnessService;
import ro.fmi.HeathTracker.service.MessageService;
import ro.fmi.HeathTracker.util.ResponseEntityUtil;

import java.util.List;

@RestController
@RequestMapping("/api/fitness")
@RequiredArgsConstructor
public class FitnessController {

    private final FitnessService fitnessService;

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<FitnessModel>> getAll() {
        return ResponseEntity.ok(fitnessService.getAllByUser());
    }

    @PostMapping
    public ResponseEntity<RestResponseModel> create(@RequestBody final FitnessModel fitnessModel, final BindingResult bindingResult) {
        fitnessModel.setId(null);

        if (bindingResult.hasErrors()) {
            return ResponseEntityUtil.getRestResponseModelWithErrors(messageService.getAllErrors(bindingResult));
        }

        fitnessService.create(fitnessModel);

        return ResponseEntity.ok(RestResponseModel
                .builder()
                .success(Boolean.TRUE)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FitnessModel> getOne(@PathVariable final String id) {
        return ResponseEntity.ok(fitnessService.getById(id));
    }

    @PutMapping
    public ResponseEntity<RestResponseModel> update(@RequestBody final FitnessModel fitnessModel, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntityUtil.getRestResponseModelWithErrors(messageService.getAllErrors(bindingResult));
        }

        fitnessService.update(fitnessModel);

        return ResponseEntityUtil.getRestResponseModel();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponseModel> delete(@PathVariable final String id) {
        fitnessService.delete(id);
        return ResponseEntityUtil.getRestResponseModel();
    }
}
