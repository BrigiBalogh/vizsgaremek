package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTimeBookerCommand {


    private long timeBookerId;
}
