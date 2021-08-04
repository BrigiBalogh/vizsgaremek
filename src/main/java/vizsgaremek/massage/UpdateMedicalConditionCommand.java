package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMedicalConditionCommand {

    private MedicalCondition medicalCondition;
}
