package com.ferra13671.MegaEvents.Base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Ferra13671
 * @version 1
 */

public class UpdatedEventBus implements IEventBus {
    private final HashMap<Class<?>, List<RegisteredObject>> registeredMap = new HashMap<>();

    @Override
    public void register(Object object) {
        HashMap<Class<?>, List<Method>> temp = new HashMap<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventSubscriber.class)) {
                if (method.getParameterTypes().length > 0) {
                    if (temp.containsKey(method.getParameterTypes()[0])) {
                        temp.get(method.getParameterTypes()[0]).add(method);
                    } else {
                        temp.put(method.getParameterTypes()[0], new ArrayList<>(Collections.singletonList(method)));
                    }
                }
            }
        }
        temp.forEach((_class, methods) -> {
            if (registeredMap.containsKey(_class)) {
                registeredMap.get(_class).add(new RegisteredObject(object, new ArrayList<>(methods)));
            } else {
                registeredMap.put(_class, new ArrayList<>(Collections.singletonList(new RegisteredObject(object, new ArrayList<>(methods)))));
            }
        });
    }

    @Override
    public void unregister(Object object) {
        registeredMap.forEach((_class, regObjects) -> {
            for (RegisteredObject registeredObject : regObjects) {
                if (registeredObject.object.equals(object)) {
                    regObjects.remove(registeredObject);
                    return;
                }
            }
        });
    }

    @Override
    public <T extends Event> void activate(T event) {
        try {
            registeredMap.forEach((_class, regObjects) -> {
                if (_class.equals(event.getClass())) {
                    regObjects.forEach(registeredObject -> registeredObject.methods.forEach(method -> {
                        try {
                            method.invoke(registeredObject.object, event);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }));
                }
            });
        } catch (Exception e) {
            if (!(e instanceof ConcurrentModificationException)) e.printStackTrace();
        }
    }


    private static class RegisteredObject {
        public final Object object;
        public final List<Method> methods;

        public RegisteredObject(Object object, List<Method> methods) {
            this.object = object;
            this.methods = methods;
        }
    }
}
