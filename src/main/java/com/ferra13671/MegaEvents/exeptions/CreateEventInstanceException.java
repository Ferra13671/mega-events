package com.ferra13671.MegaEvents.exeptions;

/**
 * @author Ferra13671
 * @LastUpdate 1.4
 *
 * This exception is thrown when an error occurs while creating a new instance of an event.
 * This can often be due to invalid arguments or inability to access the initialization method.
 */

public class CreateEventInstanceException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public CreateEventInstanceException(String message) {
        super(message);
    }

    public CreateEventInstanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateEventInstanceException(Throwable cause) {
        super(cause);
    }
}
