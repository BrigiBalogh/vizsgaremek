package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guest_name")
    private String name;

    @Column(name = "guest_phoneNumber")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_condition")
    private MedicalCondition medicalCondition;
}
