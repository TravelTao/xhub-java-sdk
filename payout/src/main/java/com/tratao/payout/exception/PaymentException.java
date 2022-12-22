package com.tratao.payout.exception;

/**
 * Payment Exception
 *
 * When hit errors throw this exception.
 */
public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}
