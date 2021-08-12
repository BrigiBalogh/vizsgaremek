package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneNumberCommand {

    private String phoneNumber;
}
