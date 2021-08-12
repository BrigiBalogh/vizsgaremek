package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UpdateTimeBookerCommand {

    private LocalDateTime startTime;


}
