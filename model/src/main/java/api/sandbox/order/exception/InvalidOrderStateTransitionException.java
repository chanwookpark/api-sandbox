package api.sandbox.order.exception;


/**
 * Created by chanwook on 2015. 1. 13..
 */
public class InvalidOrderStateTransitionException extends RuntimeException {
    public InvalidOrderStateTransitionException(String message) {
        super(message);
    }
}
