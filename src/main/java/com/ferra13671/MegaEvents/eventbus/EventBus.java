package com.ferra13671.MegaEvents.eventbus;

import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.lambda.LambdaListener;
import com.ferra13671.MegaEvents.register.RegistrationDispatchers;

import java.util.*;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.7
 */

public class EventBus implements IEventBus {

    @Override
    public void register(Object listener) {
        if (listener instanceof LambdaListener<?>) {
            LambdaListener<?> lambdaListener = (LambdaListener<?>) listener;
            RegistrationDispatchers.LAMBDA.register(lambdaListener);
        } else {
            RegistrationDispatchers.OBJECT.register(listener);
        }
    }

    @Override
    public void unregister(Object listener) {
        if (listener instanceof LambdaListener<?>) {
            LambdaListener<?> lambdaListener = (LambdaListener<?>) listener;
            RegistrationDispatchers.LAMBDA.unregister(lambdaListener);
        } else {
            RegistrationDispatchers.OBJECT.unregister(listener);
        }
    }

    @Override
    public <T extends Event<?>> void activate(T event) {
        event.getEventDispatcher().getInvokeConsumer().accept(Collections.singletonList(event));
    }
}
