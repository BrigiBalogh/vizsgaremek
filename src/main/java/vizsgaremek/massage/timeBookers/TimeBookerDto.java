package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.guests.Guest;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeBookerDto {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Status status;

    private Guest guest;

}
