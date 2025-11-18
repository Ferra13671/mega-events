package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.eventbus.LambdaListener;

import java.lang.reflect.Method;

/**
 * Implementation of a EventBus listener registration dispatcher that manages lambda listeners.
 */
public class LambdaRegistrationDispatcher extends RegistrationDispatcher<LambdaListener<?>> {

    @Override
    public void register(LambdaListener<?> listener) {
        for (Method method : listener.listener.getClass().getDeclaredMethods()) {
            if (method.getParameterTypes().length > 0) {
                try {
                    method.setAccessible(true);
                    registerMethod(method, listener.clazz, listener.listener);
                } catch (Exception ignored) {}
            }
        }
    }

    @Override
    public void unregister(LambdaListener<?> listener) {
        for (Method method : listener.listener.getClass().getDeclaredMethods()) {
            if (method.getParameterTypes().length > 0) {
                try {
                    unregisterMethod(method, listener.clazz, listener.listener);
                } catch (Exception ignored) {}
            }
        }
    }
}
