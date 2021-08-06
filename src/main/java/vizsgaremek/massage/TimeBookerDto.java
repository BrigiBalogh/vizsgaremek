package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeBookerDto {


    private LocalDateTime startTime;
    private LocalDateTime endTime;
  //  private Guest guest;
}
