package vizsgaremek.massage.timeBookers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.guests.Guest;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTimeBookerCommand {

    @Schema(description = "Time of the massage")
    @NotBlank(message = "Start time can not be blank")
    private LocalDateTime startTime;

    @NotBlank(message = "End time can not be blank")
    private LocalDateTime endTime;


    @NotBlank(message = "Name can not be blank")
    private Guest guest;

    @NotBlank(message = "Status can not be blank")
    private Status status;
}
