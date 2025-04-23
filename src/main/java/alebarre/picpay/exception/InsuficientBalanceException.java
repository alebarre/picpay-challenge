package alebarre.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsuficientBalanceException extends PicPayException{
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Insuficient balance for this transaction");
        pb.setDetail("You cannot tranfer a value bigger than your current balance");
        return pb;
    }
}
