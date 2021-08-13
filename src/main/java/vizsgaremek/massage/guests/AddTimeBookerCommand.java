package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.timeBookers.Status;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTimeBookerCommand {


    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Status status;
}
