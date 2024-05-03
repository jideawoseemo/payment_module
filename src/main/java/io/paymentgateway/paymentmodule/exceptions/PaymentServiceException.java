package io.paymentgateway.paymentmodule.exceptions;

public class PaymentServiceException extends Exception{
    public PaymentServiceException(String message) {
        super(message);
    }

    public PaymentServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentServiceException(Throwable cause) {
        super(cause);
    }}
