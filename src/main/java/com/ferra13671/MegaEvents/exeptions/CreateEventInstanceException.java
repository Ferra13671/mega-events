package com.ferra13671.MegaEvents.exeptions;

/**
 * This exception is thrown when an error occurs while creating a new instance of an event.
 * This can often be due to invalid arguments or inability to access the initialization method.
 */

public class CreateEventInstanceException extends RuntimeException {
    static final long serialVersionUID = -1159757426915715653L;

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
