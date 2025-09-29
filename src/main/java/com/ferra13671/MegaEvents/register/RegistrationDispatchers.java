package com.ferra13671.MegaEvents.register;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.5
 */

public final class RegistrationDispatchers {
    public static final RegistrationDispatcher OBJECT = new ObjectRegistrationDispatcher();
    public static final RegistrationDispatcher CONSUMER = new ConsumerRegistrationDispatcher();
}
