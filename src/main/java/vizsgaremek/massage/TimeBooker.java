package vizsgaremek.massage;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_bookers")
public class TimeBooker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    @JoinColumn(name = "time_booker_guest")
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Guest guest;

    public TimeBooker(LocalDateTime startTime, LocalDateTime endTime, Guest guest) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.guest = guest;
    }

    public TimeBooker(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}