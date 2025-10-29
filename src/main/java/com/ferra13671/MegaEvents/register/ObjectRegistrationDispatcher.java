package com.ferra13671.MegaEvents.register;

import com.ferra13671.MegaEvents.event.Event;
import com.ferra13671.MegaEvents.eventbus.EventSubscriber;

import java.lang.reflect.Method;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.7
 */

public class ObjectRegistrationDispatcher extends RegistrationDispatcher<Object> {

    @Override
    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                EventSubscriber annotation = method.getAnnotation(EventSubscriber.class);
                Class<? extends Event> clazz = annotation.event()[0];
                registerMethod(method, clazz, listener);
            }
        }
    }

    @Override
    public void unregister(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                EventSubscriber annotation = method.getAnnotation(EventSubscriber.class);
                unregisterMethod(method, annotation.event()[0], listener);
            }
        }
    }
}
