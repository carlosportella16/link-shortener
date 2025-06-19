package encurtador.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DomainException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        return pb;
    }
}
