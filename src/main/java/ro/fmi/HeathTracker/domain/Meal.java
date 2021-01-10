package ro.fmi.HeathTracker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ro.fmi.HeathTracker.util.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private Long calories;

    @Convert(converter = StringListConverter.class)
    private List<String> names;

    @ManyToOne
    @JoinColumn(name = "daily_data_id")
    private DailyData dailyData;
}
