package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UpdateTimeBookerCommand {

    @NotNull(message = "startTime cannot be blank")
    private LocalDateTime startTime;

    @NotNull(message = "endTime cannot be blank")
    private LocalDateTime endTime;

    private Status status;
}
