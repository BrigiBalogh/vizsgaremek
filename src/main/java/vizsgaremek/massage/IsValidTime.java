package vizsgaremek.massage;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = TimeValidator.class )
public @interface IsValidTime {

    String message() default "Invalid time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
