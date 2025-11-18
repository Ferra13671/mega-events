package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.eventbus.LambdaListener;

/**
 * All EventBus listener registration managers available in MegaEvents.
 */
public final class RegistrationDispatchers {
    public static final RegistrationDispatcher<Object> OBJECT = new ObjectRegistrationDispatcher();
    public static final RegistrationDispatcher<LambdaListener<?>> LAMBDA = new LambdaRegistrationDispatcher();
}
