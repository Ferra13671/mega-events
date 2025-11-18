package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.*;
import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.event.EventDispatcher;
import com.ferra13671.MegaEvents.event.EventDispatchers;
import com.ferra13671.MegaEvents.eventbus.EventSubscriber;
import com.ferra13671.MegaEvents.eventbus.RegisteredMethod;
import com.ferra13671.MegaEvents.exeptions.InvokeRegisteredMethodException;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;

/**
 * EventBus Listener Registration Manager.
 * Controls how listeners are registered and unregistered.
 */
public abstract class RegistrationDispatcher<T> {

    /**
     * Registers the listener.
     *
     * @param listener listener.
     */
    public abstract void register(T listener);

    /**
     * Unregisters the listener.
     *
     * @param listener listener.
     */
    public abstract void unregister(T listener);

    /**
     * Recreates method to call all registered listeners in event dispatcher.
     *
     * @param eventDispatcher event dispatcher.
     */
    protected void recreateConsumer(EventDispatcher<?> eventDispatcher) {
        eventDispatcher.setInvokeConsumer(ListUtils.convertToConsumer(eventDispatcher.getRegisteredMap(), ((registeredMethod, args) -> {
            try {
                if (registeredMethod.ghostEvent)
                    registeredMethod.method.invoke(registeredMethod.object);
                else
                    registeredMethod.method.invoke(registeredMethod.object, args.toArray());
            } catch (Exception e) {
                throw new InvokeRegisteredMethodException(e);
            }
        })));
    }

    protected <S extends Event<S>> void registerMethod(Method method, Class<S> clazz, Object listener) {
        EventDispatcher<?> eventDispatcher = EventDispatchers.getDispatcher(clazz);
        boolean needAdd = true;
        for (RegisteredMethod registeredMethod : eventDispatcher.getRegisteredMap()) {
            if (registeredMethod.method.equals(method)) {
                needAdd = false;
                break;
            }
        }
        if (needAdd)
            eventDispatcher.getRegisteredMap().add(new RegisteredMethod(listener, method, method.getParameterTypes().length == 0));

        eventDispatcher.getRegisteredMap().sort(Comparator.comparing(registeredMethod ->
                registeredMethod.method.isAnnotationPresent(EventSubscriber.class) ? registeredMethod.method.getAnnotation(EventSubscriber.class).priority() : 0
        ));
        Collections.reverse(eventDispatcher.getRegisteredMap());

        recreateConsumer(eventDispatcher);
    }

    protected <S extends Event<S>> void unregisterMethod(Method method, Class<S> clazz, Object listener) {
        EventDispatcher<?> eventDispatcher = EventDispatchers.getDispatcher(clazz);
        eventDispatcher.getRegisteredMap().removeIf(registeredMethod -> registeredMethod.object.equals(listener) && registeredMethod.method.equals(method));

        recreateConsumer(eventDispatcher);
    }
}
