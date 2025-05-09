package com.ferra13671.MegaEvents.Base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Ferra13671
 * @LastUpdate 1.3
 */


public class EventBus implements IEventBus {
    private final HashMap<Class<?>, List<MethodWithObject>> registeredMap = new HashMap<>();

    @Override
    public void register(Object object) {
        HashMap<Class<?>, List<MethodWithObject>> temp = new HashMap<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                if (method.getParameterTypes().length > 0) {
                    if (temp.containsKey(method.getParameterTypes()[0])) {
                        temp.get(method.getParameterTypes()[0]).add(new MethodWithObject(object, method));
                    } else {
                        temp.put(method.getParameterTypes()[0], new ArrayList<>(Collections.singletonList(new MethodWithObject(object, method))));
                    }
                }
            }
        }
        temp.forEach((_class, methods) -> {
            if (registeredMap.containsKey(_class)) {
                registeredMap.get(_class).addAll(methods);
            } else {
                registeredMap.put(_class, new ArrayList<>(methods));
            }
            registeredMap.get(_class).sort(Comparator.comparing(method -> method.method.getAnnotation(EventSubscriber.class).priority()));
            Collections.reverse(registeredMap.get(_class));
        });
    }

    @Override
    public void unregister(Object object) {
        registeredMap.forEach((_class, methods) -> {
            Method[] objectMethods = object.getClass().getDeclaredMethods();
            methods.removeIf(method -> {
                for (Method m : objectMethods)
                    if (method.method.equals(m)) return true;
                return false;
            });
        });
    }

    @Override
    public <T extends Event> void activate(T event) {
        try {
            registeredMap.forEach((_class, methods) -> {
                if (_class.equals(event.getClass())) {
                    methods.forEach(method -> {
                        try {
                            method.method.invoke(method.object, event);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        } catch (Exception e) {
            if (!(e instanceof ConcurrentModificationException)) e.printStackTrace();
        }
    }

    private static class MethodWithObject {
        public final Object object;
        public final Method method;

        public MethodWithObject(Object object, Method method) {
            this.object = object;
            this.method = method;
        }
    }
}
