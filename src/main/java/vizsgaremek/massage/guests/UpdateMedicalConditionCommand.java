package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.guests.MedicalCondition;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMedicalConditionCommand {

    private MedicalCondition medicalCondition;
}
