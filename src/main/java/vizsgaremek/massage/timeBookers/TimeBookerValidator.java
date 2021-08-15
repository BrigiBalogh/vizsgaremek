package vizsgaremek.massage.timeBookers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Data
@AllArgsConstructor
public class TimeBookerValidator implements ConstraintValidator<ValidTimeBooker, CreateTimeBookerCommand> {


    @Autowired
    private final TimeBookerRepository repository;


    @Override
    public boolean isValid(CreateTimeBookerCommand command, ConstraintValidatorContext context) {

        return repository.findTimeBookerBetweenStartTimeAndEndTime(command.getStartTime(), command.getEndTime()).isEmpty();
    }

}
