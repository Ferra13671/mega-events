package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.lambda.LambdaInfo;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.7
 */

//TODO custom RegistrationDispatchers
public final class RegistrationDispatchers {
    public static final RegistrationDispatcher<Object> OBJECT = new ObjectRegistrationDispatcher();
    public static final RegistrationDispatcher<LambdaInfo<?>> LAMBDA = new ConsumerRegistrationDispatcher();
}
