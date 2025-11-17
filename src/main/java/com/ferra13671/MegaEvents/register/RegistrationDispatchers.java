package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.lambda.LambdaListener;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.8
 */

public final class RegistrationDispatchers {
    public static final RegistrationDispatcher<Object> OBJECT = new ObjectRegistrationDispatcher();
    public static final RegistrationDispatcher<LambdaListener<?>> LAMBDA = new LambdaRegistrationDispatcher();
}
