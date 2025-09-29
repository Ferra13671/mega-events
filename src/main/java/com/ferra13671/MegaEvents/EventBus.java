package com.ferra13671.MegaEvents;

import com.ferra13671.MegaEvents.register.RegistrationDispatcher;
import com.ferra13671.MegaEvents.register.RegistrationDispatchers;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.5
 */

public class EventBus implements IEventBus {

    @Override
    public void register(Object listener) {
        RegistrationDispatcher registrationDispatcher = listener instanceof Consumer ? RegistrationDispatchers.CONSUMER : RegistrationDispatchers.OBJECT;
        registrationDispatcher.register(listener);
    }

    @Override
    public void unregister(Object listener) {
        RegistrationDispatcher registrationDispatcher = listener instanceof Consumer ? RegistrationDispatchers.CONSUMER : RegistrationDispatchers.OBJECT;
        registrationDispatcher.unregister(listener);
    }

    @Override
    public <T extends Event<?>> void activate(T event) {
        event.getEventDispatcher().getInvokeConsumer().accept(Collections.singletonList(event));
    }
}
