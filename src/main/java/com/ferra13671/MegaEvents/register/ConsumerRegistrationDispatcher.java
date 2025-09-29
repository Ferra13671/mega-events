package com.ferra13671.MegaEvents.register;


import com.ferra13671.MegaEvents.event.Event;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.6
 */

public class ConsumerRegistrationDispatcher extends RegistrationDispatcher {

    @Override
    public void register(Object listener) {
        if (listener instanceof Consumer<?>) {
            Consumer<?> consumer = (Consumer<?>) listener;
            for (Method method : consumer.getClass().getDeclaredMethods()) {
                if (method.getParameterTypes().length > 0) {
                    try {
                        registerMethod(method, (Class<? extends Event>) method.getParameterTypes()[0], listener);
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    @Override
    public void unregister(Object listener) {
        if (listener instanceof Consumer<?>) {
            Consumer<?> consumer = (Consumer<?>) listener;
            for (Method method : consumer.getClass().getDeclaredMethods()) {
                if (method.getParameterTypes().length > 0) {
                    try {
                        unregisterMethod(method, (Class<? extends Event>) method.getParameterTypes()[0], listener);
                    } catch (Exception ignored) {}
                }
            }
        }
    }
}
