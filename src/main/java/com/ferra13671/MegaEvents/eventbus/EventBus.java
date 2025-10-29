package com.ferra13671.MegaEvents.eventbus;

import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.lambda.LambdaInfo;
import com.ferra13671.MegaEvents.register.RegistrationDispatchers;

import java.util.*;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.7
 */

public class EventBus implements IEventBus {

    @Override
    public void register(Object listener) {
        if (listener instanceof LambdaInfo<?>) {
            LambdaInfo<?> lambdaInfo = (LambdaInfo<?>) listener;
            RegistrationDispatchers.LAMBDA.register(lambdaInfo);
        } else {
            RegistrationDispatchers.OBJECT.register(listener);
        }
    }

    @Override
    public void unregister(Object listener) {
        if (listener instanceof LambdaInfo<?>) {
            LambdaInfo<?> lambdaInfo = (LambdaInfo<?>) listener;
            RegistrationDispatchers.LAMBDA.unregister(lambdaInfo);
        } else {
            RegistrationDispatchers.OBJECT.unregister(listener);
        }
    }

    @Override
    public <T extends Event<?>> void activate(T event) {
        event.getEventDispatcher().getInvokeConsumer().accept(Collections.singletonList(event));
    }
}
