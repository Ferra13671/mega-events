package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.lambda.LambdaInfo;

import java.lang.reflect.Method;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.8
 */

public class LambdaRegistrationDispatcher extends RegistrationDispatcher<LambdaInfo<?>> {

    @Override
    public void register(LambdaInfo<?> listener) {
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
    public void unregister(LambdaInfo<?> listener) {
        for (Method method : listener.listener.getClass().getDeclaredMethods()) {
            if (method.getParameterTypes().length > 0) {
                try {
                    unregisterMethod(method, listener.clazz, listener.listener);
                } catch (Exception ignored) {}
            }
        }
    }
}
