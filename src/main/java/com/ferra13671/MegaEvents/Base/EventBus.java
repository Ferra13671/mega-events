package com.ferra13671.MegaEvents.Base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ferra13671
 * @version 1
 */

public class EventBus implements IEventBus {
    private final HashMap<Object, List<Method>> registeredMap = new HashMap<>();

    @Override
    public void register(Object object) {

        registeredMap.put(object, Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(EventSubscriber.class))
                .collect(Collectors.toList())
        );
    }

    @Override
    public void unregister(Object object) {

        registeredMap.remove(object);
    }

    @Override
    public <T extends Event> void activate(T event) {
        if (event.isCancelled()) return;

        HashMap<Object, List<Method>> registeredMap = this.registeredMap;

        try {
            registeredMap.forEach((object, methods) -> methods
                    .forEach(method -> {
                        if (method.getParameterTypes()[0] != event.getClass()) return;

                        try {
                            method.invoke(object, event);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    })
            );
        } catch (Exception ignored) {}
    }
}
