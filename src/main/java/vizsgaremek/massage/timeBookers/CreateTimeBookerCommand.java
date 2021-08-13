package vizsgaremek.massage.timeBookers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.guests.Guest;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimeBookerCommand {

    @Schema(description = "Time of the massage")
    @NotNull(message = "Start time can not be null")
    private LocalDateTime startTime;

    @NotNull(message = "End time can not be null")
    private LocalDateTime endTime;

    @NotNull(message = "Status can not be null")
    private Status status;

    @NotNull(message = "Name can not be null")
    private Long guestId;
}
