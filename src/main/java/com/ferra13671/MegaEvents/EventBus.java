package com.ferra13671.MegaEvents;

import com.ferra13671.MegaEvents.exeptions.InvokeRegisteredMethodException;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Ferra13671
 * @LastUpdate 1.5.3
 */

public class EventBus implements IEventBus {

    private void recreateConsumer(EventDispatcher<?> eventDispatcher) {
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

    @Override
    public void register(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                EventSubscriber annotation = method.getAnnotation(EventSubscriber.class);
                Class<? extends Event> clazz = annotation.event()[0];
                EventDispatcher<?> eventDispatcher = EventDispatchers.getDispatcher(clazz);
                boolean needAdd = true;
                for (RegisteredMethod registeredMethod : eventDispatcher.getRegisteredMap()) {
                    if (registeredMethod.method.equals(method)) {
                        needAdd = false;
                        break;
                    }
                }
                if (needAdd)
                    eventDispatcher.getRegisteredMap().add(new RegisteredMethod(object, method, method.getParameterTypes().length == 0));

                eventDispatcher.getRegisteredMap().sort(Comparator.comparing(registeredMethod -> registeredMethod.method.getAnnotation(EventSubscriber.class).priority()));
                Collections.reverse(eventDispatcher.getRegisteredMap());

                recreateConsumer(eventDispatcher);
            }
        }
    }

    @Override
    public void unregister(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                EventSubscriber annotation = method.getAnnotation(EventSubscriber.class);
                if (method.getParameterTypes().length > 0) {
                    EventDispatcher<?> eventDispatcher = EventDispatchers.getDispatcher((Class<? extends Event>) annotation.event()[0]);
                    eventDispatcher.getRegisteredMap().removeIf(registeredMethod -> registeredMethod.object.equals(object) && registeredMethod.method.equals(method));

                    recreateConsumer(eventDispatcher);
                }
            }
        }
    }

    @Override
    public <T extends Event<?>> void activate(T event) {
        event.getEventDispatcher().getInvokeConsumer().accept(Collections.singletonList(event));
    }
}
