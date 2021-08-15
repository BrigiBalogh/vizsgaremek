package vizsgaremek.massage.timeBookers;


import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeBookerValidator.class)
public @interface ValidTimeBooker {


    java.lang.String message() default "not valid Case";

    java.lang.Class<?>[] groups() default {};

    java.lang.Class<? extends javax.validation.Payload>[] payload() default {};
}



