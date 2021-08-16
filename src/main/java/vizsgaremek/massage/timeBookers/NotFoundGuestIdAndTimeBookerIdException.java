package vizsgaremek.massage.timeBookers;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

public class NotFoundGuestIdAndTimeBookerIdException extends AbstractThrowableProblem {




    public NotFoundGuestIdAndTimeBookerIdException (long id, long timeBookerId) {
        super(URI.create("guestsWithTime-bookers/not-found"),
                "Not found",
                org.zalando.problem.Status.NOT_FOUND,
                String.format("Not found with id '%d' and timeBookerId '%d'", id,timeBookerId));
    }
}
