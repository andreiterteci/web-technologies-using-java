package ro.fmi.HeathTracker.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.fmi.HeathTracker.model.dto.MealModel;
import ro.fmi.HeathTracker.model.responses.RestResponseModel;
import ro.fmi.HeathTracker.service.MealService;
import ro.fmi.HeathTracker.service.MessageService;
import ro.fmi.HeathTracker.util.ResponseEntityUtil;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MealModel>> getAll() {
        return ResponseEntity.ok(mealService.getAllByUser());
    }

    @PostMapping
    public ResponseEntity<RestResponseModel> create(@RequestBody final MealModel mealModel, final BindingResult bindingResult) {
        mealModel.setId(null);

        if (bindingResult.hasErrors()) {
            return ResponseEntityUtil.getRestResponseModelWithErrors(messageService.getAllErrors(bindingResult));
        }

        mealService.create(mealModel);

        return ResponseEntityUtil.getRestResponseModel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealModel> getOne(@PathVariable final String id) {
        return ResponseEntity.ok(mealService.getById(id));
    }

    @PutMapping
    public ResponseEntity<RestResponseModel> update(@RequestBody final MealModel mealModel, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntityUtil.getRestResponseModelWithErrors(messageService.getAllErrors(bindingResult));
        }

        mealService.update(mealModel);

        return ResponseEntityUtil.getRestResponseModel();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponseModel> delete(@PathVariable final String id) {
        mealService.delete(id);
        return ResponseEntityUtil.getRestResponseModel();
    }
}
