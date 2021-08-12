package vizsgaremek.massage;

import org.zalando.problem.Status;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

public class NotFoundException extends AbstractThrowableProblem {


    public NotFoundException(long id) {
        super(URI.create("guests/not-found"),
                "Not found",
                org.zalando.problem.Status.NOT_FOUND,
                String.format("Not found with id '%d'", id));
    }

}
