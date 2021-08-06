package vizsgaremek.massage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGuestCommand {

    @Schema(description = "name of the guest", example = "frau Markgraf")
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "phoneNumber can not be blank")
    private String phoneNumber;

    private MedicalCondition medicalCondition;

}
