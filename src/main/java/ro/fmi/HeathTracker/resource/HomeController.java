package ro.fmi.HeathTracker.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fmi.HeathTracker.model.dto.HealthModel;
import ro.fmi.HeathTracker.service.HomeService;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/healthScore")
    public ResponseEntity<HealthModel> getHealthScore() {
        return ResponseEntity.ok(homeService.calculateUserScore());
    }
}
