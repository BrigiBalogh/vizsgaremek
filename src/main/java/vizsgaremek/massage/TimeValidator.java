package vizsgaremek.massage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TimeValidator implements ConstraintValidator <IsValidTime, LocalDateTime>{


    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value != ;
    }
}
