package vizsgaremek.massage;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timeBookers")
public class TimeBooker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "new_time_booker")
    private LocalDateTime newTimeBooker;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


    @JoinColumn(name = "time_booker_guest")
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Guest guest;


    public TimeBooker(LocalDateTime newTimeBooker) {
        this.newTimeBooker = newTimeBooker;
    }
}

/*  @Data
public class FutureInterval {

    @Future(message = "Must be in the future")
    private final LocalDateTime start;

    @Future(message = "Must be in the future")
    private final LocalDateTime end;



      public class IntervalValidator implements ConstraintValidator<ValidInterval, FutureInterval> {
    @Override
    public boolean isValid(FutureInterval futureInterval, ConstraintValidatorContext constraintValidatorContext) {
        return futureInterval.getStart().isBefore(futureInterval.getEnd());
    }



    @Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = IntervalValidator.class)
public @interface ValidInterval {

    String message() default "Invalid interval";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};




       @ValidInterval
    @Valid
    private FutureInterval futureInterval;
*/