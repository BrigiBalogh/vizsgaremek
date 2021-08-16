package vizsgaremek.massage.guests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vizsgaremek.massage.timeBookers.TimeBooker;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "guest_phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_condition")
    private MedicalCondition medicalCondition;


    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimeBooker> timeBookers;


    public Guest(String name, String phoneNumber, MedicalCondition medicalCondition) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.medicalCondition = medicalCondition;
    }

}
