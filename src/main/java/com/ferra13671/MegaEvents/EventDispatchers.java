package com.ferra13671.MegaEvents;

import java.util.HashMap;

/**
 * @author Ferra13671
 * @LastUpdate 1.4.5
 */

public final class EventDispatchers {
    private static final HashMap<Class<?>, EventDispatcher<?>> eventDispatchers = new HashMap<>();

    public static <T extends Event<T>> void register(Class<T> eventClass, EventDispatcher<T> dispatcher) {
        eventDispatchers.put(eventClass, dispatcher);
    }

    public static <T extends Event<T>> EventDispatcher<T> getDispatcher(Class<T> eventClass) {
        return (EventDispatcher<T>) eventDispatchers.get(eventClass);
    }
}
