package com.ferra13671.MegaEvents.event;

import java.util.HashMap;
import com.ferra13671.MegaEvents.eventbus.EventBus;

/**
 * A class designed to store and register event dispatchers.
 * <p>
 * You won't be able to call an event unless you've registered an event dispatcher for it in this class.
 *
 * @see EventDispatcher
 * @see EventBus
 * @see Event
 */
public final class EventDispatchers {
    /** List of all registered event dispatchers. **/
    private static final HashMap<Class<?>, EventDispatcher<?>> eventDispatchers = new HashMap<>();

    /**
     * Registers the event dispatcher.
     *
     * @param dispatcher event dispatcher.
     * @return event dispatcher.
     * @param <T> type of event that the event dispatcher should handle.
     * @param <G> type of event dispatcher.
     */
    public static  <T extends Event<T>, G extends EventDispatcher<T>> G register(G dispatcher) {
        eventDispatchers.put(dispatcher.getEventClass(), dispatcher);
        return dispatcher;
    }

    /**
     * Returns the event dispatcher for the given class.
     *
     * @param eventClass event class that the dispatcher should handle.
     * @return event dispatcher.
     * @param <T> type of event that the event dispatcher should handle.
     */
    public static <T extends Event<T>> EventDispatcher<T> getDispatcher(Class<T> eventClass) {
        return (EventDispatcher<T>) eventDispatchers.get(eventClass);
    }
}
