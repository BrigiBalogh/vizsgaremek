package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {

    private String name;

    private String phoneNumber;

    private MedicalCondition medicalCondition;

    private Set<TimeBookerDto> timeBookers;

  //  private Status status;

}
