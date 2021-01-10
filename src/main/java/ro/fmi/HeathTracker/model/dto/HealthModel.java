package ro.fmi.HeathTracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.fmi.HeathTracker.domain.enums.Recommendation;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthModel {

    private String healthScore;

    private Double healthScoreDecimal;

    private List<Recommendation> recommendations;
}
