package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UpdateTimeBookerCommand {

    @NotBlank(message = "startTime cannot be blank")
    private LocalDateTime startTime;

    @NotBlank(message = "endTime cannot be blank")
    private LocalDateTime endTime;

    private Status status;
}
