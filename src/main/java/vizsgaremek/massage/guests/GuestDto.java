package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.timeBookers.TimeBookerDto;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {


    private Long id;

    private String name;

    private String phoneNumber;

    private MedicalCondition medicalCondition;

    private Set<TimeBookerDto> timeBookers;

  //  private Status status;

}
