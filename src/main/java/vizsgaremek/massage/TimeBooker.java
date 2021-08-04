package vizsgaremek.massage;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timeBookers")
public class TimeBooker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime newTimeBooker;


    @JoinColumn(name = "time_booker_guest")
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Guest guest;


    public TimeBooker(LocalDateTime newTimeBooker) {
        this.newTimeBooker = newTimeBooker;
    }
}