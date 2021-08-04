package vizsgaremek.massage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "time_bookers_of_guest")
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private Set<TimeBooker> timeBookers = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    public Guest(String name, String phoneNumber, MedicalCondition medicalCondition, Set<TimeBooker> timeBookers, Status status) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.medicalCondition = medicalCondition;
        this.timeBookers = timeBookers;
        this.status = status;
    }

    public Guest(String name, String phoneNumber, MedicalCondition medicalCondition) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.medicalCondition = medicalCondition;
    }

    public Guest(String name, String phoneNumber, MedicalCondition medicalCondition, Set<TimeBooker> timeBookers) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.medicalCondition = medicalCondition;
        this.timeBookers = timeBookers;
    }

    public void addTimeBooker(TimeBooker timeBooker) {
        if(timeBookers == null) {
            timeBookers = new HashSet<>();
        }
        timeBookers.add(timeBooker);
        timeBooker.setGuest(this);
    }
}
